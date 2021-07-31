package com.example.demo.mapper;

import com.example.demo.entity.Invoices;
import com.example.demo.entity.JoinLineItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SalesMapper {
    @Select("SELECT SUM(inv.sub_total) AS amount, CONCAT(YEAR (inv.date),'-',MONTH(inv.date)) AS yearMonth FROM localhost_mysql.invoices AS inv WHERE (inv.status = 'PAID' OR inv.status = 'AUTHORISED') AND tenant_id = #{id} AND date >= #{from} AND date <= #{to} GROUP BY yearMonth")
    List<Invoices> findID(@Param("id") String id, @Param("from") String from, @Param("to") String to );

    @Select("SELECT SUM(inv.sub_total) AS amount, CONCAT(YEAR (inv.date),'-',MONTH(inv.date)) AS yearMonth FROM localhost_mysql.invoices AS inv WHERE (inv.status = 'PAID' OR inv.status = 'AUTHORISED') AND date >= #{from} AND date <= #{to} GROUP BY yearMonth")
    List<Invoices> findNonID( @Param("from") String from, @Param("to") String to );

    @Select("SELECT litem.account_code AS 'accountCode', litem.unit_amount AS 'amount', acc.tenant_id AS 'tenantId', acc.name AS 'name' FROM localhost_mysql.line_items litem JOIN localhost_mysql.invoices inv USING (invoice_id) JOIN localhost_mysql.accounts acc ON litem.account_code = acc.code WHERE (inv.status = 'PAID' OR inv.status = 'AUTHORISED') AND acc.tenant_id = #{id} AND date >= #{from} AND date <= #{to}")
    List<JoinLineItem> findSaleAccountID(@Param("id") String id, @Param("from") String from, @Param("to") String to );
    
    @Select("SELECT litem.account_code AS 'accountCode', litem.unit_amount AS 'amount', acc.tenant_id AS 'tenantId', acc.name AS 'name' FROM localhost_mysql.line_items litem JOIN localhost_mysql.invoices inv USING (invoice_id) JOIN localhost_mysql.accounts acc ON litem.account_code = acc.code WHERE (inv.status = 'PAID' OR inv.status = 'AUTHORISED') AND date >= #{from} AND date <= #{to}")
    List<JoinLineItem> findSaleAccountNonID(@Param("from") String from, @Param("to") String to );
}

