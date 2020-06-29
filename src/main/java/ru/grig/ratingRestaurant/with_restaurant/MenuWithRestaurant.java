package ru.grig.ratingRestaurant.with_restaurant;

public class MenuWithRestaurant {
    private Integer id;
    private int idRestaurant;
    private String dishes;
    private int price;
    private String nameRestaurant;

    public MenuWithRestaurant(int idRestaurant, String dishes, int price, String nameRestaurant) {
        this(null, idRestaurant, dishes, price, nameRestaurant);
    }

    public MenuWithRestaurant(Integer id, int idRestaurant, String dishes, int price, String nameRestaurant) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.dishes = dishes;
        this.price = price;
        this.nameRestaurant = nameRestaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    @Override
    public String toString() {
        return "MenuWithRestaurant{" +
                "id=" + id +
                ", idRestaurant=" + idRestaurant +
                ", dishes='" + dishes + '\'' +
                ", price=" + price +
                ", nameRestaurant='" + nameRestaurant + '\'' +
                '}';
    }
}
