package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;
import app.retake.parser.ModelParserImpl;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void create(VetXMLImportDTO dto) {
        Vet vet = ModelParserImpl.getInstance().map(dto, Vet.class);
        if(ValidationUtil.isValid(vet)){
            this.vetRepository.saveAndFlush(vet);
        }else {
            throw new IllegalArgumentException("Error: Invalid data.");
        }
    }
}
