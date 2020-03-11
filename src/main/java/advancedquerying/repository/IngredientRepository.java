package advancedquerying.repository;

import advancedquerying.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Set<Ingredient> findAllByNameIn(List<String>ingredientNames);

    @Transactional
   @Query("update advancedquerying.domain.entities.Ingredient as i set i.price = 1.1*i.price where i.name in :names")
    int updateIngredientsPrice(@Param(value = "names") List<String>names);
}
