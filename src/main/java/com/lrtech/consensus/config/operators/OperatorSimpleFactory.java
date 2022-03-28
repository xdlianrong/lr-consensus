package com.lrtech.consensus.config.operators;

import com.lrtech.consensus.Application;
import com.lrtech.consensus.config.operators.lianrong.LianrongUsernameEncoder;
import com.lrtech.consensus.config.producer.ProducerProperties;
import com.lrtech.consensus.user.UsernameEncoder;

public class OperatorSimpleFactory {
    private static UsernameEncoder usernameEncoder;

    private static void instance(ProducerProperties producerProperties) {
        if (producerProperties == null) {
            return;
        }

        if (ProducerProperties.OPERATOR_LIANRONG.equals(producerProperties.getOperator())) {
            usernameEncoder = new LianrongUsernameEncoder();
        } else if (ProducerProperties.OPERATOR_SHUNFENG.equals(producerProperties.getOperator())) {
            usernameEncoder = new LianrongUsernameEncoder();
        } else {
            throw new RuntimeException();
        }
    }

    public static UsernameEncoder usernameEncoderInstance() {
        if (usernameEncoder != null) {
            return usernameEncoder;
        }
        ProducerProperties producerProperties = Application.applicationContext.getBean(ProducerProperties.class);
        return usernameEncoderInstance(producerProperties);
    }

    public static UsernameEncoder usernameEncoderInstance(ProducerProperties producerProperties) {
        if (usernameEncoder != null) {
            return usernameEncoder;
        }
        instance(producerProperties);
        return usernameEncoder;
    }
}