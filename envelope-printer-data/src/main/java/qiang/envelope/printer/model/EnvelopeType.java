package qiang.envelope.printer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @Column(name = "WIDTH")
    private Long width;

    @Column(name = "HEIGHT")
    private Long height;

    @OneToMany(mappedBy = "envelopeType", fetch = FetchType.LAZY)
    private Set<EnvelopeField> envelopeFields;

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

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Set<EnvelopeField> getEnvelopeFields() {
        return envelopeFields;
    }

    public void setEnvelopeFields(Set<EnvelopeField> envelopeFields) {
        this.envelopeFields = envelopeFields;
    }
}
