package fi.solita.devacademy2021.model;

public class Name {
    private final String name;
    private final int amount;

    public Name(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }
}
