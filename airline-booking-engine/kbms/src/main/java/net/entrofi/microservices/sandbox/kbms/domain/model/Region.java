package net.entrofi.microservices.sandbox.kbms.domain.model;

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
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * Region
 *
 * @author hcomak
 * @version Dec 23, 2014
 * @created Dec 23, 2014
 * @see <Add inherited classes>
 ******************************************************************************/
@Entity
@Audited
@Table(name = "KBMS_REGION")
public class Region extends BaseInfoEntity {

    /**
     *
     */
    private static final long serialVersionUID = -1168087538248034270L;

    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.name.empty}")
    @Column(unique = true, nullable = false)
    private String name;

    @NotEmpty(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.commons.error.FieldIsRequired}")
    @Pattern(message = "{net.entrofi.microservices.sandbox.kbms.domain.model.Region.error.validation.codeFormat}", regexp = "[A-Z]{2,3}\\b")
    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private Set<Country> countries = new HashSet<Country>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Region parent;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Region> regions = new HashSet<Region>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Set<Country> getCountries() {
        return countries;
    }


    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }


    public Region getParent() {
        return parent;
    }


    public void setParent(Region parent) {
        this.parent = parent;
    }

    @XmlTransient
    public Set<Region> getRegions() {
        return regions;
    }


    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
