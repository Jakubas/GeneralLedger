package my.generalledger.dao.ledger;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.generalledger.domain.ledger.Transaction;

@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO {

	private final SessionFactory sessionFactory;
	
	@Autowired
	public TransactionDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveTransaction(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(transaction);
	}

	@Override
	public Transaction getAccountById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.get(Transaction.class, id);
		return transaction;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactions() {
		Session session = sessionFactory.getCurrentSession();
		List<Transaction> list = session.createSQLQuery("SELECT * FROM test.ledger_entry")
				.addEntity(Transaction.class).list();
		return list;
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		session.update(transaction);
	}

	@Override
	public void deleteTransaction(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		transaction = session.get(Transaction.class, transaction.getId());
		if (transaction != null) {
			session.delete(transaction);
		}
	}
}
