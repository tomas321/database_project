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
@Table(name = "estates")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "arrangement_id")
    private Arrangement arrangement;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private int status;

    @Column(name = "category")
    private int category;

    @Column(name = "build_at")
    private Date build_at;

    @Column(name = "land")
    private Integer land;

    @Column(name = "price")
    private Double price;

    @OneToMany
    @JoinColumn(name = "estate_id")
    private Set<Open_house> open_houses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBuild_at() {
        return build_at.toString();
    }

    public void setBuild_at(String build_at) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.build_at = format.parse(build_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Integer getLand() {
        return land;
    }

    public void setLand(Integer land) {
        this.land = land;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Open_house> getOpen_houses() {
        return open_houses;
    }

    public void setOpen_houses(Set<Open_house> open_houses) {
        this.open_houses = open_houses;
    }

    public String parseCategory(int category) {
        switch (category) {
            case 1:
                return "house";
            case 2:
                return "flat";
            case 3:
                return "villa";
            default:
                return "unknown category";
        }
    }

    public String parseStatus(int status) {
        switch (status) {
            case 1:
                return "new";
            case 2:
                return "second hand";
            default:
                return "unknown category";
        }
    }
}
