package ru.grig.ratingRestaurant.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//public class Vote extends AbstractBaseEntity {
public class Vote extends AbstractBaseEntity {
//    private Long id;
    private long idUser;
    private long idRestaurant;
    private LocalDateTime voteDateTime;

    public Vote() {}

    public Vote(Vote v) {
        this(v.getId(), v.getIdUser(), v.getIdRestaurant());
    }

    public Vote(Long id, long idUser, long idRestaurant) {
        super(id);
        this.idUser = idUser;
        this.idRestaurant = idRestaurant;
        this.voteDateTime = LocalDate.now().atTime(00, 00);
    }

    public Vote(long idUser, long idRestaurant) {
        this(null, idUser, idRestaurant);
        this.voteDateTime = LocalDate.now().atTime(00, 00);
    }

    public Vote(long idUser, long idRestaurant, LocalDate date) {
        this(null, idUser, idRestaurant);
        this.voteDateTime = date.atTime(00, 00);
    }

    public Vote(Long id, long idUser, long idRestaurant, LocalDate date) {
        super(id);
        this.idUser = idUser;
        this.idRestaurant = idRestaurant;
        this.voteDateTime = date.atTime(00, 00);
    }


//    public boolean isNew()  {   return id == null;  }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    public void setVoteDateTime(LocalDate voteDate) {
        this.voteDateTime = voteDate.atTime(00, 00);
    }


    public void setVoteDate(LocalDate voteDate) {
        this.voteDateTime = voteDate.atTime(00, 00);
    }

    public LocalDate getVoteDate()  {   return voteDateTime.toLocalDate(); }

//    public LocalTime getVoteTime() {    return getVoteDateTime().toLocalTime(); }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idRestaurant=" + idRestaurant +
                ", voteDateTime=" + voteDateTime +
                '}';
    }
}
