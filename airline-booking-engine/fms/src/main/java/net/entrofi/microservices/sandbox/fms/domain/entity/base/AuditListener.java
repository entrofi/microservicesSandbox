package net.entrofi.microservices.sandbox.fms.domain.entity.base;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * This class listens for hibernate operations and triggers logging processes on entity manager events.
 *
 * @author oyalcin
 * @since 1/20/2015.
 */
public class AuditListener {


    private Long userId;
    private String ipAddress;

    /**
     * this method prepares the entity fields before save operation
     *
     * @param entity the entity to manipulate
     */
    @PrePersist
    public void onPrePersist(BaseInfoEntity entity) {
        assignInformations();
        entity.setCreateDate(new Date());
        entity.setUserId(userId);
        entity.setIpAddress(ipAddress);
    }

    /**
     * bu method nesne veritabanına güncellenmeden önce gerekli alanları doldurma işlevini gerçekleştirir.
     *
     * @param entity işlem yapılan nesne
     */
    @PreUpdate
    public void onPreUpdate(BaseInfoEntity entity) {
        assignInformations();
        entity.setUserId(userId);
        entity.setUpdateDate(new Date());
        entity.setIpAddress(ipAddress);
    }

    /**
     * This method reads the ip address and user information
     */
    private void assignInformations() {

    }

}
