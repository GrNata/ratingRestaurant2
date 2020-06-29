package ru.grig.ratingRestaurant.with_restaurant;

public class RestaurantWithRating {
    private Integer id;
    private String name;
    private int menu;
    private int rating;

    public RestaurantWithRating() {}

    public RestaurantWithRating(Integer id, String name, int menu, int rating) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "RestaurantRating{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", rating=" + rating +
                '}';
    }
}
