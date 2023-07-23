package com.unipd.semicolon.api.util.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unipd.semicolon.api.util.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHelper {

    private ResponseHelper() {
    }

    public static <T> ResponseEntity response(T t) {
        return response(t, "OK", HttpStatus.OK);
    }

    public static <T> ResponseEntity response(T t, HttpStatus status) {
        return response(t, "OK", status);
    }

    public static <T> ResponseEntity response(T t, String msg, HttpStatus status) {
        return new ResponseEntity(new ResponseStructure(t, msg), status);
    }

    public static <T> ResponseEntity<String> okJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(object);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static <T> ResponseEntity<String> okJsonList(List<?> objects) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(objects);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
