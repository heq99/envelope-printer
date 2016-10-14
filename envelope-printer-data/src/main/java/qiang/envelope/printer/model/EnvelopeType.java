package qiang.envelope.printer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Qiang on 06/10/2016.
 */
@Entity
@Table(name = "ENVELOPE_TYPE")
public class EnvelopeType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "TEMPLATE")
    private String template;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
