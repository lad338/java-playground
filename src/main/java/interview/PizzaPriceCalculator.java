package interview;

import static interview.PizzaPriceCalculator.PriceCalculator.Base.*;
import static interview.PizzaPriceCalculator.PriceCalculator.Size.*;
import static interview.PizzaPriceCalculator.PriceCalculator.Topping.*;

import java.util.*;

public class PizzaPriceCalculator {

    // Pizza has a base (normal, gluten free), a size (Small, med, etc), and toppings (mushrooms, pepperoni, etc)
    // price of pizza
    // Order - 0 or more pizzas, 0 or more drinks

    class PriceCalculator {

        Map<Base, Double> basePriceMap = new HashMap<>();
        Map<Size, Double> sizePriceMap = new HashMap<>();
        Map<Topping, Double> toppingPriceMap = new HashMap<>();
        Map<Drink, Double> drinkPriceMap = new HashMap<>();

        public PriceCalculator() {
            basePriceMap.put(NORMAL, 5.0);
            basePriceMap.put(GLUTEN_FREE, 6.5);
            sizePriceMap.put(SMALL, 3.0);
            sizePriceMap.put(MEDIUM, 4.7);
            sizePriceMap.put(LARGE, 5.33);
            toppingPriceMap.put(MUSHROOM, 1.0);
            toppingPriceMap.put(PEPPERONI, 0.5);
        }

        public double getOrderPrice(
            Map<Pizza, Integer> pizzas,
            Map<Drink, Integer> drinks
        ) {
            double sum = 0;
            for (Pizza pizza : pizzas.keySet()) {
                sum +=
                    getPizzaPrice(pizza.base, pizza.size, pizza.toppings) *
                    pizzas.get(pizza);
            }
            for (Drink drink : drinks.keySet()) {
                sum += drinkPriceMap.get(drink) * drinks.get(drink);
            }
            return sum;
        }

        public double getPizzaPrice(
            Base base,
            Size size,
            List<Topping> toppings
        ) {
            double sum = 0;

            //Assume base, size, toppings are not null and map contains them
            sum += basePriceMap.get(base);
            sum += sizePriceMap.get(size);
            for (Topping topping : toppings) {
                sum += toppingPriceMap.get(topping);
            }

            return sum;
        }

        public enum Base {
            NORMAL,
            GLUTEN_FREE,
        }

        public enum Size {
            SMALL,
            MEDIUM,
            LARGE,
        }

        public enum Topping {
            MUSHROOM,
            PEPPERONI,
        }

        public enum Drink {}

        public class Pizza {

            public Base base;
            public Size size;
            public List<Topping> toppings;

            public Pizza() {}
        }
    }
}
