package advancedquerying.controller;

import advancedquerying.service.IngredientService;
import advancedquerying.service.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class AppController implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public AppController(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
//        String inputSize = scanner.nextLine();
//        long id = Long.parseLong(scanner.nextLine());
//
//        List<String> result = this.shampooService.getAllShampoosBySizeOrLabelId(inputSize,id);
//        result.forEach(System.out::println);
        //ex 7
//       List<String> ingredients = this.getListOfIngredientsNames(scanner);
//       this.shampooService.selectShampoosByIngredients(ingredients)
//               .forEach(System.out::println);

        //Ex3
//        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
//        this.shampooService.getAllShampooByPrice(price).forEach(System.out::println);

        //Ex 4
//        String chars = scanner.nextLine();
//        this.shampooService.getAllShampoosStartingWith(chars).stream().forEach(System.out::println);

        //Ex 6
//        double price = Double.parseDouble(scanner.nextLine());
//        System.out.println(this.shampooService.findAllShampooWithPriceLessThan(BigDecimal.valueOf(price)).size());

//        int number = Integer.parseInt(scanner.nextLine());
//        this.shampooService.findShampoosWithIngredientsLessThan(number)
//                .stream().forEach(System.out::println);

        List<String>names = this.getListOfIngredientsNames(scanner);
        this.ingredientService.updateIngredientsPrices(names).forEach(i-> System.out.println(i));
       // BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
       // this.shampooService.getAllShampoosBySizeOrLabelId("MEDIUM",5).forEach(System.out::println);
//        this.shampooService.selectAllShampoosPriceGreater(price).forEach(System.out::println);
    }

    private List<String> getListOfIngredientsNames(Scanner scanner) {
        List<String> ingredientsNames = new ArrayList<>();
        while(true){
            String line = scanner.nextLine();
            if("".equals(line)){
                break;
            }
            ingredientsNames.add(line);
        }
        return ingredientsNames;
    }
}
