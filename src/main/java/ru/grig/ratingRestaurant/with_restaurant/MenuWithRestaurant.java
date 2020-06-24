package ru.grig.ratingRestaurant.with_restaurant;

public class MenuWithRestaurant {
    private Long id;
    private long idRestaurant;
    private String dishes;
    private int price;
    private String nameRestaurant;

    public MenuWithRestaurant(long idRestaurant, String dishes, int price, String nameRestaurant) {
        this(null, idRestaurant, dishes, price, nameRestaurant);
    }

    public MenuWithRestaurant(Long id, long idRestaurant, String dishes, int price, String nameRestaurant) {
        this.id = id;
        this.idRestaurant = idRestaurant;
        this.dishes = dishes;
        this.price = price;
        this.nameRestaurant = nameRestaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(long idRestaurant) {
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
