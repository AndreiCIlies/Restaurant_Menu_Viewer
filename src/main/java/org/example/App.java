package org.example;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<User> users = new ArrayList<>();

    private static String[] roles = {"Administrator", "administrator", "Admin", "admin", "User", "user"};

    public static Boolean verifyRole(String role) {

        for(String roleInRoles : roles) {
            if(role.equals(roleInRoles)) {
                return true;
            }
        }
        return false;

    }

    public static Boolean verifyName(String name) {

        if(name == null) {
            return false;
        }
        if(!Character.isUpperCase(name.charAt(0))) {
            return false;
        }
        for(int i = 0; i < name.length() - 1; i++) {
            char currentChar = name.charAt(i);
            if(currentChar == ' ') {
                return false;
            }
            if(currentChar == '-' && !Character.isUpperCase(name.charAt(i + 1))) {
                return false;
            }
        }
        return true;

    }

    public static Boolean verifyCity(String city) {

        if(city == null) {
            return false;
        }
        if(!Character.isUpperCase(city.charAt(0))) {
            return false;
        }
        for(int i = 0; i < city.length() - 1; i++) {
            char currentChar = city.charAt(i);
            if((currentChar == '-' || currentChar == ' ') && !Character.isUpperCase(city.charAt(i + 1))) {
                return false;
            }
        }
        return true;

    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void readUsers() {

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

    public static void updateUsers() {

        Path filePath = Paths.get("C:/Users/usER/Desktop/Anul_II/Semestrul_I/MIP/ProiectFinal_MIP/src/main/resources/Users.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for(User user : users) {
                bw.write(user.getLastName());
                bw.newLine();
                bw.write(user.getFirstName());
                bw.newLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedBirthDate = dateFormat.format(user.getBirthDate());
                bw.write(formattedBirthDate);
                bw.newLine();
                bw.write(user.getCity());
                bw.newLine();
                bw.write(user.getPhoneNumber());
                if(!user.equals(users.get(users.size() - 1))) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        String role = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Administrator / administrator / Admin / admin");
            System.out.println("User / user");
            System.out.print("Introduce role: ");
            role = scanner.next();

            if (verifyRole(role)) {
                break;
            } else {
                System.out.println("Incorrect Role. Try Again.");
                System.out.println();
            }

        } while(true);

        System.out.println();

        if(role.equals("Administrator") || role.equals("administrator") || role.equals("Admin") || role.equals("admin")) {
            Administrator admin = new Administrator();
            while (true) {
                System.out.println("Admin Menu:");

                System.out.println();
                System.out.println("1. Create Food / Drink / Dessert;");
                System.out.println("2. Read Foods / Drinks / Desserts;");
                System.out.println("3. Delete chosen Food / Drink / Dessert;");
                System.out.println("4. Print Foods / Drinks / Desserts;");
                System.out.println("5. Filter Foods / Drinks / Desserts by chosen Initial Letter;");
                System.out.println("6. Sort Foods / Drinks / Desserts (Ascending / Descending);");
                System.out.println("7. Read Users;");
                System.out.println("8. Print Users;");
                System.out.println("9. Sort Users by BirthDate;");
                System.out.println("10. Sort Users by City;");
                System.out.println("0. Exit.");
                System.out.println();

                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.println("1. Create Food;");
                        System.out.println("2. Create Drink;");
                        System.out.println("3. Create Dessert.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                if(admin.getFoods().isEmpty()) {
                                    admin.readFoods();
                                }
                                admin.createFood();
                                break;
                            case 2:
                                if(admin.getDrinks().isEmpty()) {
                                    admin.readDrinks();
                                }
                                admin.createDrink();
                                break;
                            case 3:
                                if(admin.getDesserts().isEmpty()) {
                                    admin.readDesserts();
                                }
                                admin.createDessert();
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("1. Read Foods;");
                        System.out.println("2. Read Drinks;");
                        System.out.println("3. Read Desserts.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                admin.readFoods();
                                break;
                            case 2:
                                admin.readDrinks();
                                break;
                            case 3:
                                admin.readDesserts();
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 3:
                        System.out.println("1. Delete Food;");
                        System.out.println("2. Delete Drink;");
                        System.out.println("3. Delete Dessert.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                if(admin.getFoods().isEmpty()) {
                                    admin.readFoods();
                                }
                                System.out.print("Choose Index of Food: ");
                                int indexOfFood = scanner.nextInt();
                                admin.deleteFood(indexOfFood);
                                break;
                            case 2:
                                if(admin.getDrinks().isEmpty()) {
                                    admin.readDrinks();
                                }
                                System.out.print("Choose Index of Drink: ");
                                int indexOfDrink = scanner.nextInt();
                                admin.deleteDrink(indexOfDrink);
                                break;
                            case 3:
                                if(admin.getDesserts().isEmpty()) {
                                    admin.readDesserts();
                                }
                                System.out.print("Choose Index of Dessert: ");
                                int indexOfDessert = scanner.nextInt();
                                admin.deleteDessert(indexOfDessert);
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 4:
                        System.out.println("1. Print Foods;");
                        System.out.println("2. Print Drinks;");
                        System.out.println("3. Print Desserts.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                if(admin.getFoods().isEmpty()) {
                                    admin.readFoods();
                                }
                                System.out.println();
                                admin.printFoods();
                                break;
                            case 2:
                                if(admin.getDrinks().isEmpty()) {
                                    admin.readDrinks();
                                }
                                System.out.println();
                                admin.printDrinks();
                                break;
                            case 3:
                                if(admin.getDesserts().isEmpty()) {
                                    admin.readDesserts();
                                }
                                System.out.println();
                                admin.printDesserts();
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 5:
                        System.out.println("1. Filter Foods;");
                        System.out.println("2. Filter Drinks;");
                        System.out.println("3. Filter Desserts.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        char initialLetter;

                        switch (choice) {
                            case 1:
                                if(admin.getFoods().isEmpty()) {
                                    admin.readFoods();
                                }
                                System.out.print("Choose Initial Letter: ");
                                initialLetter = scanner.next().charAt(0);
                                if(!Character.isLetter(initialLetter)) {
                                    do {
                                        System.out.print("Choose Initial Letter Again: ");
                                        initialLetter = scanner.next().charAt(0);
                                    } while(!Character.isLetter(initialLetter));
                                }
                                System.out.println();
                                admin.filterFoodsByInitialLetter(initialLetter);
                                break;
                            case 2:
                                if(admin.getDrinks().isEmpty()) {
                                    admin.readDrinks();
                                }
                                System.out.print("Choose Initial Letter: ");
                                initialLetter = scanner.next().charAt(0);
                                if(!Character.isLetter(initialLetter)) {
                                    do {
                                        System.out.print("Choose Initial Letter Again: ");
                                        initialLetter = scanner.next().charAt(0);
                                    } while(!Character.isLetter(initialLetter));
                                }
                                System.out.println();
                                admin.filterDrinksByInitialLetter(initialLetter);
                                break;
                            case 3:
                                if(admin.getDesserts().isEmpty()) {
                                    admin.readDesserts();
                                }
                                System.out.print("Choose Initial Letter: ");
                                initialLetter = scanner.next().charAt(0);
                                if(!Character.isLetter(initialLetter)) {
                                    do {
                                        System.out.print("Choose Initial Letter Again: ");
                                        initialLetter = scanner.next().charAt(0);
                                    } while(!Character.isLetter(initialLetter));
                                }
                                System.out.println();
                                admin.filterDessertsByInitialLetter(initialLetter);
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 6:
                        System.out.println("1. Sort Foods;");
                        System.out.println("2. Sort Drinks;");
                        System.out.println("3. Sort Desserts");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                System.out.println("1. Sort Foods by Name Ascending");
                                System.out.println("2. Sort Foods by Name Descending");
                                System.out.println("3. Sort Foods by Price Ascending");
                                System.out.println("4. Sort Foods by Price Descending");
                                System.out.println("5. Sort Foods by Amount Ascending");
                                System.out.println("6. Sort Foods by Amount Descending");
                                System.out.println();

                                System.out.print("Select an option: ");
                                choice = scanner.nextInt();
                                System.out.println();

                                switch (choice) {
                                    case 1:
                                        if(admin.getFoods().isEmpty()) {
                                            admin.readFoods();
                                        }
                                        System.out.println();
                                        admin.sortFoodsByNameAscending();
                                        break;
                                    case 2:
                                        if(admin.getFoods().isEmpty()) {
                                            admin.readFoods();
                                        }
                                        System.out.println();
                                        admin.sortFoodsByNameDescending();
                                        break;
                                    case 3:
                                        if(admin.getFoods().isEmpty()) {
                                            admin.readFoods();
                                        }
                                        System.out.println();
                                        admin.sortFoodsByPriceAscending();
                                        break;
                                    case 4:
                                        if(admin.getFoods().isEmpty()) {
                                            admin.readFoods();
                                        }
                                        System.out.println();
                                        admin.sortFoodsByPriceDescending();
                                        break;
                                    case 5:
                                        if(admin.getFoods().isEmpty()) {
                                            admin.readFoods();
                                        }
                                        System.out.println();
                                        admin.sortFoodsByAmountAscending();
                                        break;
                                    case 6:
                                        if(admin.getFoods().isEmpty()) {
                                            admin.readFoods();
                                        }
                                        System.out.println();
                                        admin.sortFoodsByAmountDescending();
                                        break;
                                    default:
                                        System.out.println("Invalid option. Try again.");
                                        System.out.println();
                                }
                                break;
                            case 2:
                                System.out.println("1. Sort Drinks by Name Ascending");
                                System.out.println("2. Sort Drinks by Name Descending");
                                System.out.println("3. Sort Drinks by Price Ascending");
                                System.out.println("4. Sort Drinks by Price Descending");
                                System.out.println("5. Sort Drinks by Content Ascending");
                                System.out.println("6. Sort Drinks by Content Descending");
                                System.out.println();

                                System.out.print("Select an option: ");
                                choice = scanner.nextInt();
                                System.out.println();

                                switch (choice) {
                                    case 1:
                                        if(admin.getDrinks().isEmpty()) {
                                            admin.readDrinks();
                                        }
                                        System.out.println();
                                        admin.sortDrinksByNameAscending();
                                        break;
                                    case 2:
                                        if(admin.getDrinks().isEmpty()) {
                                            admin.readDrinks();
                                        }
                                        System.out.println();
                                        admin.sortDrinksByNameDescending();
                                        break;
                                    case 3:
                                        if(admin.getDrinks().isEmpty()) {
                                            admin.readDrinks();
                                        }
                                        System.out.println();
                                        admin.sortDrinksByPriceAscending();
                                        break;
                                    case 4:
                                        if(admin.getDrinks().isEmpty()) {
                                            admin.readDrinks();
                                        }
                                        System.out.println();
                                        admin.sortDrinksByPriceDescending();
                                        break;
                                    case 5:
                                        if(admin.getDrinks().isEmpty()) {
                                            admin.readDrinks();
                                        }
                                        System.out.println();
                                        admin.sortDrinksByContentAscending();
                                        break;
                                    case 6:
                                        if(admin.getDrinks().isEmpty()) {
                                            admin.readDrinks();
                                        }
                                        System.out.println();
                                        admin.sortDrinksByContentDescending();
                                        break;
                                    default:
                                        System.out.println("Invalid option. Try again.");
                                        System.out.println();
                                }
                                break;
                            case 3:
                                System.out.println("1. Sort Desserts by Name Ascending");
                                System.out.println("2. Sort Desserts by Name Descending");
                                System.out.println("3. Sort Desserts by Price Ascending");
                                System.out.println("4. Sort Desserts by Price Descending");
                                System.out.println("5. Sort Desserts by Amount Ascending");
                                System.out.println("6. Sort Desserts by Amount Descending");
                                System.out.println();

                                System.out.print("Select an option: ");
                                choice = scanner.nextInt();
                                System.out.println();

                                switch (choice) {
                                    case 1:
                                        if(admin.getDesserts().isEmpty()) {
                                            admin.readDesserts();
                                        }
                                        System.out.println();
                                        admin.sortDessertsByNameAscending();
                                        break;
                                    case 2:
                                        if(admin.getDesserts().isEmpty()) {
                                            admin.readDesserts();
                                        }
                                        System.out.println();
                                        admin.sortDessertsByNameDescending();
                                        break;
                                    case 3:
                                        if(admin.getDesserts().isEmpty()) {
                                            admin.readDesserts();
                                        }
                                        System.out.println();
                                        admin.sortDessertsByPriceAscending();
                                        break;
                                    case 4:
                                        if(admin.getDesserts().isEmpty()) {
                                            admin.readDesserts();
                                        }
                                        System.out.println();
                                        admin.sortDessertsByPriceDescending();
                                        break;
                                    case 5:
                                        if(admin.getDesserts().isEmpty()) {
                                            admin.readDesserts();
                                        }
                                        System.out.println();
                                        admin.sortDessertsByAmountAscending();
                                        break;
                                    case 6:
                                        if(admin.getDesserts().isEmpty()) {
                                            admin.readDesserts();
                                        }
                                        System.out.println();
                                        admin.sortDessertsByAmountDescending();
                                        break;
                                    default:
                                        System.out.println("Invalid option. Try again.");
                                        System.out.println();
                                }
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 7:
                        admin.readUsers();
                        break;
                    case 8:
                        if(admin.getUsers().isEmpty()) {
                            admin.readUsers();
                        }
                        admin.printUsers();
                        break;
                    case 9:
                        if(admin.getUsers().isEmpty()) {
                            admin.readUsers();
                        }
                        admin.sortUsersByBirthDate();
                        break;
                    case 10:
                        if(admin.getUsers().isEmpty()) {
                            admin.readUsers();
                        }
                        admin.sortUsersByCity();
                        break;
                    case 0:
                        System.out.println();
                        System.out.println("Exit App.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again.");
                        System.out.println();
                }
            }
        } else {
            System.out.print("Last Name (it has to start with upper): ");
            String lastName = scanner.next();
            scanner.nextLine();
            if(!verifyName(lastName)) {
                do {
                    System.out.print("Introduce Last Name Again (it has to start with upper):");
                    lastName = scanner.nextLine();
                } while(!verifyName(lastName));
            }

            System.out.print("First Name (it has to start with upper): ");
            String firstName = scanner.next();
            scanner.nextLine();
            if(!verifyName(lastName)) {
                do {
                    System.out.print("Introduce First Name Again (it has to start with upper):");
                    firstName = scanner.nextLine();
                } while(!verifyName(lastName));
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            Date birthDate = null;
            do {
                System.out.print("Birth Date (dd/MM/yyyy): ");
                String birthDateString = scanner.nextLine();
                try {
                    birthDate = dateFormat.parse(birthDateString);
                } catch (ParseException e) {
                    System.out.println("Enter Birth Date Again (dd/MM/yyyy)");
                }
            } while(birthDate == null);

            System.out.print("City: ");
            String city = scanner.nextLine();
            if(!verifyCity(city)) {
                do {
                    System.out.print("Introduce City Again (it has to start with upper):");
                    city = scanner.nextLine();
                } while(!verifyCity(city));
            }

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            if(!phoneNumber.matches("^[0-9]+$")) {
                do {
                    System.out.print("Introduce Phone Number Again:");
                    phoneNumber = scanner.nextLine();
                } while(!phoneNumber.matches("^[0-9]]+$"));
            }

            readUsers();
            User user = new User(lastName, firstName, birthDate, city, phoneNumber);
            users.add(user);
            updateUsers();
            System.out.println();

            while (true) {
                System.out.println("User Menu:");

                System.out.println("1. Read Foods / Drinks / Desserts;");
                System.out.println("2. Print Foods / Drinks / Desserts;");
                System.out.println("3. Filter Foods / Drinks / Desserts by chosen Initial Letter;");
                System.out.println("4. Sort Foods / Drinks / Desserts (Ascending / Descending);");
                System.out.println("0. Exit.");
                System.out.println();

                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.println("1. Read Foods;");
                        System.out.println("2. Read Drinks;");
                        System.out.println("3. Read Desserts.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                user.readFoods();
                                break;
                            case 2:
                                user.readDrinks();
                                break;
                            case 3:
                                user.readDesserts();
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("1. Print Foods;");
                        System.out.println("2. Print Drinks;");
                        System.out.println("3. Print Desserts.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                if(user.getFoods().isEmpty()) {
                                    user.readFoods();
                                }
                                user.printFoods();
                                break;
                            case 2:
                                if(user.getDrinks().isEmpty()) {
                                    user.readDrinks();
                                }
                                user.printDrinks();
                                break;
                            case 3:
                                if(user.getDesserts().isEmpty()) {
                                    user.readDesserts();
                                }
                                user.printDesserts();
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 3:
                        System.out.println("1. Filter Foods;");
                        System.out.println("2. Filter Drinks;");
                        System.out.println("3. Filter Desserts.");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        char initialLetter;

                        switch (choice) {
                            case 1:
                                if(user.getFoods().isEmpty()) {
                                    user.readFoods();
                                }
                                System.out.print("Choose Initial Letter: ");
                                initialLetter = scanner.next().charAt(0);
                                if(!Character.isLetter(initialLetter)) {
                                    do {
                                        System.out.print("Choose Initial Letter Again: ");
                                        initialLetter = scanner.next().charAt(0);
                                    } while(!Character.isLetter(initialLetter));
                                }
                                System.out.println();
                                user.filterFoodsByInitialLetter(initialLetter);
                                break;
                            case 2:
                                if(user.getDrinks().isEmpty()) {
                                    user.readDrinks();
                                }
                                System.out.print("Choose Initial Letter: ");
                                initialLetter = scanner.next().charAt(0);
                                if(!Character.isLetter(initialLetter)) {
                                    do {
                                        System.out.print("Choose Initial Letter Again: ");
                                        initialLetter = scanner.next().charAt(0);
                                    } while(!Character.isLetter(initialLetter));
                                }
                                System.out.println();
                                user.filterDrinksByInitialLetter(initialLetter);
                                break;
                            case 3:
                                if(user.getDesserts().isEmpty()) {
                                    user.readDesserts();
                                }
                                System.out.print("Choose Initial Letter: ");
                                initialLetter = scanner.next().charAt(0);
                                if(!Character.isLetter(initialLetter)) {
                                    do {
                                        System.out.print("Choose Initial Letter Again: ");
                                        initialLetter = scanner.next().charAt(0);
                                    } while(!Character.isLetter(initialLetter));
                                }
                                System.out.println();
                                user.filterDessertsByInitialLetter(initialLetter);
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 4:
                        System.out.println("1. Sort Foods;");
                        System.out.println("2. Sort Drinks;");
                        System.out.println("3. Sort Desserts");
                        System.out.println();

                        System.out.print("Select an option: ");
                        choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1:
                                System.out.println("1. Sort Foods by Name Ascending");
                                System.out.println("2. Sort Foods by Name Descending");
                                System.out.println("3. Sort Foods by Price Ascending");
                                System.out.println("4. Sort Foods by Price Descending");
                                System.out.println("5. Sort Foods by Amount Ascending");
                                System.out.println("6. Sort Foods by Amount Descending");
                                System.out.println();

                                System.out.print("Select an option: ");
                                choice = scanner.nextInt();
                                System.out.println();

                                switch (choice) {
                                    case 1:
                                        if(user.getFoods().isEmpty()) {
                                            user.readFoods();
                                        }
                                        System.out.println();
                                        user.sortFoodsByNameAscending();
                                        break;
                                    case 2:
                                        if(user.getFoods().isEmpty()) {
                                            user.readFoods();
                                        }
                                        System.out.println();
                                        user.sortFoodsByNameDescending();
                                        break;
                                    case 3:
                                        if(user.getFoods().isEmpty()) {
                                            user.readFoods();
                                        }
                                        System.out.println();
                                        user.sortFoodsByPriceAscending();
                                        break;
                                    case 4:
                                        if(user.getFoods().isEmpty()) {
                                            user.readFoods();
                                        }
                                        System.out.println();
                                        user.sortFoodsByPriceDescending();
                                        break;
                                    case 5:
                                        if(user.getFoods().isEmpty()) {
                                            user.readFoods();
                                        }
                                        System.out.println();
                                        user.sortFoodsByAmountAscending();
                                        break;
                                    case 6:
                                        if(user.getFoods().isEmpty()) {
                                            user.readFoods();
                                        }
                                        System.out.println();
                                        user.sortFoodsByAmountDescending();
                                        break;
                                    default:
                                        System.out.println("Invalid option. Try again.");
                                        System.out.println();
                                }
                                break;
                            case 2:
                                System.out.println("1. Sort Drinks by Name Ascending");
                                System.out.println("2. Sort Drinks by Name Descending");
                                System.out.println("3. Sort Drinks by Price Ascending");
                                System.out.println("4. Sort Drinks by Price Descending");
                                System.out.println("5. Sort Drinks by Content Ascending");
                                System.out.println("6. Sort Drinks by Content Descending");
                                System.out.println();

                                System.out.print("Select an option: ");
                                choice = scanner.nextInt();
                                System.out.println();

                                switch (choice) {
                                    case 1:
                                        if(user.getDrinks().isEmpty()) {
                                            user.readDrinks();
                                        }
                                        System.out.println();
                                        user.sortDrinksByNameAscending();
                                        break;
                                    case 2:
                                        if(user.getDrinks().isEmpty()) {
                                            user.readDrinks();
                                        }
                                        System.out.println();
                                        user.sortDrinksByNameDescending();
                                        break;
                                    case 3:
                                        if(user.getDrinks().isEmpty()) {
                                            user.readDrinks();
                                        }
                                        System.out.println();
                                        user.sortDrinksByPriceAscending();
                                        break;
                                    case 4:
                                        if(user.getDrinks().isEmpty()) {
                                            user.readDrinks();
                                        }
                                        System.out.println();
                                        user.sortDrinksByPriceDescending();
                                        break;
                                    case 5:
                                        if(user.getDrinks().isEmpty()) {
                                            user.readDrinks();
                                        }
                                        System.out.println();
                                        user.sortDrinksByContentAscending();
                                        break;
                                    case 6:
                                        if(user.getDrinks().isEmpty()) {
                                            user.readDrinks();
                                        }
                                        System.out.println();
                                        user.sortDrinksByContentDescending();
                                        break;
                                    default:
                                        System.out.println("Invalid option. Try again.");
                                        System.out.println();
                                }
                                break;
                            case 3:
                                System.out.println("1. Sort Desserts by Name Ascending");
                                System.out.println("2. Sort Desserts by Name Descending");
                                System.out.println("3. Sort Desserts by Price Ascending");
                                System.out.println("4. Sort Desserts by Price Descending");
                                System.out.println("5. Sort Desserts by Amount Ascending");
                                System.out.println("6. Sort Desserts by Amount Descending");
                                System.out.println();

                                System.out.print("Select an option: ");
                                choice = scanner.nextInt();
                                System.out.println();

                                switch (choice) {
                                    case 1:
                                        if(user.getDesserts().isEmpty()) {
                                            user.readDesserts();
                                        }
                                        System.out.println();
                                        user.sortDessertsByNameAscending();
                                        break;
                                    case 2:
                                        if(user.getDesserts().isEmpty()) {
                                            user.readDesserts();
                                        }
                                        System.out.println();
                                        user.sortDessertsByNameDescending();
                                        break;
                                    case 3:
                                        if(user.getDesserts().isEmpty()) {
                                            user.readDesserts();
                                        }
                                        System.out.println();
                                        user.sortDessertsByPriceAscending();
                                        break;
                                    case 4:
                                        if(user.getDesserts().isEmpty()) {
                                            user.readDesserts();
                                        }
                                        System.out.println();
                                        user.sortDessertsByPriceDescending();
                                        break;
                                    case 5:
                                        if(user.getDesserts().isEmpty()) {
                                            user.readDesserts();
                                        }
                                        System.out.println();
                                        user.sortDessertsByAmountAscending();
                                        break;
                                    case 6:
                                        if(user.getDesserts().isEmpty()) {
                                            user.readDesserts();
                                        }
                                        System.out.println();
                                        user.sortDessertsByAmountDescending();
                                        break;
                                    default:
                                        System.out.println("Invalid option. Try again.");
                                        System.out.println();
                                }
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                System.out.println();
                        }
                        break;
                    case 0:
                        System.out.println("Exit User Mode.");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again.");
                        System.out.println();
                }
            }
        }

    }

}