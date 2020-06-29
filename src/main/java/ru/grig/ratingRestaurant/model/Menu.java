package ru.grig.ratingRestaurant.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id" +
                " AND m.restaurant.id=:id_restaurant"),
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m from Menu m"),
        @NamedQuery(name = Menu.GET_ALL_BY_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.restaurant.id=:id_restaurant"),
        @NamedQuery(name = Menu.GET, query = "SELECT m FROM Menu m WHERE m.id=:id"),
})

@Entity
@Table(name = "menu", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "dishes", "price", "id_restaurant"}))
public class Menu extends AbstractBaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String GET_ALL = "Menu.getAll";
    public static final String GET_ALL_BY_RESTAURANT = "Menu.getAllByRestaurant";
    public static final String GET = "Menu.get";

//    private Long id;
//    @Column(name = "id_restaurant", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_restaurant")
//    private long idRestaurant;

    @Column(name = "dishes", nullable = false)
//    @Range(min = 2, max = 150)
    @NotNull
    private String dishes;

    @Column(name = "price", nullable = false)
    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;


    public Menu() {}

//    public Menu(Menu m) {
//        this(m.getId(), m.getIdRestaurant(), m.getDishes(), m.getPrice());
//    }
    public Menu(Menu m) {
        this(m.getId(), m.getDishes(), m.getPrice());
    }

//    public Menu(long idRestaurant, String dishes, int price) {
//        this(null, idRestaurant, dishes, price);
//    }
    public Menu(String dishes, int price) {
        this(null, dishes, price);
    }

    public Menu(Integer id, String dishes, int price) {
//    public Menu(Long id, long idRestaurant, String dishes, int price) {
        super(id);
//        this.idRestaurant = idRestaurant;
        this.dishes = dishes;
        this.price = price;
    }

    public Menu(Integer id, Restaurant restaurant, String dishes, int price) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.price = price;
    }

//    public long getIdRestaurant() {
//        return idRestaurant;
//    }
//
//    public void setIdRestaurant(long idRestaurant) {
//        this.idRestaurant = idRestaurant;
//    }
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
//                "idRestaurant=" + idRestaurant +
                ", dishes='" + dishes + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
