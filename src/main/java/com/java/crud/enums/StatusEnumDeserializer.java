package com.java.crud.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class StatusEnumDeserializer extends StdDeserializer<StatusEnum> {

    public StatusEnumDeserializer() {
        this(null);
    }

    public StatusEnumDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public StatusEnum deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        StatusEnum type = StatusEnum.getStatusEnum(jsonParser.getValueAsInt());
        if (type != null) {
            return type;
        }
        throw new JsonMappingException("invalid value for type, must be 'one' or 'two'");
    }
}
