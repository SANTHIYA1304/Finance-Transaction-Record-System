package com.wipro.ftrs.service;

import java.util.ArrayList;
import java.time.LocalDate;
import com.wipro.ftrs.entity.*;
import com.wipro.ftrs.util.*;

public class FinancialTransactionService {
    private ArrayList<Account> accounts;
    private ArrayList<TransactionRecord> transactions;
   public FinancialTransactionService(ArrayList<Account> accounts,ArrayList<TransactionRecord> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;}
   public Account findAccount(String id) throws AccountNotFoundException {
        for (Account a : accounts)
            if (a.getAccountId().equals(id))
                return a;
        throw new AccountNotFoundException("Account not found");}

    public void deposit(String id, double amt, String remarks) throws AccountNotFoundException, InvalidTransactionException {
        if (amt <= 0) throw new InvalidTransactionException("Invalid amount");
        Account a = findAccount(id);
        a.setBalance(a.getBalance() + amt);
        transactions.add(new TransactionRecord("T" + transactions.size(), id, "DEPOSIT",amt, LocalDate.now().toString(), remarks)); }
    public void withdraw(String id, double amt, String remarks) throws AccountNotFoundException, InvalidTransactionException,InsufficientBalanceException {
        if (amt <= 0) throw new InvalidTransactionException("Invalid amount");
        Account a = findAccount(id);
        if (a.getBalance() < amt)
            throw new InsufficientBalanceException("Insufficient balance");
        a.setBalance(a.getBalance() - amt);
        transactions.add(new TransactionRecord(
                "T" + transactions.size(), id, "WITHDRAW",
                amt, LocalDate.now().toString(), remarks));}
    public void transfer(String from, String to, double amt, String remarks) throws AccountNotFoundException, InvalidTransactionException,InsufficientBalanceException {
        withdraw(from, amt, remarks);
        deposit(to, amt, remarks); }
    public ArrayList<TransactionRecord> getTransactionHistory(String id) throws AccountNotFoundException {
        findAccount(id);
        ArrayList<TransactionRecord> list = new ArrayList<>();
        for (TransactionRecord t : transactions)
            if (t.getAccountId().equals(id))
                list.add(t);
        return list; }
    public String generateAccountSummary(String id) throws AccountNotFoundException {
        Account a = findAccount(id);
        return a.getAccountHolderName() + " | Balance: " + a.getBalance();}}
