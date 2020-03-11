package advancedquerying.service;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.domain.entities.Shampoo;
import advancedquerying.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientsServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientsServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> updateIngredientsPrices(List<String> names) {
        List<Ingredient> updatedIngredients = this.ingredientRepository.updateIngredientsPrice(names);
        return updatedIngredients.stream().map(ingredient -> String.format("%s %s",
                ingredient.getName(),ingredient.getShampoos().stream().map(Shampoo::getBrand))).collect(Collectors.toList());
    }
}
