package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {


    @Expose
    private String name;


    @Expose
    private String type;


    @Expose
    private Integer age;

    @Expose
    private PassportJSONImportDTO passport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
