package Service;


import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServletService {

    public static Map<Integer,String> phraseStorage;

    public ServletService() {
        phraseStorage = new ConcurrentHashMap<>(Map.of(
                1, "У тебя все получится!",
                2, "Просто сделай это!",
                3, "Следуй за своей мечтой и не сомневайся!"));
    }

    public List<String> getPhrases(){
        List<String> phrases = new ArrayList<>(phraseStorage.values());
        Collections.shuffle(phrases);
        return phrases;
    }

    public void putNewPhrase (String additionalPhrase){
        phraseStorage.put((phraseStorage.size() + 1), additionalPhrase);
    }

}
