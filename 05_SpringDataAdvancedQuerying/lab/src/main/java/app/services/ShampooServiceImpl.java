package app.services;

import app.enums.Size;
import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;
import app.repositories.LabelRepository;
import app.repositories.ShampooRepository;
import app.services.contracts.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository, LabelRepository labelRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public List<BasicShampoo> selectShampoosBySize(Size size) {
        return this.shampooRepository.findBySizeOrderByIdAsc(size);
    }

    @Override
    public List<BasicShampoo> selectShampoosBySizeOrLabel(Size size, Long id) {
        BasicLabel label = this.labelRepository.findOne(id);
        return this.shampooRepository.findBySizeOrLabelOrderByPriceAscIdAsc(size, label);
    }

    @Override
    public List<BasicShampoo> selectShampoosByPrice(BigDecimal price) {
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDescIdAsc(price);
    }

    @Override
    public Integer countShampoosByPrice(BigDecimal price) {
        return this.shampooRepository.countByPriceLessThan(price);
    }

    @Override
    public List<BasicShampoo> selectShampoosByIngredients(String... ingredientsNames) {
        return this.shampooRepository.findByIngredientsInJPQL(ingredientsNames);
    }

    @Override
    public List<BasicShampoo> selectShampoosByIngredientsLessThan(int count) {
        return this.shampooRepository.selectShampoosByIngredientsCount(count);
    }

    @Override
    public BigDecimal selectIngredientsPriceByShampooName(String name) {
        return this.shampooRepository.findShampooNamesAndIngredientsPrices(name);
    }
}
