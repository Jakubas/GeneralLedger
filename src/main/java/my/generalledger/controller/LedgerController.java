package my.generalledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.LedgerAccount.Type;
import my.generalledger.service.LedgerAccountService;

@Controller
public class LedgerController {
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger", method = RequestMethod.GET)
	public String getLedger(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		return "ledger";
	}
	
	@RequestMapping(value = "/ledger/addaccount", method = RequestMethod.GET)
	public String getAddAccount() {
		return "addaccount";
	}
	@RequestMapping(value = "/ledger/addaccount", method = RequestMethod.POST)
	public String addAccount(@RequestParam(value = "name") String name,
								   @RequestParam(value = "type") Type type,
							       @RequestParam(value = "number") String number) {
		LedgerAccount la = new LedgerAccount(name, type, number);
		ledgerAccountService.saveAccount(la);
		//TODO redirect fix
		return "redirect:/ledger";
	}
	
	@RequestMapping(value = "/ledger/deleteaccount", method = RequestMethod.GET)
	public String getDeleteAccount(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		return "deleteaccount";
	}
	
	@RequestMapping(value = "/ledger/deleteaccount", method = RequestMethod.POST)
	public String deleteAccount(@RequestParam(value="id") int id) {
		LedgerAccount la = ledgerAccountService.getAccountById(id);
		ledgerAccountService.deleteAccount(la);
		return "redirect:/ledger";
	}
}
