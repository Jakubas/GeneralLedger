package my.generalledger.dao.ledger;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.generalledger.domain.ledger.LedgerAccount;

@Repository
@Transactional
public class LedgerAccountDAOImpl implements LedgerAccountDAO {

	private final static Logger logger = Logger.getLogger(LedgerAccountDAOImpl.class);
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public LedgerAccountDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveAccount(LedgerAccount ledgerAccount) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("added ledger account: " + ledgerAccount.getId());
		session.persist(ledgerAccount);
	}
	
	@Override
	public LedgerAccount getAccountById(int id) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving ledger account by id: " + id);
		LedgerAccount ledgerAccount = session.get(LedgerAccount.class, id);
		return ledgerAccount;
	}

	@Override
	public LedgerAccount getAccountByNumber(String number) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving ledger account by number: " + number);
		LedgerAccount ledgerAccount = session.get(LedgerAccount.class, number);
		return ledgerAccount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LedgerAccount> getAccounts() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving list of ledger accounts");
		List<LedgerAccount> list = session.createSQLQuery("SELECT * FROM test.ledger_account")
				.addEntity(LedgerAccount.class).list();
		return list;
	}

	@Override
	public void updateAccount(LedgerAccount ledgerAccount) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("updating ledger account: " + ledgerAccount.getId());
		session.update(ledgerAccount);
	}

	@Override
	public void deleteAccount(LedgerAccount ledgerAccount) {
		Session session = sessionFactory.getCurrentSession();
		int id = ledgerAccount.getId();
		logger.debug("deleting ledger account: " + id);
		ledgerAccount = session.get(LedgerAccount.class, id);
		if (ledgerAccount != null) {
			session.delete(ledgerAccount);
		}
	}
}
