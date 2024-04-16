package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class User {
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String city;
    private String phoneNumber;
    private List<Food> foods = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Dessert> desserts = new ArrayList<>();

    public User(String lastName, String firstName, Date birthDate, String city, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Dessert> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<Dessert> desserts) {
        this.desserts = desserts;
    }

    public void readFoods() {

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Foods.txt");

        if (!filePath.toFile().exists()) {
            System.err.println("File not found: " + filePath.toAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String foodName = line.trim();
                String[] ingredientsArray = br.readLine().split(" ");
                Integer ingredientCount = Integer.parseInt(ingredientsArray[0]);
                List<String> ingredients = new ArrayList<>();
                for (int i = 1; i <= ingredientCount; i++) {
                    ingredients.add(ingredientsArray[i]);
                }

                Integer weight = Integer.parseInt(br.readLine().trim());
                Double price = Double.parseDouble(br.readLine().trim());

                Food food = new Food(foodName, ingredients, weight, price);
                foods.add(food);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readDrinks() {

        drinks = new ArrayList<>();

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Drinks.txt");

        if (!filePath.toFile().exists()) {
            System.err.println("File not found: " + filePath.toAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String drinkName = line.trim();

                String[] contentPriceArray = br.readLine().split(" ");
                Integer content = Integer.parseInt(contentPriceArray[0]);
                Double price = Double.parseDouble(contentPriceArray[1]);

                Drink drink = new Drink(drinkName, content, price);
                drinks.add(drink);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readDesserts() {

        desserts = new ArrayList<>();

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Desserts.txt");

        if (!filePath.toFile().exists()) {
            System.err.println("File not found: " + filePath.toAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String dessertName = line.trim();
                String[] ingredientsArray = br.readLine().split(" ");
                Integer ingredientCount = Integer.parseInt(ingredientsArray[0]);
                List<String> ingredients = new ArrayList<>();
                for (int i = 1; i <= ingredientCount; i++) {
                    ingredients.add(ingredientsArray[i]);
                }

                Integer weight = Integer.parseInt(br.readLine().trim());
                Double price = Double.parseDouble(br.readLine().trim());

                Dessert dessert = new Dessert(dessertName, ingredients, weight, price);
                desserts.add(dessert);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printFoods() {

        for(int i = 0; i < foods.size(); i++) {
            foods.get(i).printFood();
        }

    }

    public void printDrinks() {

        for(int i = 0; i < drinks.size(); i++) {
            drinks.get(i).printDrink();
        }

    }

    public void printDesserts() {

        for(int i = 0; i < desserts.size(); i++) {
            desserts.get(i).printDessert();
        }

    }

    public void filterFoodsByInitialLetter(char letter) {

        List<Food> filteredFoods = foods.stream()
                .filter(food -> Character.toLowerCase(food.getName().charAt(0)) == Character.toLowerCase(letter))
                .collect(Collectors.toList());
        for (Food food : filteredFoods) {
            food.printFood();
        }

    }

    public void filterDrinksByInitialLetter(char letter) {

        List<Drink> filteredDrinks = drinks.stream()
                .filter(drink -> Character.toLowerCase(drink.getName().charAt(0)) == Character.toLowerCase(letter))
                .collect(Collectors.toList());
        for (Drink drink : filteredDrinks) {
            drink.printDrink();
        }

    }

    public void filterDessertsByInitialLetter(char letter) {

        List<Dessert> filteredDrinks = desserts.stream()
                .filter(dessert -> Character.toLowerCase(dessert.getName().charAt(0)) == Character.toLowerCase(letter))
                .collect(Collectors.toList());
        for (Dessert dessert : filteredDrinks) {
            dessert.printDessert();
        }

    }

    public void sortFoodsByNameAscending() {

        List<Food> foodsToSort = new ArrayList<>(foods);
        Collections.sort(foodsToSort, (food1, food2) -> food1.getName().compareToIgnoreCase(food2.getName()));
        for (Food food : foodsToSort) {
            food.printFood();
        }

    }

    public void sortFoodsByNameDescending() {

        List<Food> foodsToSort = new ArrayList<>(foods);
        Collections.sort(foodsToSort, Comparator.comparing(Food::getName, String.CASE_INSENSITIVE_ORDER).reversed());
        for (Food food : foodsToSort) {
            food.printFood();
        }

    }

    public void sortFoodsByPriceAscending() {

        List<Food> foodsToSort = new ArrayList<>(foods);
        Collections.sort(foodsToSort, Comparator.comparing(Food::getPrice));
        for (Food food : foodsToSort) {
            food.printFood();
        }

    }

    public void sortFoodsByPriceDescending() {

        List<Food> foodsToSort = new ArrayList<>(foods);
        Collections.sort(foodsToSort, Comparator.comparing(Food::getPrice).reversed());
        for (Food food : foodsToSort) {
            food.printFood();
        }

    }

    public void sortFoodsByAmountAscending() {

        List<Food> foodsToSort = new ArrayList<>(foods);
        Collections.sort(foodsToSort, Comparator.comparing(Food::getAmount));
        for (Food food : foodsToSort) {
            food.printFood();
        }

    }

    public void sortFoodsByAmountDescending() {

        List<Food> foodsToSort = new ArrayList<>(foods);
        Collections.sort(foodsToSort, Comparator.comparing(Food::getAmount).reversed());
        for (Food food : foodsToSort) {
            food.printFood();
        }

    }

    public void sortDrinksByNameAscending() {

        List<Drink> drinksToSort = new ArrayList<>(drinks);
        Collections.sort(drinksToSort, (drink1, drink2) -> drink1.getName().compareToIgnoreCase(drink2.getName()));
        for (Drink drink : drinksToSort) {
            drink.printDrink();
        }

    }

    public void sortDrinksByNameDescending() {

        List<Drink> drinksToSort = new ArrayList<>(drinks);
        Collections.sort(drinksToSort, Comparator.comparing(Drink::getName, String.CASE_INSENSITIVE_ORDER).reversed());
        for (Drink drink : drinksToSort) {
            drink.printDrink();
        }

    }

    public void sortDrinksByPriceAscending() {

        List<Drink> drinksToSort = new ArrayList<>(drinks);
        Collections.sort(drinksToSort, Comparator.comparing(Drink::getPrice));
        for (Drink drink : drinksToSort) {
            drink.printDrink();
        }

    }

    public void sortDrinksByPriceDescending() {

        List<Drink> drinksToSort = new ArrayList<>(drinks);
        Collections.sort(drinksToSort, Comparator.comparing(Drink::getPrice).reversed());
        for (Drink drink : drinksToSort) {
            drink.printDrink();
        }

    }

    public void sortDrinksByContentAscending() {

        List<Drink> drinksToSort = new ArrayList<>(drinks);
        Collections.sort(drinksToSort, Comparator.comparing(Drink::getContent));
        for (Drink drink : drinksToSort) {
            drink.printDrink();
        }

    }

    public void sortDrinksByContentDescending() {

        List<Drink> drinksToSort = new ArrayList<>(drinks);
        Collections.sort(drinksToSort, Comparator.comparing(Drink::getContent).reversed());
        for (Drink drink : drinksToSort) {
            drink.printDrink();
        }

    }

    public void sortDessertsByNameAscending() {

        List<Dessert> dessertsToSort = new ArrayList<>(desserts);
        Collections.sort(dessertsToSort, (dessert1, dessert2) -> dessert1.getName().compareToIgnoreCase(dessert2.getName()));
        for (Dessert dessert : dessertsToSort) {
            dessert.printDessert();
        }

    }

    public void sortDessertsByNameDescending() {

        List<Dessert> dessertsToSort = new ArrayList<>(desserts);
        Collections.sort(dessertsToSort, Comparator.comparing(Dessert::getName, String.CASE_INSENSITIVE_ORDER).reversed());
        for (Dessert dessert : dessertsToSort) {
            dessert.printDessert();
        }

    }

    public void sortDessertsByPriceAscending() {

        List<Dessert> dessertsToSort = new ArrayList<>(desserts);
        Collections.sort(dessertsToSort, Comparator.comparing(Dessert::getPrice));
        for (Dessert dessert : dessertsToSort) {
            dessert.printDessert();
        }

    }

    public void sortDessertsByPriceDescending() {

        List<Dessert> dessertsToSort = new ArrayList<>(desserts);
        Collections.sort(dessertsToSort, Comparator.comparing(Dessert::getPrice).reversed());
        for (Dessert dessert : dessertsToSort) {
            dessert.printDessert();
        }

    }

    public void sortDessertsByAmountAscending() {

        List<Dessert> dessertsToSort = new ArrayList<>(desserts);
        Collections.sort(dessertsToSort, Comparator.comparing(Dessert::getAmount));
        for (Dessert dessert : dessertsToSort) {
            dessert.printDessert();
        }

    }

    public void sortDessertsByAmountDescending() {

        List<Dessert> dessertsToSort = new ArrayList<>(desserts);
        Collections.sort(dessertsToSort, Comparator.comparing(Dessert::getAmount).reversed());
        for (Dessert dessert : dessertsToSort) {
            dessert.printDessert();
        }

    }

    public void printUser() {

        System.out.println(lastName);
        System.out.println(firstName);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedBirthDate = dateFormat.format(birthDate);
        System.out.println(formattedBirthDate);

        System.out.println(city);
        System.out.println(phoneNumber);

    }

}