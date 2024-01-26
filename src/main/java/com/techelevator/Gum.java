package com.techelevator;

public class Gum extends Item{
    public Gum(String slotId, String name, Double price, String type, int qty) {
        super(slotId, name, price, type, qty);
        setMessage("Chew Chew, Yum!");
    }

}
