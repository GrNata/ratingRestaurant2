package ru.grig.ratingRestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@NamedQueries({
    @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
    @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email=?1"),
    @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.role ORDER BY u.name, u.email"),
})

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNameEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_EMAIL = "User.byEmail";
    public static final String ALL_SORTED = "User.getAllSorted";
//    private Long id;
//    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

//    @Column(name = "enable", nullable = false)
//    private boolean enable;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    public User() { }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.getRole());
//        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.getRole());

    }

    public User(Integer id, String name, String email, String password,
//                LocalDate registered, Set<Role> roles) {
                Date registered, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
//        this.enable = enable;
        this.role = roles;
    }

        public User(Integer id, String name, String email, String password,
//                LocalDate registered, Role role, Role... roles) {
                Date registered, Role role, Role... roles) {
//        this(id, name, email, password, registered, EnumSet.of(role, roles));
        this(id, name, email, password, registered, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, new Date(), EnumSet.of(role, roles));
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

//    public boolean isEnable() {
//        return enable;
//    }
//
//    public void setEnable(boolean enable) {
//        this.enable = enable;
//    }

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
//                ", enable=" + enable +
                ", role=" + role +
                '}';
    }
}
