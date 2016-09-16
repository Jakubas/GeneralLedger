package my.generalledger.service.ledger;

import java.util.List;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.LedgerAccount.Type;

public interface LedgerAccountService {

	void saveAccount(LedgerAccount ledgerAccount);
	void saveAccount(String name, Type type, String number);
	LedgerAccount getAccountById(int id);
	LedgerAccount getAccountByNumber(String number);
	List<LedgerAccount> getAccounts();
	void updateAccount(LedgerAccount ledgerAccount);
	void deleteAccount(LedgerAccount ledgerAccount);
}
