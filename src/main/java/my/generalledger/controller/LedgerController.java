package my.generalledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.service.ledger.LedgerAccountService;

@Controller
public class LedgerController {
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger", method = RequestMethod.GET)
	public String getLedger(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		return "ledger/ledger";
	}
	
	@RequestMapping(value = "/ledger/account", method = RequestMethod.GET)
	public String getAccount(@RequestParam(value = "id") int id, Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		
		LedgerAccount account = ledgerAccountService.getAccountById(id);
		model.addAttribute("ledgerAccount", account);
		model.addAttribute("debitEntries", account.getDebitEntries());
		model.addAttribute("creditEntries", account.getCreditEntries());
		return "ledger/account";
	}
}
