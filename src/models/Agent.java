package models;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by tomko on 27.3.2017.
 */

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "started_at")
    private Date started_at;

    @Column(name = "rating")
    private int rating;

    @OneToMany
    @JoinColumn(name = "agent_id")
    private Set<Sold_estate> sold_estates;

    @OneToMany
    @JoinColumn(name = "agent_id")
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

    public String getStarted_at() {
        return started_at.toString();
    }

    public void setStarted_at(String started_at) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.started_at = format.parse(started_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Sold_estate> getSold_estates() {
        return sold_estates;
    }

    public void setSold_estates(Set<Sold_estate> sold_estates) {
        this.sold_estates = sold_estates;
    }

    public Set<Open_house> getOpen_houses() {
        return open_houses;
    }

    public void setOpen_houses(Set<Open_house> open_houses) {
        this.open_houses = open_houses;
    }

    public String parseRating(int rating) {
        switch (rating) {
            case 1:
                return "amateur";
            case 2:
                return "satisfactory";
            case 3:
                return "good";
            case 4:
                return "persuasive";
            case 5:
                return "experienced";
            default:
                return "unknown rating";
        }
    }
}
