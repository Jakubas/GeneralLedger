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
public class LedgerDeleteAccountController {

	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger/deleteaccount", method = RequestMethod.GET)
	public String getDeleteAccountPage(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		return "ledger/deleteaccount";
	}
	
	@RequestMapping(value = "/ledger/deleteaccount", method = RequestMethod.POST)
	public String deleteAccount(@RequestParam(value="id") int id) {
		LedgerAccount la = ledgerAccountService.getAccountById(id);
		ledgerAccountService.deleteAccount(la);
		return "redirect:/ledger";
	}
}
