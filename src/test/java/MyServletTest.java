import Service.ServletService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

    public class MyServletTest extends Mockito {

        private SimpleServlet servlet;
        private ServletService service;
        private HttpServletRequest request;
        private HttpServletResponse response;
        private StringWriter responseWriter;

        @BeforeEach
        public void setUp() throws Exception{
            service = new ServletService();
            servlet = new SimpleServlet(service);
            request = mock(HttpServletRequest.class);
            response = mock(HttpServletResponse.class);
            responseWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(responseWriter);
            when(response.getWriter()).thenReturn(writer);
        }


        @Test
        public void testDoServlet() throws ServletException, IOException {
            servlet.doGet(request, response);
            Mockito.verify(response).setContentType(Mockito.eq("text/plain;charset=UTF-8"));
            assertTrue(service.getPhrases().contains("У тебя все получится!"));
            assertEquals(3, service.getPhrases().size());

        }

        @Test
        public void testPostServlet() throws ServletException, IOException {
            Mockito.when(request.getParameter("phrase")).thenReturn("testDoPost");
            servlet.doPost(request, response);
            assertTrue(service.getPhrases().contains("testDoPost"));
        }

    }
