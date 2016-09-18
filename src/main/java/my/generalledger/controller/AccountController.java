package my.generalledger.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.LedgerAccount.Type;
import my.generalledger.service.ledger.LedgerAccountService;

@Controller
public class AccountController {

	private final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger/accounts", method = RequestMethod.GET)
	public String getAccount(@RequestParam(value = "id") int id, Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		
		LedgerAccount account = ledgerAccountService.getAccountById(id);
		model.addAttribute("ledgerAccount", account);
		model.addAttribute("debitEntries", account.getDebitEntries());
		model.addAttribute("creditEntries", account.getCreditEntries());
		logger.info("retrieving view for displaying account info and entries");
		return "ledger/account";
	}
	
	@RequestMapping(value = "/ledger/accounts/add", method = RequestMethod.GET)
	public String getAddAccount() {
		logger.info("retrieving view for adding new ledger accounts");
		return "ledger/addaccount";
	}
	
	@RequestMapping(value = "/ledger/accounts/delete", method = RequestMethod.GET)
	public String getDeleteAccount(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		logger.info("retrieving view for deleting ledger accounts");
		return "ledger/deleteaccount";
	}
	
	@RequestMapping(value = "/ledger/accounts/add", method = RequestMethod.POST)
	public String addAccount(@RequestParam(value = "name") String name,
								   @RequestParam(value = "type") Type type,
							       @RequestParam(value = "number") String number) {
		logger.debug("creating a new ledger account");
		ledgerAccountService.saveAccount(name, type, number);
		return "redirect:/ledger";
	}
	
	@RequestMapping(value = "/ledger/accounts/delete", method = RequestMethod.DELETE)
	public String deleteAccount(@RequestParam(value="id") int id) {
		LedgerAccount la = ledgerAccountService.getAccountById(id);
		logger.debug("calling service to delete ledger account: " + id);
		ledgerAccountService.deleteAccount(la);
		return "redirect:/ledger";
	}
}
