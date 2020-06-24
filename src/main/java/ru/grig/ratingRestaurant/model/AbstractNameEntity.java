package ru.grig.ratingRestaurant.model;

public abstract class AbstractNameEntity extends AbstractBaseEntity {

    protected String name;

    protected AbstractNameEntity() {}

    protected AbstractNameEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
