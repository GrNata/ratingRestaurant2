package ru.grig.ratingRestaurant.model;

public class Restaurant extends AbstractNameEntity{
//    private Long id;
//    private String name;
    private int menu;   //  количество блюд
    private int rating;

    public Restaurant(){}

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getMenu(), r.getRating());
    }

    public Restaurant(String name, int menu, int rating) {
        this(null, name, menu, rating);
    }

    public Restaurant(Long id, String name, int menu, int rating) {
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
