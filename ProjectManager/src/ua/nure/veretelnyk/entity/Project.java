package ua.nure.veretelnyk.entity;

public class Project {
    Customer customer;
    String name;
    int price;
    String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
