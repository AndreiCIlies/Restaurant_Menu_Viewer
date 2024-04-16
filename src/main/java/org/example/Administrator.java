package org.example;

import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Administrator {

    private List<Food> foods = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Dessert> desserts = new ArrayList<>();
    private List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isValidSingleWord(String input) {

        if (input == null || input.isEmpty()) {
            return false;
        }

        if (!Character.isUpperCase(input.charAt(0))) {
            return false;
        }

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetter(currentChar) && Character.isUpperCase(currentChar)) {
                return false;
            }

            if (currentChar == ' ') {
                return false;
            }
        }

        return true;

    }


    public boolean isValidCamelCase(String input) {

        if (input == null || input.isEmpty()) {
            return false;
        }

        if (Character.isLowerCase(input.charAt(0))) {
            return false;
        }

        boolean foundUpperCase = false;

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isLetter(currentChar) && Character.isUpperCase(currentChar)) {
                foundUpperCase = true;
            }

            if (!Character.isLetterOrDigit(currentChar)) {
                return false;
            }
        }

        return foundUpperCase;

    }

    public void createFood() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Food Name (it has to start with upper): ");
        String foodName = scanner.nextLine();
        if(!Character.isUpperCase(foodName.charAt(0)) || !foodName.matches("^[a-zA-Z ]+$")) {
            do {
                System.out.print("Introduce Food Name Again (it has to start with upper):");
                foodName = scanner.nextLine();
            } while(!Character.isUpperCase(foodName.charAt(0)) || !foodName.matches("^[a-zA-Z ]+$"));
        }

        System.out.print("Number of Ingredients: ");
        Integer noIngredients = null;
        boolean isValidNoIngredients = false;
        do {
            if(scanner.hasNextInt()) {
                noIngredients = scanner.nextInt();
                isValidNoIngredients = noIngredients > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Number of Ingredients Again: ");
                scanner.nextLine();
            }
        } while(!isValidNoIngredients);

        List<String> ingredients = new ArrayList<>();
        for(int i = 0; i < noIngredients; i++) {
            String ingredient;
            System.out.print("Ingredient (if it has more words, write it in Camel Case (e.g. SeaSalt, CherryTomatoSauce): ");
            ingredient = scanner.nextLine();
            if(!isValidSingleWord(ingredient) && !isValidCamelCase(ingredient)) {
                do {
                    System.out.print("Introduce Ingredient Again (if it has more words, write it in Camel Case, but with upper first letter (e.g. SeaSalt, CherryTomatoSauce): ");
                    ingredient = scanner.nextLine();
                } while(!isValidSingleWord(ingredient) && !isValidCamelCase(ingredient));
            }
            ingredients.add(ingredient);
        }

        System.out.print("Food Amount: ");
        Integer amount = null;
        boolean isValidFoodAmount = false;
        do {
            if(scanner.hasNextInt()) {
                amount = scanner.nextInt();
                isValidFoodAmount = amount > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Food Amount Again: ");
                scanner.nextLine();
            }
        } while(!isValidFoodAmount);

        System.out.print("Food Price (e.g. 6,0): ");
        Double price = null;
        boolean isValidPrice = false;
        do {
            if(scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                isValidPrice = price > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Food Price Again (e.g. 6,0): ");
                scanner.nextLine();
            }
        } while(!isValidPrice);

        Food food = new Food(foodName, ingredients, amount, price);
        foods.add(food);

        updateFoods();

    }

    public void createDrink() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Drink Name (it has to start with upper): ");
        String drinkName = scanner.nextLine();
        if(!Character.isUpperCase(drinkName.charAt(0)) || !drinkName.matches("^[a-zA-Z ]+$")) {
            do {
                System.out.print("Introduce Drink Name Again (it has to start with upper):");
                drinkName = scanner.nextLine();
            } while(!Character.isUpperCase(drinkName.charAt(0)) || !drinkName.matches("^[a-zA-Z ]+$"));
        }

        System.out.print("Drink Content: ");
        Integer content = null;
        boolean isValidDrinkContent = false;
        do {
            if(scanner.hasNextInt()) {
                content = scanner.nextInt();
                isValidDrinkContent = content > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Drink Content Again: ");
                scanner.nextLine();
            }
        } while(!isValidDrinkContent);

        System.out.print("Drink Price (e.g. 6,0): ");
        Double price = null;
        boolean isValidPrice = false;
        do {
            if(scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                isValidPrice = price > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Drink Price Again (e.g. 6,0): ");
                scanner.nextLine();
            }
        } while(!isValidPrice);

        Drink drink = new Drink(drinkName, content, price);
        drinks.add(drink);

        updateDrinks();

    }

    public void createDessert() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Dessert Name (it has to start with upper): ");
        String dessertName = scanner.nextLine();
        if(!Character.isUpperCase(dessertName.charAt(0)) || !dessertName.matches("^[a-zA-Z ]+$")) {
            do {
                System.out.print("Introduce Dessert Name Again (it has to start with upper):");
                dessertName = scanner.nextLine();
            } while(!Character.isUpperCase(dessertName.charAt(0)) || !dessertName.matches("^[a-zA-Z ]+$"));
        }

        System.out.print("Number of Ingredients: ");
        Integer noIngredients = null;
        boolean isValidNoIngredients = false;
        do {
            if(scanner.hasNextInt()) {
                noIngredients = scanner.nextInt();
                isValidNoIngredients = noIngredients > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Number of Ingredients Again: ");
                scanner.nextLine();
            }
        } while(!isValidNoIngredients);

        List<String> ingredients = new ArrayList<>();
        for(int i = 0; i < noIngredients; i++) {
            String ingredient;
            System.out.print("Ingredient (if it has more words, write it in Camel Case (e.g. ChocolateChip, BrownSugar): ");
            ingredient = scanner.nextLine();
            if(!isValidSingleWord(ingredient) && !isValidCamelCase(ingredient)) {
                do {
                    System.out.print("Introduce Ingredient Again (if it has more words, write it in Camel Case, but with upper first letter (e.g. ChocolateChip, BrownSugar): ");
                    ingredient = scanner.nextLine();
                } while(!isValidSingleWord(ingredient) && !isValidCamelCase(ingredient));
            }
            ingredients.add(ingredient);
        }

        System.out.print("Dessert Amount: ");
        Integer amount = null;
        boolean isValidDessertAmount = false;
        do {
            if(scanner.hasNextInt()) {
                amount = scanner.nextInt();
                isValidDessertAmount = amount > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Dessert Amount Again: ");
                scanner.nextLine();
            }
        } while(!isValidDessertAmount);

        System.out.print("Dessert Price (e.g. 6,0): ");
        Double price = null;
        boolean isValidPrice = false;
        do {
            if(scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                isValidPrice = price > 0;
                scanner.nextLine();
            } else {
                System.out.print("Introduce Dessert Price Again (e.g. 6,0): ");
                scanner.nextLine();
            }
        } while(!isValidPrice);

        Dessert dessert = new Dessert(dessertName, ingredients, amount, price);
        desserts.add(dessert);

        updateDesserts();

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

    public void deleteFood(int foodIndex) {

        int removeFoodIndex = foodIndex - 1;
        if(removeFoodIndex >= 0 && removeFoodIndex < foods.size()) {
            foods.remove(removeFoodIndex);
            updateFoods();
        } else {
            System.out.println("Invalid Food Index.");
            System.out.println();
        }

    }

    public void deleteDrink(int drinkIndex) {

        int removeDrinkIndex = drinkIndex - 1;
        if(removeDrinkIndex >= 0 && removeDrinkIndex < drinks.size()) {
            drinks.remove(removeDrinkIndex);
            updateDrinks();
        } else {
            System.out.println("Invalid Drink Index.");
            System.out.println();
        }

    }

    public void deleteDessert(int dessertIndex) {

        int removeDessertIndex = dessertIndex - 1;
        if(removeDessertIndex >= 0 && removeDessertIndex < desserts.size()) {
            desserts.remove(removeDessertIndex);
            updateDesserts();
        } else {
            System.out.println("Invalid Dessert Index.");
            System.out.println();
        }

    }

    public void updateFoods() {

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Foods.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for(Food food : foods) {
                bw.write(food.getName());
                bw.newLine();
                bw.write(food.getIngredients().size() + " " + String.join(" ", food.getIngredients()));
                bw.newLine();
                bw.write(food.getAmount().toString());
                bw.newLine();
                bw.write(food.getPrice().toString());
                if(!food.equals(foods.get(foods.size() - 1))) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateDrinks() {

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Drinks.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for(Drink drink : drinks) {
                bw.write(drink.getName());
                bw.newLine();
                bw.write(drink.getContent().toString() + " " + drink.getPrice().toString());
                if(!drink.equals(drinks.get(drinks.size() - 1))) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateDesserts() {

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Desserts.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for(Dessert dessert : desserts) {
                bw.write(dessert.getName());
                bw.newLine();
                bw.write(dessert.getIngredients().size() + " " + String.join(" ", dessert.getIngredients()));
                bw.newLine();
                bw.write(dessert.getAmount().toString());
                bw.newLine();
                bw.write(dessert.getPrice().toString());
                if(!dessert.equals(desserts.get(desserts.size() - 1))) {
                    bw.newLine();
                }
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

    public void readUsers() {

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Users.txt");

        if (!filePath.toFile().exists()) {
            System.err.println("File not found: " + filePath.toAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String lastName = line.trim();
                String firstName = br.readLine().trim();
                Date birthDate = parseDate(br.readLine().trim());
                String city = br.readLine().trim();
                String phoneNumber = br.readLine().trim();

                User user = new User(lastName, firstName, birthDate, city, phoneNumber);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printUsers() {

        for(int i = 0; i < users.size(); i++) {
            users.get(i).printUser();
            System.out.println();
        }

    }

    public void sortUsersByBirthDate() {

        Comparator<User> byBirthDate = Comparator.comparing(User::getBirthDate);

        List<User> usersToSort = users.stream()
                .sorted(byBirthDate)
                .collect(Collectors.toList());

        for (User user : usersToSort) {
            user.printUser();
            System.out.println();
        }

    }

    public void sortUsersByCity() {

        List<User> usersToSort = new ArrayList<>(users);
        Collections.sort(usersToSort, (user1, user2) -> user1.getCity().compareToIgnoreCase(user2.getCity()));
        for (User user : usersToSort) {
            user.printUser();
            System.out.println();
        }

    }

}