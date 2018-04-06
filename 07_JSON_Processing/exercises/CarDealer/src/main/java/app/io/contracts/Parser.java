package app.io.contracts;

public interface Parser {

    public <T> T importJson(Class<T> oClass, String content);

    public <T> String exportJson(T object);
}
