package com.techelevator;

public class Candy extends Item{
    public Candy(String slotId, String name, Double price, String type, int qty) {
        super(slotId, name, price, type, qty);
        setMessage("Yummy Yummy, So Sweet!");
    }

}
