package org.edinar.abnamrofxtradeclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbnAmroObjectMapper extends ObjectMapper {
    public AbnAmroObjectMapper() {
        super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
