package models;

import javax.persistence.*;

/**
 * Created by tomko on 27.3.2017.
 */
@Entity
@Table(name = "sold_estates")
public class Sold_estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Column(name = "sold_to")
    private String sold_to;

    @Column(name = "price")
    private double price;

    @Column(name = "sold_date")
    private String sold_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getSold_to() {
        return sold_to;
    }

    public void setSold_to(String sold_to) {
        this.sold_to = sold_to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSold_date() {
        return sold_date;
    }

    public void setSold_date(String sold_date) {
        this.sold_date = sold_date;
    }
}
