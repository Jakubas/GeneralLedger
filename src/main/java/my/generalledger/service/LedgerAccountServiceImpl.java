package my.generalledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.generalledger.dao.LedgerAccountDAO;
import my.generalledger.domain.ledger.LedgerAccount;

@Service
public class LedgerAccountServiceImpl implements LedgerAccountService {

	private final LedgerAccountDAO dao;
	
	@Autowired
	public LedgerAccountServiceImpl(LedgerAccountDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public void saveAccount(LedgerAccount ledgerAccount) {
		dao.saveAccount(ledgerAccount);
	}

	@Override
	public LedgerAccount getAccountById(int id) {
		return dao.getAccountById(id);
	}

	@Override
	public LedgerAccount getAccountByNumber(String number) {
		return dao.getAccountByNumber(number);
	}

	@Override
	public List<LedgerAccount> getAccounts() {		
		return dao.getAccounts();
	}

	@Override
	public void updateAccount(LedgerAccount ledgerAccount) {
		dao.updateAccount(ledgerAccount);
	}

	@Override
	public void deleteAccount(LedgerAccount ledgerAccount) {
		dao.deleteAccount(ledgerAccount);
	}
}
