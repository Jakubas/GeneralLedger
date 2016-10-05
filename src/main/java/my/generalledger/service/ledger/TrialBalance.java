package my.generalledger.service.ledger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import my.generalledger.domain.ledger.Transaction;

@Service
public class TrialBalance {
	
	public int calculateBalance(List<Transaction> transactions) {
		int debitBalance = 0;
		int creditBalance = 0;
		
		for (Transaction transaction : transactions) {
			if (transaction.getDebitAccount() != null) {
				debitBalance += transaction.getAmount();
			}
			if (transaction.getCreditAccount() != null) {
				creditBalance += transaction.getAmount();
			}
		}
		return debitBalance - creditBalance;
	}
	
	//in a balanced ledger the set of debit transactions and credit transactions 
	//will be equivalent
	public Set<Transaction> getDebitTransactions(List<Transaction> transactions) {
		Set<Transaction> debitTransactions = new HashSet<Transaction>();
		for (Transaction transaction : transactions) {
			if (transaction.getDebitAccount() != null) {
				debitTransactions.add(transaction);
			}
		}
		return debitTransactions;
	}
	
	public Set<Transaction> getCreditTransactions(List<Transaction> transactions) {
		Set<Transaction> creditTransactions = new HashSet<Transaction>();
		for (Transaction transaction : transactions) {
			if (transaction.getCreditAccount() != null) {
				creditTransactions.add(transaction);
			}
		}
		return creditTransactions;
	}
}
