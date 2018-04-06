package app.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import java.io.File;

public class XMLParser {


    private final String path;

    public XMLParser(String path) {
        this.path = path;

    }

    @SuppressWarnings("unchecked")
    public <T> T importXml(Class<T> oClass, String fileName) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(oClass);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new File(path + fileName));
    }

    public <T> void exportXml(T object, String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, new File(path + fileName));
    }
}
