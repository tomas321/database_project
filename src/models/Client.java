package models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tomko on 27.3.2017.
 */
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany
    @JoinColumn(name = "client_id")
    private Set<Open_house> open_houses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Open_house> getOpen_houses() {
        return open_houses;
    }

    public void setOpen_houses(Set<Open_house> open_houses) {
        this.open_houses = open_houses;
    }
}
