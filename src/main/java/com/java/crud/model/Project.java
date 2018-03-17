package com.java.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Project extends Entity {

    private Long version = new Long(0);
    private String name;
    private String description;
    private String status;
//    @JsonDeserialize(using = StatusEnumDeserializer.class)
//    private StatusEnum statusEnum;

    public Long getVersion() {
        return version;
    }

    @JsonIgnore
    public Long getVersioning() {
        return version++;
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
