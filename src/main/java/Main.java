import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


    @WebServlet("/help-service/v1/support")
    public class Main extends HttpServlet {

        static Map<Integer,String> phraseStorage = new ConcurrentHashMap<>(Map.of(
                1, "У тебя все получится!",
                2, "Просто сделай это!",
                3, "Следуй за своей мечтой и не сомневайся!"));


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain;charset=UTF-8");
            List<String> phrases = new ArrayList<>(phraseStorage.values());
            Collections.shuffle(phrases);
            response.getWriter().println(phrases.stream().findAny().get());
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/plain;charset=UTF-8");
            String additionalPhrase = request.getParameter("phrase");
            phraseStorage.put((phraseStorage.size() + 1), additionalPhrase);
        }

    }
