package com.epam.gm.web.servlets.fileuploader;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;

import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

 

public class UploadServlet implements HttpRequestHandler{
        
    private String altPath ;
    
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        altPath = request.getServletContext().getRealPath("/imgs/");
	        System.out.println("do Get alt path : "+altPath);
        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
            System.out.println("get method has been called");
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+ request.getParameter("getfile"));
            File file = new File(altPath + request.getParameter("getfile"));
            if (file.exists()) {
        	System.out.println(file.getAbsolutePath());
                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file));
                response.setContentLength((int) file.length());
                response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            }else{        	System.out.println(file.getAbsolutePath());}
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            System.out.println("delfile method has been called");
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+ request.getParameter("delfile"));
            File file = new File(altPath +request.getParameter("delfile"));
            if (file.exists()) {
                file.delete(); // TODO:check and report success
            } else{        	System.out.println(file.getAbsolutePath());}
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
            System.out.println("getthumb method has been called");
//            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getthumb"));
            File file = new File(altPath + request.getParameter("getthumb"));
                if (file.exists()) {
                    System.out.println(file.getAbsolutePath());
                    String mimetype = getMimeType(file);
                    if (mimetype.endsWith("png") || mimetype.endsWith("jpeg")|| mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
                        BufferedImage im = ImageIO.read(file);
                        if (im != null) {
                            BufferedImage thumb = Scalr.resize(im, 75); 
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            if (mimetype.endsWith("png")) {
                                ImageIO.write(thumb, "PNG" , os);
                                response.setContentType("image/png");
                            } else if (mimetype.endsWith("jpeg")) {
                                ImageIO.write(thumb, "jpg" , os);
                                response.setContentType("image/jpeg");
                            } else if (mimetype.endsWith("jpg")) {
                                ImageIO.write(thumb, "jpg" , os);
                                response.setContentType("image/jpeg");
                            } else {
                                ImageIO.write(thumb, "GIF" , os);
                                response.setContentType("image/gif");
                            }
                            ServletOutputStream srvos = response.getOutputStream();
                            response.setContentLength(os.size());
                            response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
                            os.writeTo(srvos);
                            srvos.flush();
                            srvos.close();
                        }
                    }
            } else{        	System.out.println(file.getAbsolutePath());}
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
    }
    
    /**
        * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
        * 
        */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    altPath = request.getServletContext().getRealPath("/imgs/");
	    System.out.println("do Post alt path : "+altPath);
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
                    File file = new File(altPath , item.getName());
                        item.write(file);
                        JSONObject jsono = new JSONObject();
                        jsono.put("name", item.getName());
                        jsono.put("size", item.getSize());
                        jsono.put("url", "upload.do?getfile=" + item.getName());
                        jsono.put("thumbnailUrl", "upload.do?getthumb=" + item.getName());
                        jsono.put("deleteUrl", "upload.do?delfile=" + item.getName());
                        jsono.put("deleteType", "GET");
                        json.put(jsono);
                        System.out.println("{\"files\": " +json.toString()+"}");
                }
            }
        } catch (FileUploadException e) {
                throw new RuntimeException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        } finally {
            writer.write("{\"files\": " +json.toString()+"}");
            writer.close();
        }

    }

    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpg")){
                mimetype = "image/jpg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpeg")){
                mimetype = "image/jpeg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("gif")){
                mimetype = "image/gif";
            }else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
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

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {

        altPath = request.getServletContext().getRealPath("/imgs/");
	
        if("POST".equals(request.getMethod())){
            System.out.println("post request ");
            doPost(request, response);
            return;
        }else {
            System.out.println("not post method");
        }

        if("GET".equals(request.getMethod())){
            System.out.println("get request ");
            doGet(request,response);
            return;
        }else {
            System.out.println("not get method");
        }

    }
}