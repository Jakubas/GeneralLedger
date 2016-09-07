package my.generalledger.dao.ledger;

import java.util.List;

import my.generalledger.domain.ledger.Transaction;

public interface TransactionDAO {
	
	void saveTransaction(Transaction transaction);
	Transaction getAccountById(int id);
	List<Transaction> getTransactions();
	void updateTransaction(Transaction transaction);
	void deleteTransaction(Transaction transaction);
}