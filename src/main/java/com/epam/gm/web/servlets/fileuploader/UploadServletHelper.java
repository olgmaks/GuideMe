package com.epam.gm.web.servlets.fileuploader;

import com.epam.gm.model.Photo;
import com.epam.gm.services.PhotoService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UploadServletHelper {

    private String targetFolderPath;
    private String absoluteFolderPath;
    private String targerUrl;
    private Integer ownerId;
    private String ownerType;
    private PhotoService photoService;
    private static Map<String, Integer> pathIdMap;

    static {
        pathIdMap = new HashMap<String, Integer>();
    }

    public UploadServletHelper(
            String targetFolderPath, String targetUrl, PhotoService photoService, Integer ownerId, String ownerType) {
        this.targetFolderPath = targetFolderPath;
        this.targerUrl = targetUrl;
        this.photoService = photoService;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
    }

    public static UploadServletHelper getInstance(String targetFolderPath,
                                                  String targetUrl,
                                                  PhotoService photoService,
                                                  Integer ownerId,
                                                  String ownerType) {

        return new UploadServletHelper(targetFolderPath, targetUrl, photoService, ownerId, ownerType);
    }

    public void dropMap() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalArgumentException, IllegalAccessException, SQLException {
        absoluteFolderPath = request.getServletContext().getRealPath(targetFolderPath);
        System.out.println("do Get alt path : " + absoluteFolderPath);
        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
            System.out.println("get method has been called");
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+ request.getParameter("getfile"));
            String getFileParam = request.getParameter("getfile");
            File file = new File(absoluteFolderPath + getFileParam);
            if (file.exists()) {
                System.out.println(file.getAbsolutePath());

                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file));
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            }
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            System.out.println("delfile method has been called");

            File file = new File(absoluteFolderPath + request.getParameter("delfile"));
            if (file.exists()) {
                String path = targetFolderPath.substring(1) + request.getParameter("delfile");
//        	System.out.println("delete path : "+path);
                photoService.deletePhoto(pathIdMap.get(path));
                file.delete(); // TODO:check and report success
            }
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
            System.out.println("getthumb method has been called");
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getthumb"));
            File file = new File(absoluteFolderPath + request.getParameter("getthumb"));
            if (file.exists()) {
                System.out.println(file.getAbsolutePath());
                String path = targetFolderPath.substring(1) + request.getParameter("getthumb");
//                   System.out.println("save path : "+ path);

                Photo photo = new Photo();
                photo.setPath(path);
                photo.setOwnerId(ownerId);
                photo.setOwnerType(ownerType);
                Integer photoId = photoService.savePhotoReturnId(photo);
//                   System.out.println("photo id : " + photoId);
                pathIdMap.put(path, photoId);

                String mimetype = getMimeType(file);
                if (mimetype.endsWith("png") || mimetype.endsWith("jpeg") || mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
                    BufferedImage im = ImageIO.read(file);

                    if (im != null) {
                        BufferedImage thumb = Scalr.resize(im, 75);
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        if (mimetype.endsWith("png")) {
                            ImageIO.write(thumb, "PNG", os);
                            response.setContentType("image/png");
                        } else if (mimetype.endsWith("jpeg")) {
                            ImageIO.write(thumb, "jpg", os);
                            response.setContentType("image/jpeg");
                        } else if (mimetype.endsWith("jpg")) {
                            ImageIO.write(thumb, "jpg", os);
                            response.setContentType("image/jpeg");
                        } else {
                            ImageIO.write(thumb, "GIF", os);
                            response.setContentType("image/gif");
                        }
                        ServletOutputStream srvos = response.getOutputStream();
                        response.setContentLength(os.size());
                        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                        os.writeTo(srvos);
                        srvos.flush();
                        srvos.close();
                    }
                }
            } else {
                System.out.println(file.getAbsolutePath());
            }
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        absoluteFolderPath = request.getServletContext().getRealPath(targetFolderPath);

        System.out.println("do Post alt path : " + absoluteFolderPath);

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
//                        File file = new File(request.getServletContext().getRealPath("/")+"imgs/", item.getName());
                    File file = new File(absoluteFolderPath, item.getName());
                    item.write(file);
                    JSONObject jsono = new JSONObject();

                    if (!item.getName().contains(".jpg")
                            && !item.getName().contains(".png")
                            && !item.getName().contains(".jpeg")
                            && !item.getName().contains(".gif")) {
                        jsono.put("name", item.getName());
                        jsono.put("size", item.getSize());
                        jsono.put("error", "Filetype not allowed. Supported types: .png, .jpg, .jpeg, .gif ");
                        json.put(jsono);
                        continue;
                    }

                    jsono.put("name", item.getName());
                    jsono.put("size", item.getSize());
                    jsono.put("url", targerUrl + "?getfile=" + item.getName());
                    jsono.put("thumbnailUrl", targerUrl + "?getthumb=" + item.getName());
                    jsono.put("deleteUrl", targerUrl + "?delfile=" + item.getName());
                    jsono.put("deleteType", "GET");
                    json.put(jsono);
                    System.out.println("{\"files\": " + json.toString() + "}");
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writer.write("{\"files\": " + json.toString() + "}");
            writer.close();
        }

    }

    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            } else if (getSuffix(file.getName()).equalsIgnoreCase("jpg")) {
                mimetype = "image/jpg";
            } else if (getSuffix(file.getName()).equalsIgnoreCase("jpeg")) {
                mimetype = "image/jpeg";
            } else if (getSuffix(file.getName()).equalsIgnoreCase("gif")) {
                mimetype = "image/gif";
            } else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }


    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }


}
