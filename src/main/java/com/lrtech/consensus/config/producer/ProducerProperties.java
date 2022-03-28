package com.lrtech.consensus.config.producer;

import lombok.Data;

@Data
public class ProducerProperties {
    public static final String OPERATOR_LIANRONG = "lianrong";
    public static final String OPERATOR_SHUNFENG = "shunfeng";

    private String producer;
    private String operator;
    private CORS cors;
    private String defaultAdminUsername;
    private String defaultAdminName;

    @Data
    public static class CORS {
        private String allowedOrigins;
    }
}