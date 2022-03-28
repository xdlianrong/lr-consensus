package com.lrtech.consensus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PageEntity {
    private int pageNum;
    private List contents;
}