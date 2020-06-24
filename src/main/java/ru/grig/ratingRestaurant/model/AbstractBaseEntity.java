package ru.grig.ratingRestaurant.model;

public abstract class AbstractBaseEntity {
//    public static final int START_USER_SEQ = 100000;
    public static final long START_REST_SEQ = 100000;
    public static final long START_MENU_SEQ = 100000;
    public static final long START_RATING_SEQ = 100000;
    public static final long START_VOTE_SEQ = 100000;

    protected Long id;

    protected AbstractBaseEntity() {}

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
