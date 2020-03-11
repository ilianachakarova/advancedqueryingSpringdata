package advancedquerying.repository;

import advancedquerying.domain.entities.Ingredient;
import advancedquerying.domain.entities.Label;
import advancedquerying.domain.entities.Shampoo;
import advancedquerying.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    List<Shampoo> findAllByBrand(String brand);
    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findAllByLabel(Label label);
    List<Shampoo>findAllByLabelAndSize(Label label, Size size);
    List<Shampoo> findAllBySizeOrLabel_IdOrderByPriceAsc(Size size, long id);
    @Query("select s from advancedquerying.domain.entities.Shampoo as s join s.ingredients as i where i in :ingredients")
    List<Shampoo> findByIngredientsIn(@Param(value = "ingredients") Set<Ingredient> ingredients);
    List<Shampoo>findAllByPriceAfterOrderByPriceDesc(BigDecimal price);
    List<Shampoo> findAllByPriceBefore(BigDecimal price);
    @Query("select s from advancedquerying.domain.entities.Shampoo as s where s.ingredients.size <=:number")
    List<Shampoo>countShampooByIngredientsCount(@Param(value = "number") int number);
    List<Shampoo>findAllByBrandStartingWith(String chars);
}
