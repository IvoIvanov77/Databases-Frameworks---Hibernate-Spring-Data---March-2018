package app.domain.dto.p00_seed_database;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WrapperUsersImportDto {

    @XmlElement(name = "user")
    private List<ImportUserXmlDto> users;

    public List<ImportUserXmlDto> getUsers() {
        return users;
    }

    public void setUsers(List<ImportUserXmlDto> users) {
        this.users = users;
    }
}
