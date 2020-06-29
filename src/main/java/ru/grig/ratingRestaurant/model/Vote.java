package ru.grig.ratingRestaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.idUser=:user_id"),
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.GET_ALL_BY_USER, query = "SELECT v FROM Vote v WHERE v.idUser=:user_id"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:id AND v.idUser=:user_id"),
})

@Entity
@Table(name = "vote")
public class Vote extends AbstractBaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String GET_ALL = "Vote.getAll";
    public static final String GET_ALL_BY_USER = "Vote.getAllByUser";
    public static final String GET = "Vote.get";
//    private Long id;
    @Column(name = "id_user", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int idUser;

    @Column(name = "id_restaurant", nullable = false)
    @NotNull
    private int idRestaurant;

    @Column(name = "vote_date_time", nullable = false)
    @NotNull
    private LocalDate voteDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_user", nullable = false)
//    @NotNull
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_restaurant", nullable = false)
//    @NotNull
//    private Restaurant restaurant;

    public Vote() {}

    public Vote(Vote v) {
        this(v.getId(), v.getIdUser(), v.getIdRestaurant());
//        this(v.getId(), v.getUser(), v.getRestaurant());
    }

//    public Vote(Vote v) {
//        this(v.getId());
//    }

    public Vote(Integer id, int userId, int restId) {
        super(id);
        this.idUser = userId;
        this.idRestaurant = restId;
    }

    public Vote(Integer id, int userId, int restId, LocalDate date) {
        super(id);
        this.idUser = userId;
        this.idRestaurant = restId;
        this.voteDate = date;
    }

//    public Vote(Integer id, int idUser, int idRestaurant) {
    public Vote(Integer id) {
        super(id);
//        this.idUser = idUser;
//        this.idRestaurant = idRestaurant;
//        this.voteDate = LocalDate.now().atTime(00, 00);
        this.voteDate = LocalDate.now();
    }

//    public Vote(int idUser, int idRestaurant) {
//    public Vote() {
////        this(null, idUser, idRestaurant);
//        super(null);
//        this.voteDateTime = LocalDate.now().atTime(00, 00);
//    }

    public Vote(int idUser, int idRestaurant, LocalDate date) {
//    public Vote(LocalDate date) {
        this(null, idUser, idRestaurant);
//        super(null);
//        this.voteDateTime = date.atTime(00, 00);
        this.voteDate = date;
    }

//    public Vote(Integer id, int idUser, int idRestaurant, LocalDate date) {
    public Vote(Integer id, LocalDate date) {
        super(id);
//        this.idUser = idUser;
//        this.idRestaurant = idRestaurant;
//        this.voteDateTime = date.atTime(00, 00);
        this.voteDate = date;
    }

//    public boolean isNew()  {   return id == null;  }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    public LocalDate getVoteDate()  {   return voteDate; }

//    public LocalTime getVoteTime() {    return getVoteDateTime().toLocalTime(); }


//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idRestaurant=" + idRestaurant +
                ", voteDate=" + voteDate +
                '}';
    }
}
