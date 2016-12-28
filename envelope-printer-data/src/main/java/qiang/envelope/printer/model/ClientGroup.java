package qiang.envelope.printer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Qiang on 28/11/2016.
 */
@Entity
@Table(name = "CLIENT_GROUP")
public class ClientGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "CLIENT_GROUP_MAPPING",
            joinColumns = @JoinColumn(name = "CLIENT_GROUP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")
    )
    private List<Client> clients;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENVELOPE_ID")
    private Envelope envelope;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }
}
