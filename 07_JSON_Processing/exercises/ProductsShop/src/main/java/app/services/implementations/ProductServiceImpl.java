package app.services.implementations;

import app.domain.dto.json.p00_seed_database.ImportProductJsonDto;
import app.domain.dto.json.p01_products_in_range.ExportProductInRangeDto;
import app.domain.models.Product;
import app.repositories.CategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.UserRepository;
import app.services.contracts.ProductService;
import app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.domain.models.Category;
import app.domain.models.User;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private Random random;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.random = new Random();
    }


    @Override
    public void create(ImportProductJsonDto dto) {
        Product product = Mapper.getInstance().map(dto, Product.class);
        int flag = random.nextInt(2);
        product.setSeller(this.getRandomUser());
        if(flag == 0){
            product.setBuyer(this.getRandomUser());
        }
        product.setCategories(this.getRandomCategories(3));
        this.productRepository.save(product);

    }

    @Override
    public List<ExportProductInRangeDto> productsInRage(BigDecimal low, BigDecimal high) {
        return this.productRepository
                .findAllByPriceBetweenAndBuyerNullOrderByPrice(low, high)
                .stream()
                .map(ExportProductInRangeDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> productsWithBuyerBySeller(User user){
        return this.productRepository.findBySellerAndBuyerNotNull(user);
    }

    @Override
    public List<Product> productsBySeller(User user){
        return this.productRepository.findBySeller(user);
    }


    @Override
    public Set<Product> findByCategory(Category category){
        return this.productRepository.findByCategoriesContains(category);
    }


    private User getRandomUser() {

        long userId = random.nextInt((int)this.userRepository.count()) + 1;

        return this.userRepository.findOne(userId);
    }


    private Set<Category> getRandomCategories(int limit) {

        int length = random.nextInt(limit) + 1;
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < length ; i++) {
            long id = random.nextInt((int)this.categoryRepository.count()) + 1;
            categories.add(this.categoryRepository.findOne(id));
        }
        return categories;
    }
}
