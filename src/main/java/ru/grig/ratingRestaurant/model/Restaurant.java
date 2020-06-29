package ru.grig.ratingRestaurant.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.getALL, query = "SELECT r FROM Restaurant r"),
})

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name", "menu", "rating"})})
public class Restaurant extends AbstractNameEntity{
    public static final String DELETE = "Restaurant.delete";
    public static final String getALL = "Restaurant.getAll";
//    private Long id;
//    private String name;
    @Column(name = "menu", nullable = false)
    @NotNull
//    @Size(min = 2, max = 150)
    private int menu;   //  количество блюд

    @Column(name = "rating", nullable = false)
    @NotNull
    private int rating;

    public Restaurant(){}

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getMenu(), r.getRating());
    }

    public Restaurant(String name, int menu, int rating) {
        this(null, name, menu, rating);
    }

    public Restaurant(Integer id, String name, int menu, int rating) {
        super(id, name);
        this.menu = menu;
        this.rating = rating;
    }


    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", restaurantName='" + name + '\'' +
                ", menu=" + menu +
                ", rating=" + rating +
                '}';
    }
}
