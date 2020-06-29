package ru.grig.ratingRestaurant.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Rating.DELETE, query = "DELETE FROM Rating r where r.id=:id"),
        @NamedQuery(name = Rating.GET_ALL, query = "SELECT r from Rating r"),
        @NamedQuery(name = Rating.GET_ALL_BY_RESTAURANT, query = "SELECT r FROM " +
                "Rating r WHERE r.restaurant.id=:id_restaurant"),
        @NamedQuery(name = Rating.GET_BY_RESTAURANT_BY_DATE,
        query = "SELECT r FROM Rating r WHERE r.restaurant.id=:id_restaurant AND r.dateVote=:date_vota"),
})

@Entity
@Table(name = "rating")
public class Rating extends AbstractBaseEntity {

    public static final String DELETE = "Rating.delete";
    public static final String GET_ALL = "Rating.getAll";
    public static final String GET_ALL_BY_RESTAURANT = "Rating.getAllByRestaurant";
    public static final String GET_BY_RESTAURANT_BY_DATE = "Rating.getByRestaurantByDate";

//public class Rating {
//    private Long id;
//    private Integer idRestaurant;
    @Column(name = "count_vote", nullable = false)
    @NotNull
    private int countVote;

    @Column(name = "date_vote", nullable = false)
    @NotNull
    private LocalDate dateVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Rating() {}

//    public Rating(Rating r) {
//        this(r.getId(), r.getIdRestaurant(), r.getCountVote(), r.getDateVote());
//    }
    public Rating(Rating r) {
        this(r.getId(), r.getCountVote(), r.getDateVote());
    }
//    public Rating(int countVote, LocalDate dateVote) {
//        this(null, countVote, dateVote);
//    }

//    public Rating(int idRestaurant, int countVote, LocalDate dateVote) {
//        this(null, idRestaurant, countVote, dateVote);
//    }
//    public Rating(int countVote, LocalDate dateVote) {
//        this(null, countVote, dateVote);
//    }

    public Rating(Restaurant restaurant, int countVote, LocalDate dateVote) {
        this(null, restaurant, countVote, dateVote);
    }

//    public Rating(Integer id, int idRestaurant, int countVote, LocalDate dateVote) {
    public Rating(Integer id, int countVote, LocalDate dateVote) {
        super(id);
//        this.idRestaurant = idRestaurant;
        this.countVote = countVote;
        this.dateVote = dateVote;
    }

    public Rating(Integer id, Restaurant restaurant, int countVote, LocalDate dateVote) {
        super(id);
        this.restaurant = restaurant;
        this.countVote = countVote;
        this.dateVote = dateVote;
    }

//    public boolean isNew() {
//        return idRestaurant != null && dateVote;
//    }

//    public boolean isNewDate() {
//        return dateVote == null;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public int getIdRestaurant() {
//        return idRestaurant;
//    }
//
//    public void setIdRestaurant(int idRestaurant) {
//        this.idRestaurant = idRestaurant;
//    }

    public int getCountVote() {
        return countVote;
    }

    public void setCountVote(int countVote) {
        this.countVote = countVote;
    }

    public LocalDate getDateVote() {
        return dateVote;
    }

    public void setDateVote(LocalDate dateVote) {
        this.dateVote = dateVote;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
//                ", idRestaurant=" + idRestaurant +
                ", countVote=" + countVote +
                ", dateVote=" + dateVote +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        AbstractBaseEntity that = (AbstractBaseEntity) o;
////        return id != null && id.equals(that.id);
//        return idRestaurant != null && idRestaurant.equals(this.idRestaurant)
//                && dateVote != null && dateVote.equals(this.dateVote);
//    }
//
////    @Override
//    public int hashCode() {
//        int idInt = Integer.parseInt(idRestaurant.toString());
//        return idRestaurant == null ? 0 : idInt;
//    }
}
