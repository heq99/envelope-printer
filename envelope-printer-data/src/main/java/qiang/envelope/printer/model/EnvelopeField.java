package qiang.envelope.printer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Qiang on 22/11/2016.
 */
@Entity
@Table(name = "ENVELOPE_FIELD")
public class EnvelopeField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENVELOPE_ID")
    private Envelope envelope;

    @Column(name = "FIELD_NAME")
    private String fieldName;

    @Column(name = "FIELD_POSITION_X")
    private Long fieldPositionX;

    @Column(name = "FIELD_POSITION_Y")
    private Long fieldPositionY;

    @Column(name = "FIELD_WIDTH")
    private Long fieldWidth;

    @Column(name = "FIELD_HEIGHT")
    private Long fieldHeight;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getFieldPositionX() {
        return fieldPositionX;
    }

    public void setFieldPositionX(Long fieldPositionX) {
        this.fieldPositionX = fieldPositionX;
    }

    public Long getFieldPositionY() {
        return fieldPositionY;
    }

    public void setFieldPositionY(Long fieldPositionY) {
        this.fieldPositionY = fieldPositionY;
    }

    public Long getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(Long fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public Long getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(Long fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
