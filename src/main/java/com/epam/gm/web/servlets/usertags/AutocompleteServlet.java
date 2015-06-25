package com.epam.gm.web.servlets.usertags;

import com.epam.gm.model.Tag;
import com.epam.gm.services.TagService;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AutocompleteServlet implements HttpRequestHandler {

    private TagService tagService;

    public AutocompleteServlet() {
        tagService = new TagService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

//		Map<String, String[]> params = request.getParameterMap();
//		for (Map.Entry<String, String[]> entry : params.entrySet()) {
//			System.out.println("key:" + entry.getKey());
//			System.out.println("val:" + entry.getValue()[0]);
//		}

        if ("userSearchTags".equals(request.getParameter("getTags"))) {
            System.out.println("get tags has been called");
            List<Tag> tags = tagService.getAllActiveTags();
            List<String> list = new ArrayList<>();
            for (Tag tag : tags) {
                list.add(tag.getName());
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            System.out.println(list);
            response.getWriter().write(new Gson().toJson(list));
            return;
        }

        String term = request.getParameter("term");

        if (term != null) {
            List<Tag> tags = new TagService().getAllActiveTags();

            StringBuilder sb = new StringBuilder();
            StringJoiner join = new StringJoiner(",");
            for (Tag tag : tags) {
                if (term.trim().length() == 0 || tag.getName().toLowerCase().startsWith(term.trim().toLowerCase())) {

                    sb.append("{\"id\":").append(tag.getId()).append(",\"label\":\"").append(tag.getName())
                            .append("\",\"value\":\"").append(tag.getName()).append("\"}");

                    join.add(sb.toString());
                    sb.setLength(0);
                }
            }

            String result = sb.append("[").append(join.toString()).append("]").toString();

            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);


        }


    }

}
