package com.techelevator;

public class Drink extends Item{
    public Drink(String slotId, String name, Double price, String type, int qty) {
        super(slotId, name, price, type, qty);
        setMessage("Glug Glug, Yum!");
    }

}
