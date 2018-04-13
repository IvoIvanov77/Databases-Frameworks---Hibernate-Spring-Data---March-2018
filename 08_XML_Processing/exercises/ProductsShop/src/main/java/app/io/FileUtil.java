package app.io;

import app.io.contracts.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtil implements FileIO {

    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    private static final String RESOURCES_DIRECTORY = "src\\main\\resources\\";

    @Override
    public String read(String file)  {

        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream inputStream = getClass().getResourceAsStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }


    @Override
    public  void write(String path, String content)  {
        String qualifiedPath = PROJECT_DIRECTORY + File.separator + RESOURCES_DIRECTORY + path;
        try (OutputStream os = new FileOutputStream(qualifiedPath);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
