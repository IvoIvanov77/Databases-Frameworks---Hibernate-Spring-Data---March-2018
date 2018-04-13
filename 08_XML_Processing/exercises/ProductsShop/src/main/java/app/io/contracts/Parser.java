package app.io.contracts;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Parser {

    public <T> T importXml(Class<T> oClass, String content) throws IOException, JAXBException;

    public <T> String exportXml(T object) throws IOException, JAXBException;
}
