package com.java.crud.enums;


public enum StatusEnum {

    DRAFT(0, "DRAFT"),
    REVIEW(1, "REVIEW"),
    PRODUCTION(2, "PRODUCTION"),
    CANCELLED(3, "CANCELLED");

    private Integer id;
    private String value;


    StatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getValue(Integer id) {
        for (StatusEnum enums : StatusEnum.values()) {
            if (enums.getId().equals(id)) {
                return enums.getValue();
            }
        }
        return null;
    }

    public static StatusEnum get(Integer id) {
        for (StatusEnum gp : StatusEnum.values()) {
            if (gp.getId().equals(id)) {
                return gp;
            }
        }
        return null;
    }

}