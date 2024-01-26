package com.techelevator;

public class Munchy extends Item{
    public Munchy(String slotId, String name, Double price, String type, int qty) {
        super(slotId, name, price, type, qty);
        setMessage("Crunch Crunch, Yum!");
    }

}
