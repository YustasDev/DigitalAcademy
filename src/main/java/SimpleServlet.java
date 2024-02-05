import Service.ServletService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


    @WebServlet("/help-service/v1/support")
    public class SimpleServlet extends HttpServlet {

        ServletService service;

        public SimpleServlet() {
            this.service = new ServletService();
        }


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain;charset=UTF-8");
            var phrases = service.getPhrases();
            response.getWriter().println(phrases.stream().findAny().get());
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/plain;charset=UTF-8");
            String additionalPhrase = request.getParameter("phrase");
            service.putNewPhrase(additionalPhrase);
                PrintWriter out = response.getWriter();
                out.println("The phrase was successfully added");
                out.flush();
        }

    }
