package com.manyatkin.springrest2.model;

public class Item {
    private String vendorCode;
    private String name;
    private int cost;

    public Item() {
    }

    public Item(String vendorCode, String name, int cost) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.cost = cost;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{vendorCode = " + vendorCode + ", name = " + name + ", cost = " + cost + "}";
    }

    @Override
    public int hashCode() {
        return vendorCode.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Item item = (Item) o;

        return (vendorCode.equals(item.getVendorCode()) && name.equals(item.getName()) && cost == item.getCost());
    }
}