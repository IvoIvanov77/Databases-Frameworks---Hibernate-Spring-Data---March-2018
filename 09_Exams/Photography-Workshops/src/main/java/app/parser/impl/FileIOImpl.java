package app.parser.impl;

import app.parser.api.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;


@Component
public class FileIOImpl implements FileIO {

    @Override
    public String read(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (
                InputStream inputStream = getClass().getResourceAsStream(fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line);
            }
        }

        return fileContent.toString();
    }

    @Override
    public void write(String fileName, String content) throws IOException {
        try (
                OutputStream outputStream = new FileOutputStream(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))
        ) {
            bufferedWriter.write(content);
        }
    }
}
