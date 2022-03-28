package com.lrtech.consensus.config.operators;

import lombok.Data;

@Data
public class OperatorProperties {
    private String webaseFrontUrl;
    private String webaseNodeManagerUrl;

    @Data
    public static class FiscoBcos {
        private ContractAddress contractAddress;
    }

    @Data
    public static class ContractAddress {
    }
}
