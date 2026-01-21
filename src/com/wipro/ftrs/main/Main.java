package com.wipro.ftrs.main;
import java.util.ArrayList;
import com.wipro.ftrs.entity.*;
import com.wipro.ftrs.service.FinancialTransactionService;
import com.wipro.ftrs.util.*;
public class Main {
 public static void main(String[] args) {
 ArrayList<Account> accounts = new ArrayList<>();
 accounts.add(new Account("A001", "Santhiya", 5000));
 accounts.add(new Account("A002", "Sivasakthi", 7000));
 ArrayList<TransactionRecord> transactions = new ArrayList<>();
 FinancialTransactionService service = new FinancialTransactionService(accounts, transactions);
 try {
 service.deposit("A001", 1500, "Salary credit");
 service.withdraw("A001", 500, "ATM withdrawal");
 service.transfer("A001", "A002", 2000, "Online transfer to Neha");
 System.out.println("--- Transaction History for A001 ---");
 for (TransactionRecord tr : service.getTransactionHistory("A001")) {
 System.out.println(tr.getType() + " - " + tr.getAmount());
 }
 System.out.println("\n--- Account Summary ---");
 System.out.println(service.generateAccountSummary("A001"));
 } catch (Exception ex) {
 System.out.println(ex);
 }
 }
}