package advancedquerying.service;

import advancedquerying.domain.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<String> selectAllShampoosPriceGreater(BigDecimal price);
    List<String> selectShampoosBySize(String inputSize);
    List<String> getAllShampoosBySizeOrLabelId(String inputSize, long id);
    List<String> selectShampoosByIngredients(List<String>ingredientsNames);
    List<String> getAllShampooByPrice(BigDecimal price);
    List<String> getAllShampoosStartingWith(String chars);
    List<Shampoo> findAllShampooWithPriceLessThan(BigDecimal bigDecimal);
    List<String> findShampoosWithIngredientsLessThan(int number);
}
