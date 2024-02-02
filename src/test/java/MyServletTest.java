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

        private Main servlet;
        private HttpServletRequest request;
        private HttpServletResponse response;
        private StringWriter responseWriter;

        @BeforeEach
        public void setUp() throws Exception{
            servlet = new Main();
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
            assertTrue(Main.phraseStorage.values().contains("У тебя все получится!"));
            assertEquals(3, Main.phraseStorage.size());

        }

        @Test
        public void testPostServlet() throws ServletException, IOException {
            Mockito.when(request.getParameter("phrase")).thenReturn("testDoPost");
            servlet.doPost(request, response);
            assertTrue(Main.phraseStorage.values().contains("testDoPost"));
        }

    }
