package app.parser.impl;

import app.parser.api.Serializer;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;


@Component(value = "XMLParser")
public class XMLFileParser implements Serializer {

    private JAXBContext jaxbContext;


    @Override
    public <T> T deserialize(Class<T> className, String fileContent) throws JAXBException, IOException {
        this.jaxbContext = JAXBContext.newInstance(className);
        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
        T object;
        try (
                InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes())
        ) {
            object = (T) unmarshaller.unmarshal(inputStream);
        }
        return object;
    }


    @Override
    public <T> void serialize(T t, String fileName) {
        //impl
    }

    @Override
    public <T> String serialize(T t) throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = this.jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(t, sw);

        String result;
        result = sw.toString();
        return result;
    }
}
