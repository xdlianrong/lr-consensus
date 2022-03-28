package com.lrtech.consensus.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {
    public static final Violation SUCCESS = new Violation(0, "操作成功");

    int code;
    String message;
}