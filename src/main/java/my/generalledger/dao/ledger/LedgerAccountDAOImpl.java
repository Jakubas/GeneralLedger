package my.generalledger.dao.ledger;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.generalledger.domain.ledger.LedgerAccount;

@Repository
@Transactional
public class LedgerAccountDAOImpl implements LedgerAccountDAO {

	private final SessionFactory sessionFactory;
	
	@Autowired
	public LedgerAccountDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveAccount(LedgerAccount ledgerAccount) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(ledgerAccount);
	}
	
	@Override
	public LedgerAccount getAccountById(int id) {
		Session session = sessionFactory.getCurrentSession();
		LedgerAccount ledgerAccount = session.get(LedgerAccount.class, id);
		return ledgerAccount;
	}

	@Override
	public LedgerAccount getAccountByNumber(String number) {
		Session session = sessionFactory.getCurrentSession();
		LedgerAccount ledgerAccount = session.get(LedgerAccount.class, number);
		return ledgerAccount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LedgerAccount> getAccounts() {
		Session session = sessionFactory.getCurrentSession();
		List<LedgerAccount> list = session.createSQLQuery("SELECT * FROM test.ledger_account")
				.addEntity(LedgerAccount.class).list();
		return list;
	}

	@Override
	public void updateAccount(LedgerAccount ledgerAccount) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ledgerAccount);
	}

	@Override
	public void deleteAccount(LedgerAccount ledgerAccount) {
		Session session = sessionFactory.getCurrentSession();
		ledgerAccount = session.get(LedgerAccount.class, ledgerAccount.getId());
		if (ledgerAccount != null) {
			session.delete(ledgerAccount);
		}
	}
}
