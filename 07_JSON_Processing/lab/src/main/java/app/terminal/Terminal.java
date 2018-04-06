package app.terminal;

import app.domain.dto.PersonDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.io.JSONParser;
import app.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private final PersonService personService;
    private JSONParser jsonParser;

    @Autowired
    public Terminal(PersonService personService) {
        this.personService = personService;
        this.jsonParser = new JSONParser();
    }


    @Override
    public void run(String... strings) throws Exception {
        PersonDto personDto = this.jsonParser.importJson(PersonDto.class, "person.json");

        ModelMapper modelMapper = new ModelMapper();
        Person person = modelMapper.map(personDto, Person.class);
        this.personService.create(person);

        Person p = this.personService.findById(1);
        PersonDto pDto = modelMapper.map(p, PersonDto.class);

        this.jsonParser.exportJson(pDto, "ivaylo.json");

//        for (PhoneNumber phoneNumber : person.getPhoneNumbers()) {
//            System.out.println(phoneNumber.getPerson().getFirstName());
//        }
    }
}
