package com.java.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for Project
 */
public class Project extends BaseEntity {

    private Long version = 0L;
    private Long versioning;
    private String name;
    private String description;
    private String status; // There is a CONSTRAINT at the database to check words, replacing ENUM

//    @JsonDeserialize(using = StatusEnumDeserializer.class)
//    private StatusEnum statusEnum;

    public Long getVersion() {
        return version;
    }

    /**
     * Version control, just one way to have this control
     * Annotation for this attribute doesn't show up in JSON Object
     * **It seems that it is not working correctly
     * @return
     */
    @JsonIgnore
    public Long getVersioning() {
        return version + 1;
    }

    @JsonProperty
    public void setVersioning(Long versioning) {
        this.versioning = versioning;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    public StatusEnum getStatusEnum() {
//        return statusEnum;
//    }
//
//    public void setStatusEnum(StatusEnum statusEnum) {
//        this.statusEnum = statusEnum;
//    }


}
