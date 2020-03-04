package advancedquerying.service;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.domain.entities.Shampoo;
import advancedquerying.domain.entities.Size;
import advancedquerying.repository.IngredientRepository;
import advancedquerying.repository.LabelRepository;
import advancedquerying.repository.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService{

    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final IngredientRepository ingredientRepository;
    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository, LabelRepository labelRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public List<String> selectShampoosBySize(String inputSize) {
        Size size = Size.valueOf(inputSize.toUpperCase());
        List<Shampoo> shampoos = this.shampooRepository.findAllBySizeOrderById(size);
        return shampoos.stream()
                .map(shampoo -> String.format("%s %s %.2flv",
                        shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampoosBySizeOrLabelId(String inputSize, long id) {
        Size size = Size.valueOf(inputSize.toUpperCase());

        List<Shampoo> shampoos = this.shampooRepository.findAllBySizeOrLabel_IdOrderByPriceAsc(size,id);

        return shampoos.stream()
                .map(shampoo -> String.format("%s %s %.2flv",
                        shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> selectShampoosByIngredients(List<String> ingredientsNames) {
        Set<Ingredient> ingredients = this.ingredientRepository.findAllByNameIn(ingredientsNames);
        List<Shampoo> shampoos = this.shampooRepository.findByIngredientsIn(ingredients);
        return shampoos.stream().map
                (shampoo -> String.format("%s", shampoo.getBrand())).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampooByPrice(BigDecimal price) {
       List<Shampoo> shampoos = this.shampooRepository.findAllByPriceAfterOrderByPriceDesc(price);
        return shampoos.stream()
                .map(shampoo -> String.format("%s %s %.2f"
                        ,shampoo.getBrand(),shampoo.getSize().name(),shampoo.getPrice())).
                        collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampoosStartingWith(String chars) {
       List<Shampoo> shampoos = this.shampooRepository.findAllByBrandStartingWith(chars);
        return shampoos.stream().map(shampoo -> String.format("%s",shampoo.getBrand())).collect(Collectors.toList());
    }

    @Override
    public List<Shampoo> findAllShampooWithPriceLessThan(BigDecimal bigDecimal) {
        return this.shampooRepository.findAllByPriceBefore(bigDecimal);
    }

    @Override
    public List<String> findShampoosWithIngredientsLessThan(int number) {
        List<Shampoo>shampoos = this.shampooRepository.countShampooByIngredientsCount(number);
        return
                shampoos.stream().map(shampoo -> String.format("%s",shampoo.getBrand()))
                .collect(Collectors.toList());
    }


}
