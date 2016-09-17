package my.generalledger.dao.ledger;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.generalledger.domain.ledger.Transaction;

@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO {

	private final static Logger logger = LoggerFactory.getLogger(TransactionDAOImpl.class);
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public TransactionDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveTransaction(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("adding transaction: " + transaction.getId());
		session.persist(transaction);
	}

	@Override
	public Transaction getTransactionById(int id) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving transaction by id: " + id);
		Transaction transaction = session.get(Transaction.class, id);
		return transaction;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactions() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving list of transactions");
		List<Transaction> list = session.createSQLQuery("SELECT * FROM test.ledger_entry")
				.addEntity(Transaction.class).list();
		return list;
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("updating transaction: " + transaction.getId());
		session.update(transaction);
	}

	@Override
	public void deleteTransaction(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		int id = transaction.getId();
		logger.debug("deleting transaction: " + id);
		transaction = session.get(Transaction.class, id);
		if (transaction != null) {
			session.delete(transaction);
		}
	}
}
