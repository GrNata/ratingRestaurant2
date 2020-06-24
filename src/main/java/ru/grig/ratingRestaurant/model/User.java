package ru.grig.ratingRestaurant.model;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNameEntity {
//    private Long id;
//    private String name;
    private String email;
    private String password;
    private LocalDate registered;
    private boolean enable;
    private Set<Role> role;

    public User() { }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.isEnable(), u.getRole());

    }

    public User(Long id, String name, String email, String password,
                LocalDate registered, boolean enable, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enable = enable;
        this.role = roles;
    }

        public User(Long id, String name, String email, String password,
                LocalDate registered, boolean enable, Role role, Role... roles) {
        this(id, name, email, password, registered, enable, EnumSet.of(role, roles));
    }

//    public boolean isNew() {
//        return id == null;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", enable=" + enable +
                ", role=" + role +
                '}';
    }
}
