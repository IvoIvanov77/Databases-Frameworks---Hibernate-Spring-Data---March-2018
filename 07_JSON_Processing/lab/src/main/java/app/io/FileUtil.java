package app.io;

import java.io.*;

public class FileUtil {

    private final static String PATH = "C:\\Users\\Ivaylo\\Documents\\JavaDB\\DatabasesFrameworks_HibernateAndSpringData\\09_JSON_Processing\\Skeleton\\people\\src\\main\\resources\\";

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

//    public static void write(String content, String fileName)  {
//        OutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(PATH + fileName);
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//            writer.write(String.valueOf(content));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

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
