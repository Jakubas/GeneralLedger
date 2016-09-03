package my.generalledger.dao.ledger;

import java.util.List;

import my.generalledger.domain.ledger.LedgerEntry;

public interface LedgerEntryDAO {
	
	void saveEntry(LedgerEntry entry);
	LedgerEntry getAccountById(int id);
	List<LedgerEntry> getEntries();
	void updateEntry(LedgerEntry entry);
	void deleteEntry(LedgerEntry entry);
}