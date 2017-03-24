package my.generalledger.dao.ledger;

import my.generalledger.domain.ledger.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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
		return session.get(Transaction.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactions() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving list of transactions");
		return session.createSQLQuery("SELECT * FROM ledger_entry")
				.addEntity(Transaction.class).list();
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
