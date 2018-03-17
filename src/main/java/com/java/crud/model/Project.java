package com.java.crud.model;

/**
 * Model for Project
 */
public class Project extends BaseEntity {

    private Long version = 0L;
    private String name;
    private String description;
    private String status; // There is a CONSTRAINT at the database to check words, replacing ENUM

//    @JsonDeserialize(using = StatusEnumDeserializer.class)
//    private StatusEnum statusEnum;

    public Long getVersion() {
        return version;
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
