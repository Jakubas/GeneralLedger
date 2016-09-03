package my.generalledger.dao.ledger;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import my.generalledger.domain.ledger.LedgerEntry;

public class LedgerEntryDAOImpl implements LedgerEntryDAO {

	private final SessionFactory sessionFactory;
	
	@Autowired
	public LedgerEntryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveEntry(LedgerEntry entry) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entry);
	}

	@Override
	public LedgerEntry getAccountById(int id) {
		Session session = sessionFactory.getCurrentSession();
		LedgerEntry entry = session.get(LedgerEntry.class, id);
		return entry;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LedgerEntry> getEntries() {
		Session session = sessionFactory.getCurrentSession();
		List<LedgerEntry> list = session.createSQLQuery("SELECT * FROM test.ledger_entry")
				.addEntity(LedgerEntry.class).list();
		return list;
	}

	@Override
	public void updateEntry(LedgerEntry entry) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entry);
	}

	@Override
	public void deleteEntry(LedgerEntry entry) {
		Session session = sessionFactory.getCurrentSession();
		entry = session.get(LedgerEntry.class, entry.getId());
		if (entry != null) {
			session.delete(entry);
		}
	}
}
