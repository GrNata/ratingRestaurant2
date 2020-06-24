package ru.grig.ratingRestaurant.model;

public class Menu extends AbstractBaseEntity {
//    private Long id;
    private long idRestaurant;
    private String dishes;
    private int price;


    public Menu() {}

    public Menu(Menu m) {
        this(m.getId(), m.getIdRestaurant(), m.getDishes(), m.getPrice());
    }

    public Menu(long idRestaurant, String dishes, int price) {
        this(null, idRestaurant, dishes, price);
    }

    public Menu(Long id, long idRestaurant, String dishes, int price) {
        super(id);
        this.idRestaurant = idRestaurant;
        this.dishes = dishes;
        this.price = price;
    }

    public long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
//
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

    @Override
    public String toString() {
        return "Menu{" +
                "idRestaurant=" + idRestaurant +
                ", dishes='" + dishes + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
