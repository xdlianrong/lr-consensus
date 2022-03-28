package com.lrtech.consensus.entity;

import com.lrtech.consensus.validation.Violation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseEntity {
    private Violation violation;
    private Object data;

    public ResponseEntity(Violation violation) {
        this.violation = violation;
    }

    @Data
    public static class UserDetails {
        private String username;
        private String name;
        private List<String> roles;

        public UserDetails(String username, String name, List<String> roles) {
            this.username = username;
            this.name = name;
            this.roles = roles;
        }
    }
}