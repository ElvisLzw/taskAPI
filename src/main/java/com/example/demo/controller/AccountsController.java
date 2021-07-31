package com.example.demo.controller;
import com.example.demo.entity.Invoices;
import com.example.demo.entity.JoinLineItem;
import com.example.demo.wrap.MonthlyInvoiceAmounts;
import com.example.demo.mapper.SalesMapper;
import com.example.demo.wrap.SalesOfAccounts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class AccountsController {
    @Resource
    SalesMapper salesMapper;

    @GetMapping("/yearMonth")
    public MonthlyInvoiceAmounts getYearMonth(@RequestParam("tenantId") String tenantId, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) {
        List<Invoices> invoices;
        if (tenantId.isEmpty()) {
            invoices = salesMapper.findNonID(dateFrom, dateTo);
        } else {
            invoices = salesMapper.findID(tenantId, dateFrom, dateTo);
        }
        MonthlyInvoiceAmounts response = new MonthlyInvoiceAmounts();
        response.setMonthlyInvoiceAmounts(invoices);
        return response;
    }

    @GetMapping("/salesAccounts")
    public SalesOfAccounts getSalesAccounts(@RequestParam("tenantId") String tenantId, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) {
        List<JoinLineItem> joinLineItems;
        if (tenantId.isEmpty()) {
            joinLineItems = salesMapper.findSaleAccountNonID(dateFrom, dateTo);
        } else {
            joinLineItems = salesMapper.findSaleAccountID(tenantId, dateFrom, dateTo);
        }
        SalesOfAccounts response = new SalesOfAccounts();
        response.setSalesOfAccounts(joinLineItems);
        return response;
    }
}
