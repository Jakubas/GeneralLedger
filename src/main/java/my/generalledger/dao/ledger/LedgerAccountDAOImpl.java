package my.generalledger.dao.ledger;

import my.generalledger.domain.ledger.LedgerAccount;
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
public class LedgerAccountDAOImpl implements LedgerAccountDAO {

	private final static Logger logger = LoggerFactory.getLogger(LedgerAccountDAOImpl.class);
	
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
		return session.get(LedgerAccount.class, id);
	}

	@Override
	public LedgerAccount getAccountByNumber(String number) {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving ledger account by number: " + number);
		return session.get(LedgerAccount.class, number);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LedgerAccount> getAccounts() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("retrieving list of ledger accounts");
		return session.createSQLQuery("SELECT * FROM ledger_account")
				.addEntity(LedgerAccount.class).list();
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
