package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Passport;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.ModelParserImpl;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.*;
import app.retake.services.api.ProcedureService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private static final String ERROR_MESSAGE = "Error: Invalid data.";
    private final ProcedureRepository procedureRepository;
    private final VetRepository vetRepository;
    private final PassportRepository passportRepository;
    private final AnimalRepository animalRepository;
    private final AnimalAidRepository animalAidRepository;

    public ProcedureServiceImpl(ProcedureRepository procedureRepository, VetRepository vetRepository,
                                PassportRepository passportRepository, AnimalRepository animalRepository,
                                AnimalAidRepository animalAidRepository) {
        this.procedureRepository = procedureRepository;
        this.vetRepository = vetRepository;
        this.passportRepository = passportRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Vet vet = this.vetRepository.getFirstByName(dto.getVetName());
        if(vet == null){
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        Passport passport = this.passportRepository.findOne(dto.getAnimalPassportSerialNumber());
        if (passport == null){
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        Set<AnimalAid> animalAids = new HashSet<>();

        for (ProcedureAnimalAidXMLImportDTO animalAidDto  : dto.getAnimalIds() ) {
            AnimalAid animalAid = this.animalAidRepository.getFirstByName(animalAidDto.getName());
            if(animalAid == null){
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            animalAids.add(animalAid);
        }

        Procedure procedure = new Procedure();
        procedure.setVet(vet);
        procedure.setAnimal(this.animalRepository.getFirstByPassport(passport));
        procedure.setServices(animalAids);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        procedure.setDatePerformed(dateFormat.parse(dto.getDate()));

        this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        List<Procedure> allProcedure = this.procedureRepository.findAllWithServices();
        ProcedureWrapperXMLExportDTO procedureExportDTO = new ProcedureWrapperXMLExportDTO();
        List<ProcedureXMLExportDTO> dtoList = allProcedure.stream()
                .map(procedure -> {
                    ProcedureXMLExportDTO dto = new ProcedureXMLExportDTO(procedure);
                    Set<ProcedureAnimalAidXMLExportDTO> animalAidDtos = new HashSet<>();
                    procedure.getServices().forEach(animalAid ->
                            animalAidDtos.add(ModelParserImpl.getInstance().map(animalAid,
                                    ProcedureAnimalAidXMLExportDTO.class))
                    );
                    dto.setAnimalAids(animalAidDtos);
                    return dto;
                })
                .collect(Collectors.toList());
        procedureExportDTO.setProcedures(dtoList);
        return procedureExportDTO;
    }


}

