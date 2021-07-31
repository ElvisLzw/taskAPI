package com.example.demo.wrap;

import com.example.demo.entity.Invoices;
import lombok.Data;

import java.util.List;

@Data
public class MonthlyInvoiceAmounts {
    private List<Invoices> monthlyInvoiceAmounts;
}
