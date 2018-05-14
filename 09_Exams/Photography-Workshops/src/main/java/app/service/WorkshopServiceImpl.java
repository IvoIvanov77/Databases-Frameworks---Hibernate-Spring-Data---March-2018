package app.service;

import app.model.dtos.xml_import.ImportWorkshopFromXmlDto;
import app.model.dtos.xml_import.ParticipantDto;
import app.model.dtos.xml_import.WrapperWorkshopsDto;
import app.model.entities.Photographer;
import app.model.entities.Workshop;
import app.repositories.PhotographerRepository;
import app.repositories.WorkshopRepository;
import app.service.contracts.WorkshopService;
import app.utils.DataValidator;
import app.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final PhotographerRepository photographerRepository;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository, PhotographerRepository photographerRepository) {
        this.workshopRepository = workshopRepository;
        this.photographerRepository = photographerRepository;
    }

    @Override
    public String importAccessoryFromXml(WrapperWorkshopsDto dtos){

        StringBuilder builder = new StringBuilder();

        for (ImportWorkshopFromXmlDto dto : dtos.getWorkshops()) {
            Workshop workshop = this.mapDtoToEntity(dto);
            if(DataValidator.isValid(workshop)){
                this.workshopRepository.saveAndFlush(workshop);
                builder.append(String.format("Successfully imported %s", workshop.getName()))
                        .append(System.lineSeparator());
            }else{
                builder.append("Error. Invalid data provided")
                        .append(System.lineSeparator());
            }
        }
        return builder.toString().trim();

    }


    private Workshop mapDtoToEntity(ImportWorkshopFromXmlDto dto){
        Map<String, Photographer> allPhotographers = this.photographerRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Photographer::getFullName, Function.identity()));
        Workshop workshop = ModelParser.getInstance().map(dto, Workshop.class);

        Photographer trainer = allPhotographers.get(dto.getTrainer());
        workshop.setTrainer(trainer);

        if(dto.getParticipants() == null){
            return workshop;
        }
        Set<Photographer> participants = new HashSet<>();
        for (ParticipantDto p : dto.getParticipants()) {
            String key = p.getFirstName() + " " + p.getLastName();
            Photographer participant = allPhotographers.get(key);
            participants.add(participant);
        }
        workshop.setParticipants(participants);
        return workshop;
    }


}
