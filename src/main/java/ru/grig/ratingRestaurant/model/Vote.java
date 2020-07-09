package ru.grig.ratingRestaurant.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:user_id"),
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.GET_ALL_BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:user_id"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:user_id"),
        @NamedQuery(name = Vote.GET_BY_DATE_NOW, query = "SELECT v FROM Vote v WHERE v.voteDate=:date AND v.user.id=:user_id"),
})

@Entity

@NamedEntityGraphs({
        @NamedEntityGraph(name = "vote-only-entity-graph"),
        @NamedEntityGraph(name = "vote-user-restaurant-entity-graph",
                attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("restaurant")
                })
})

@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String GET_ALL = "Vote.getAll";
    public static final String GET_ALL_BY_USER = "Vote.getAllByUser";
    public static final String GET = "Vote.get";
    public static final String GET_BY_DATE_NOW = "Vote.getByDateNow";
//    private Long id;
//    @Column(name = "id_user", nullable = false)
//    @NotNull
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private int idUser;
//
//    @Column(name = "id_restaurant", nullable = false)
//    @NotNull
//    private int idRestaurant;

    @Column(name = "vote_date_time", nullable = false)
    @NotNull
    private LocalDateTime voteDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurant", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Vote() {}

    public Vote(Integer id, LocalDateTime date) {
        super(id);
        this.voteDate = date.toLocalDate().atTime(00, 00);
    }

    public Vote(User user, Restaurant restaurant) {
        super(null);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDate = LocalDate.now().atTime(00, 00);
    }

    public Vote(Integer id, User user, Restaurant restaurant) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDate = LocalDate.now().atTime(00, 00);
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime date) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDate = date.toLocalDate().atTime(00, 00);
    }

    public Vote(Integer id, Restaurant restaurant, LocalDateTime date) {
        super(id);
        this.restaurant = restaurant;
        this.voteDate = date.toLocalDate().atTime(00, 00);
    }

//    Вставка нового голоса для User в Date.now()
    public Vote(int id) {
        super(id);
        this.voteDate = LocalDate.now().atTime(00, 00);
    }

    public Vote(User user, Restaurant restaurant, LocalDateTime date) {
        this(null, user, restaurant, date.toLocalDate().atTime(00, 00));
    }

    public void setVoteDate(LocalDateTime voteDate) {
        this.voteDate = voteDate;
    }

    public LocalDateTime getVoteDate()  {   return voteDate; }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {    return voteDate.toLocalDate();  }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", voteDate=" + voteDate +
                '}';
    }
}
