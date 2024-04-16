package org.example;
import java.util.List;

public class Dessert {

    private String name;
    private List<String> ingredients;
    private Integer amount;
    private Double price;

    public Dessert(String name, List<String> ingredients, Integer amount, Double price) {

        this.name = name;
        this.ingredients = ingredients;
        this.amount = amount;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void printDessert() {

        System.out.println(name);
        System.out.print(ingredients.get(0));
        for(int i = 1; i < ingredients.size(); i++) {
            System.out.print(", " + ingredients.get(i));
        }
        System.out.println();
        System.out.println(amount);
        System.out.println(price);
        System.out.println();

    }

}