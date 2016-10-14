package qiang.envelope.printer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Qiang on 26/06/2016.
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "CONTACT_1")
    private String contact1;

    @Column(name = "CONTACT_2")
    private String contact2;

    @Column(name = "CONTACT_3")
    private String contact3;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELEPHONE_1")
    private String telephone1;

    @Column(name = "TELEPHONE_2")
    private String telephone2;

    @ManyToOne
    @JoinColumn(name = "ENVELOPE_TYPE_ID")
    private EnvelopeType envelopeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getContact3() {
        return contact3;
    }

    public void setContact3(String contact3) {
        this.contact3 = contact3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public EnvelopeType getEnvelopeType() {
        return envelopeType;
    }

    public void setEnvelopeType(EnvelopeType envelopeType) {
        this.envelopeType = envelopeType;
    }
}
