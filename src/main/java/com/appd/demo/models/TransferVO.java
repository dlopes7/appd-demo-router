package com.appd.demo.models;


public class TransferVO {

    private AccountVO from;
    private AccountVO to;
    private Double amount;

    public enum type {
        DOC, TED
    }


    public AccountVO getFrom() {
        return from;
    }

    public void setFrom(AccountVO from) {
        this.from = from;
    }

    public AccountVO getTo() {
        return to;
    }

    public void setTo(AccountVO to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
