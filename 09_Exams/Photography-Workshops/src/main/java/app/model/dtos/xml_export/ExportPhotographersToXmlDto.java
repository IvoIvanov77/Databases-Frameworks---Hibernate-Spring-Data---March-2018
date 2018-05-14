package app.model.dtos.xml_export;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ExportPhotographersToXmlDto {

//    photographer name={firstName} {lastName}” primary-camera=”{make} {model}”>

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "primary-camera")
    private String primaryCamera;

    public ExportPhotographersToXmlDto() {
    }

    public ExportPhotographersToXmlDto(String firstName, String lastName,
                                       String primaryCameraMake, String primaryCameraModel) {
        this.name = String.format("%s %s", firstName, lastName);
        this.primaryCamera = String.format("%s %s", primaryCameraMake, primaryCameraModel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(String primaryCamera) {
        this.primaryCamera = primaryCamera;
    }
}
