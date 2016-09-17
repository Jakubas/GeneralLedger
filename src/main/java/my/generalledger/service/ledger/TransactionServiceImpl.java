package my.generalledger.service.ledger;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.generalledger.dao.ledger.TransactionDAO;
import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	private final TransactionDAO dao;
	
	@Autowired
	public TransactionServiceImpl(TransactionDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void saveTransaction(Transaction transcaction) {
		dao.saveTransaction(transcaction);
	}
	
	@Override
	public void saveTransaction(Calendar date, String description, int amount, 
			LedgerAccount creditAccount, LedgerAccount debitAccount) {
		Transaction transaction = new Transaction(date, description, amount, creditAccount, debitAccount);
		logger.debug("created transaction: " + transaction.getId());
		saveTransaction(transaction);
	}

	@Override
	public Transaction getTransactionById(int id) {
		return dao.getTransactionById(id);
	}

	@Override
	public List<Transaction> getTransactions() {
		return dao.getTransactions();
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		dao.updateTransaction(transaction);
	}

	@Override
	public void updateTransaction(int transactionId, Calendar date, String description, int amount,
			LedgerAccount creditAccount, LedgerAccount debitAccount) {
		logger.debug("updating transaction: " + transactionId);
		Transaction transaction = getTransactionById(transactionId);
		transaction.setDate(date);
		transaction.setDescription(description);
		transaction.setAmount(amount);
		transaction.setCreditAccount(creditAccount);
		transaction.setDebitAccount(debitAccount);
		updateTransaction(transaction);
	}
	
	@Override
	public void deleteTransaction(Transaction transaction) {
		dao.deleteTransaction(transaction);
	}
	
	@Override
	public void deleteTransaction(int id) {
		logger.debug("deleting transaction: " + id);
		Transaction transaction = dao.getTransactionById(id);
		deleteTransaction(transaction);
	}
}
