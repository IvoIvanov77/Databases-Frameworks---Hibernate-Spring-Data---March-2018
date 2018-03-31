package app.io;

import java.io.IOException;
import java.util.List;

public interface Rider {
    List<String> read(String file) throws IOException;
}
