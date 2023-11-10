package christmas.constants.dishes;

import java.util.Arrays;
import java.util.List;

public enum Dessert {
    CHOCO_CAKE(15_000, "15,000원", "초코케이크"),
    ICE_CREAM(5_000, "5,000원", "아이스크림");

    private final int price;
    private final String priceToWon;
    private final String name;

    Dessert(int price, String priceToWon, String name){
        this.price = price;
        this.priceToWon = priceToWon;
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public String getPriceToWon(){
        return priceToWon;
    }

    public String getName(){
        return name;
    }

    public static List<Dessert> getDesserts(){
        return Arrays.stream(Dessert.values()).toList();
    }
}
