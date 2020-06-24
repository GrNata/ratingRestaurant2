package ru.grig.ratingRestaurant.model;

import java.time.LocalDate;

public class Rating extends AbstractBaseEntity {
//    private Long id;
    private long idRestaurant;
    private int countVote;
    private LocalDate dateVote;

    public Rating() {}

    public Rating(Rating r) {
        this(r.getId(), r.getIdRestaurant(), r.getCountVote(), r.getDateVote());
    }

    public Rating(long idRestaurant, int countVote, LocalDate dateVote) {
        this(null, idRestaurant, countVote, dateVote);
//        this.idRestaurant = idRestaurant;
//        this.countVote = countVote;
//        this.dateVote = dateVote;
    }

    public Rating(Long id, long idRestaurant, int countVote, LocalDate dateVote) {
        super(id);
//        this.id = id;
        this.idRestaurant = idRestaurant;
        this.countVote = countVote;
        this.dateVote = dateVote;
    }

//    public boolean isNew() {
//        return id == null;
//    }

    public boolean isNewDate() {
        return dateVote == null;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

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

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", idRestaurant=" + idRestaurant +
                ", countVote=" + countVote +
                ", dateVote=" + dateVote +
                '}';
    }
}
