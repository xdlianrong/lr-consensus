package com.lrtech.consensus.config.operators.shunfeng;

import com.lrtech.consensus.user.UsernameEncoder;

public class ShunfengUsernameEncoder extends UsernameEncoder {

    @Override
    public String fromLocal() {
        return "lo";
    }

    @Override
    public String fromCas() {
        return "cas";
    }
}