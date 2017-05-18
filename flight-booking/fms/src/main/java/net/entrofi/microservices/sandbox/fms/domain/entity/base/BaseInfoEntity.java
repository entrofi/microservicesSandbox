package net.entrofi.microservices.sandbox.fms.domain.entity.base;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

/**
 * This entity holds the values for info entity auditing.
 *
 * @author oyalcin
 * @since 1/20/2015.
 */
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class BaseInfoEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -3313308193089225957L;
    /**
     * The creation date of the entity
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();
    /**
     * Last update time for entity
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    /**
     * The owner of the last process.
     */
    private Long userId;
    /**
     * The ip from which the last operation was done
     */
    private String ipAddress;

    @Version
    private int version;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
