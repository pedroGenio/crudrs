package com.java.crud.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glassfish.jersey.internal.guava.Maps;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

/**
 * I tried to deserialize in many ways and didn't have success until now
 * to finish at right time, I back to a simple String
 */
@JsonDeserialize(using = StatusEnumDeserializer.class)
@JsonFormat(shape = OBJECT)
public enum StatusEnum {

    DRAFT(0, "DRAFT"),
    REVIEW(1, "REVIEW"),
    PRODUCTION(2, "PRODUCTION"),
    CANCELLED(3, "CANCELLED");

    private Integer id;
    private String value;

    private static final Map<Integer, StatusEnum> nameIndex = Maps.newHashMapWithExpectedSize(StatusEnum.values().length);

    static {
        for (StatusEnum status : StatusEnum.values()) {
            nameIndex.put(status.getId(), status);
        }
    }

    StatusEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public static StatusEnum getStatusEnum(Integer id) {
        return nameIndex.get(id);
    }
}