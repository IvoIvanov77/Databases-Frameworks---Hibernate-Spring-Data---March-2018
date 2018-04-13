package app.domain.xml_dto.p04_cars_with_their_list_of_parts;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class XmlCarDto {

    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    @XmlElement(name = "parts")
    private WrapperPartDto partDtos;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public WrapperPartDto getPartDtos() {
        return partDtos;
    }

    public void setPartDtos(WrapperPartDto partDtos) {
        this.partDtos = partDtos;
    }
}
