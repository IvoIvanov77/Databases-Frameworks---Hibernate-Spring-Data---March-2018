package app.io;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.*;


public final class FileUtil {

    private final static String PATH =
            "C:\\Users\\Ivaylo\\Desktop\\Databases-Frameworks---Hibernate-Spring-Data---March-2018\\07_JSON_Processing\\exercises\\ProductsShop\\src\\main\\resources\\data\\json\\";


    private FileUtil() {
    }

    public static String read(String fileName)  {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(PATH + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }



    public static void writeFile(String fileName, String content)  {
        try (OutputStream os = new FileOutputStream(PATH + fileName);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
