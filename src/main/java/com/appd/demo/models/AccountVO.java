package com.appd.demo.models;

public class AccountVO {

    private String branchNumber;
    private String accountNumber;
    private BalanceVO balance;

    public AccountVO(){
        this.balance = new BalanceVO();
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BalanceVO getBalance() {
        return balance;
    }

    public void setBalance(BalanceVO balance) {
        this.balance = balance;
    }
}
