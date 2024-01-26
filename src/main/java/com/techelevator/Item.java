package com.techelevator;

public abstract class Item {

    private String slotId;
    private String name;
    private Double price;
    private String type;
    private int qty;
    private String message;

    public Item(String slotId, String name, Double price, String type, int qty) {
        this.slotId = slotId;
        this.name = name;
        this.price = price;
        this.type = type;
        this.qty = qty;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
