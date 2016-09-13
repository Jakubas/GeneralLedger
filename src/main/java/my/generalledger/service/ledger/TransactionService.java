package my.generalledger.service.ledger;

import java.util.Calendar;
import java.util.List;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.Transaction;

public interface TransactionService {

	void saveTransaction(Transaction transaction);
	void saveTransaction(Calendar date, String description, int amount, 
			LedgerAccount creditAccount, LedgerAccount debitAccount);
	Transaction getTransactionById(int id);
	List<Transaction> getTransactions();
	void updateTransaction(Transaction transaction);
	void deleteTransaction(Transaction transaction);
	void deleteTransaction(int id);
}
