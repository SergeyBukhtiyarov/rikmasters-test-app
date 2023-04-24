package common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
public class IdEntity {
    @Id
    @Column(
            updatable = false,
            nullable = false
    )
    private UUID id;

    @PrePersist
    public void init() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public IdEntity() {
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }
}