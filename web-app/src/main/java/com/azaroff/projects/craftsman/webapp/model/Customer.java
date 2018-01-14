package com.azaroff.projects.craftsman.webapp.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Copyright DonRiver Inc. All Rights Reserved.
 * <p>
 * Author: Dmitry Azarov
 * Created: 11.01.2018.
 */
public class Customer {

    private String customer;
    private String ccyPair;
    private String type;
    private String style;
    private String direction;
    private String strategy;
    private String tradeDate;
    private BigDecimal amount1;
    private BigDecimal amount2;
    private double rate;
    private String valueDate;
    private String deliveryDate;
    private String expiryDate;
    private String trader;
    private String payCcy;
    private String excerciseStartDate;
    private double premium;
    private String premiumCcy;
    private String premiumType;
    private String premiumDate;
    private String legalEntity;

    private LocalDate valueDateDate;
    private LocalDate tradeDateDate;
    private LocalDate expiryDateDate;
    private LocalDate excerciseStartDateDate;
    private LocalDate premiumDateDate;
    private LocalDate deliveryDateDate;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getAmount1() {
        return amount1;
    }

    public void setAmount1(BigDecimal amount1) {
        this.amount1 = amount1;
    }

    public BigDecimal getAmount2() {
        return amount2;
    }

    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getPayCcy() {
        return payCcy;
    }

    public void setPayCcy(String payCcy) {
        this.payCcy = payCcy;
    }

    public String getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public void setExcerciseStartDate(String excerciseStartDate) {
        this.excerciseStartDate = excerciseStartDate;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getPremiumCcy() {
        return premiumCcy;
    }

    public void setPremiumCcy(String premiumCcy) {
        this.premiumCcy = premiumCcy;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    public String getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(String premiumDate) {
        this.premiumDate = premiumDate;
    }

    public String getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(String legalEntity) {
        this.legalEntity = legalEntity;
    }

    public LocalDate getValueDateDate() {
        return valueDateDate;
    }

    public void setValueDateDate(LocalDate valueDateDate) {
        this.valueDateDate = valueDateDate;
    }

    public LocalDate getTradeDateDate() {
        return tradeDateDate;
    }

    public void setTradeDateDate(LocalDate tradeDateDate) {
        this.tradeDateDate = tradeDateDate;
    }

    public LocalDate getExpiryDateDate() {
        return expiryDateDate;
    }

    public void setExpiryDateDate(LocalDate expiryDateDate) {
        this.expiryDateDate = expiryDateDate;
    }

    public LocalDate getExcerciseStartDateDate() {
        return excerciseStartDateDate;
    }

    public void setExcerciseStartDateDate(LocalDate excerciseStartDateDate) {
        this.excerciseStartDateDate = excerciseStartDateDate;
    }

    public LocalDate getPremiumDateDate() {
        return premiumDateDate;
    }

    public void setPremiumDateDate(LocalDate premiumDateDate) {
        this.premiumDateDate = premiumDateDate;
    }

    public LocalDate getDeliveryDateDate() {
        return deliveryDateDate;
    }

    public void setDeliveryDateDate(LocalDate deliveryDateDate) {
        this.deliveryDateDate = deliveryDateDate;
    }
}
