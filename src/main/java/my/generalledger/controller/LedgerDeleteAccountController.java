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
import my.generalledger.service.ledger.LedgerAccountService;

@Controller
public class LedgerDeleteAccountController {

	private final static Logger logger = LoggerFactory.getLogger(LedgerDeleteAccountController.class);
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger/accounts/delete", method = RequestMethod.GET)
	public String getDeleteAccountPage(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		logger.info("retrieving view for deleting ledger accounts");
		return "ledger/deleteaccount";
	}
	
	@RequestMapping(value = "/ledger/accounts/delete", method = RequestMethod.DELETE)
	public String deleteAccount(@RequestParam(value="id") int id) {
		LedgerAccount la = ledgerAccountService.getAccountById(id);
		logger.debug("calling service to delete ledger account: " + id);
		ledgerAccountService.deleteAccount(la);
		return "redirect:/ledger";
	}
}
