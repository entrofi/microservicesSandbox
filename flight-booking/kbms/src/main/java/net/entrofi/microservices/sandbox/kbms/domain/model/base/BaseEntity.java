package net.entrofi.microservices.sandbox.kbms.domain.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


/**
 * This entity holds the values for base entity auditing.
 *
 * @author mdpinar
 */
@MappedSuperclass
@EntityListeners(AuditListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    //TODO: id value must be provided by a generic id generator strategy for multi db support
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
