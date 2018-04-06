package app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JSONParser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().create();
    }

    public <T> T importJson(Class<T> oClass, String fileName){
        String content = FileUtil.read(fileName);
        return this.gson.fromJson(content, oClass);
    }

    public <T> void exportJson(T object, String fileName)  {
        String content = this.gson.toJson(object);
        FileUtil.writeFile(fileName, content);
    }

    public <T> String exportJson(T object)  {
        return this.gson.toJson(object);

    }
}
