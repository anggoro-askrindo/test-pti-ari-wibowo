package com.example.assesmentbackend.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "m_lookup")
@AllArgsConstructor
@NoArgsConstructor
public class MLookup {
    @Id
    @Column(name = "lookup_key")
    private String lookupKey;
    @Column(name = "lookup_group")
    private String lookupGroup;
    @Column(name = "key_only")
    private String keyOnly;
    @Column(name = "label")
    private String label;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "version")
    private Long version;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_date")
    private Date modifiedDate;
}
