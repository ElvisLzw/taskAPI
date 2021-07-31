package com.example.demo.wrap;

import com.example.demo.entity.JoinLineItem;
import lombok.Data;

import java.util.List;

@Data
public class SalesOfAccounts {
    private List<JoinLineItem> salesOfAccounts;
}
