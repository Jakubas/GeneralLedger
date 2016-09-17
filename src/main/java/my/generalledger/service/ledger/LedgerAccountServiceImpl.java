package my.generalledger.service.ledger;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.generalledger.dao.ledger.LedgerAccountDAO;
import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.LedgerAccount.Type;

@Service
public class LedgerAccountServiceImpl implements LedgerAccountService {

	private final static Logger logger = LoggerFactory.getLogger(LedgerAccountServiceImpl.class);
	
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
	public void saveAccount(String name, Type type, String number) {
		LedgerAccount ledgerAccount = new LedgerAccount(name, type, number);
		logger.debug("created ledger account: " + ledgerAccount.getId());
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
