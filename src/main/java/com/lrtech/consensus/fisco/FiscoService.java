package com.lrtech.consensus.fisco;

import com.lrtech.consensus.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FiscoService implements BasicService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String getService1() {
        return "";
    }
}