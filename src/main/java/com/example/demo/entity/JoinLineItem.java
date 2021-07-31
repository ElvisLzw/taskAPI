package com.example.demo.entity;

import lombok.Data;

@Data
public class JoinLineItem {
    private String accountCode;
    private Double amount;
    private String tenantId;
    private String name;
}
