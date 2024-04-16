package org.example;

public class Drink {

    private String name;
    private Integer content;
    private Double price;

    public Drink(String name, Integer content, Double price) {

        this.name = name;
        this.content = content;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void printDrink() {

        System.out.println(name);
        System.out.println(content);
        System.out.println(price);
        System.out.println();

    }

}