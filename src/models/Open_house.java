package models;

import javax.persistence.*;

/**
 * Created by tomko on 27.3.2017.
 */
@Entity
@Table(name = "open_houses")
public class Open_house {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Column(name = "time_at")
    private String time_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getTime_at() {
        return time_at;
    }

    public void setTime_at(String time_at) {
        this.time_at = time_at;
    }
}
