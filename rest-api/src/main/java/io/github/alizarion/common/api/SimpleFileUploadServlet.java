package io.github.alizarion.common.api;

import io.github.alizarion.common.tools.MultipartRequestMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbn on 11/05/2016.
 */
@WebServlet("/uploadFiles")
@MultipartConfig
public class SimpleFileUploadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        MultipartRequestMap map  = new MultipartRequestMap(request);
        File file = map.getFileParameter("submitted");
    }
}
