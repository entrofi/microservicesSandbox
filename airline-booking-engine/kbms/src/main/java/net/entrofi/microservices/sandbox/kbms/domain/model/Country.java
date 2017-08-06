package net.entrofi.microservices.sandbox.kbms.domain.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import net.entrofi.microservices.sandbox.kbms.domain.model.base.BaseInfoEntity;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * Country
 * <p>
 * Please provide <b><u>detailed</u></b> class definition.
 * Sample Start: This class is a part of KBMS application
 * and is responsible of accessing remote machines with the use of different
 * protocols.
 *
 * @author hcomak
 * @see <Add inherited classes>
 ******************************************************************************/
@Audited
@Entity
@Table(name = "KBMS_COUNTRY")
public class Country extends BaseInfoEntity {


    private static final long serialVersionUID = 7811541726640126797L;


    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.name.empty}")
    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Country.error.validation.codeFormat}", regexp = "[A-Z]{2}\\b")
    @Column(unique = true, nullable = false)
    private String code;

    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.name.empty}")
    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Country.error.validation.name.format}", regexp = "^([A-Za-z]{2,})([A-Z\\s]){0,}([A-Za-z]{0,})\\b")
    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @NotNull(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Country.error.validation.region.null}")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Region region;

    @OneToMany(mappedBy = "country")
    @JsonBackReference
    private Set<Division> cities = new HashSet<Division>();

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Region getRegion() {
        return region;
    }


    public void setRegion(Region region) {
        this.region = region;
    }


    @XmlTransient
    public Set<Division> getCities() {
        return cities;
    }

    public void setCities(Set<Division> cities) {
        this.cities = cities;
    }
}
