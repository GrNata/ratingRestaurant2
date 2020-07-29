package ru.grig.ratingRestaurant.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

//import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
//import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@MappedSuperclass
@Access(AccessType.FIELD)
//@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public abstract class AbstractBaseEntity {
    public static final int START_SEQ = 100000;
//    public static final int START_USER_SEQ = 100000;
//    public static final long START_REST_SEQ = 100000;
//    public static final long START_MENU_SEQ = 100000;
//    public static final long START_RATING_SEQ = 100000;
//    public static final long START_VOTE_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "global_seq")
    protected Integer id;

    protected AbstractBaseEntity() {}

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int idInt = Integer.parseInt(id.toString());
        return id == null ? 0 : idInt;
    }

//        @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
}
