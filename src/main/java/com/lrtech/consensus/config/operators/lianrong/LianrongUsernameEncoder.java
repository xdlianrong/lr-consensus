package com.lrtech.consensus.config.operators.lianrong;

import com.lrtech.consensus.user.UsernameEncoder;

public class LianrongUsernameEncoder extends UsernameEncoder {

    @Override
    public String fromLocal() {
        return "lo";
    }

    @Override
    public String fromCas() {
        return "cas";
    }
}