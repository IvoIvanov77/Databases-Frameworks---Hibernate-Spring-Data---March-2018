package app.io.contracts;

import java.io.IOException;

public interface FileIO {

    String read(String file) throws IOException;

    void write(String path, String content) throws IOException;
}
