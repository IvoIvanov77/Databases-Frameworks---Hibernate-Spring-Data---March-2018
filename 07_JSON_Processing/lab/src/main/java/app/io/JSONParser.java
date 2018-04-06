package app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class JSONParser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    public <T> T importJson(Class<T> oClass, String fileName){
        String content = FileUtil.read(fileName);
        return this.gson.fromJson(content, oClass);
    }

    public <T> void exportJson(T object, String fileName) throws IOException {
        String content = this.gson.toJson(object);
        System.out.println(content);
        FileUtil.writeFile(fileName, content);
    }
}
