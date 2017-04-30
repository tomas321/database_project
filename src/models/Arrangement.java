package models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by tomko on 27.3.2017.
 */
@Entity
@Table(name = "arrangements")
public class Arrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "balcony")
    private boolean balcony;

    @Column(name = "furniture")
    private boolean furniture;

    @Column(name = "pool")
    private boolean pool;

    @Column(name = "garden")
    private boolean garden;

    @Column(name = "rooms")
    private Integer room_count;

    @Column(name = "toilets")
    private Integer toilet_count;

    @Column(name = "floors")
    private Integer floor_count;

    @OneToMany
    @JoinColumn(name = "arrangement_id")
    private Set<Estate> estates;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture = furniture;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public Integer getRoom_count() {
        return room_count;
    }

    public void setRoom_count(Integer room_count) {
        this.room_count = room_count;
    }

    public Integer getToilet_count() {
        return toilet_count;
    }

    public void setToilet_count(Integer toilet_count) {
        this.toilet_count = toilet_count;
    }

    public Integer getFloor_count() {
        return floor_count;
    }

    public void setFloor_count(Integer floor_count) {
        this.floor_count = floor_count;
    }

    public Set<Estate> getEstates() {
        return estates;
    }

    public void setEstates(Set<Estate> estates) {
        this.estates = estates;
    }

    public String getPool() {
        if (this.pool) return "pool";
        return "no pool";
    }

    public String getGarden() {
        if (this.garden) return "garden";
        return "no garden";
    }

    public String getBalcony() {
        if (this.balcony) return "balcony";
        return "no balcony";
    }

    public String getFurniture() {
        if (this.furniture) return "furniture";
        return "no furniture";
    }
}
