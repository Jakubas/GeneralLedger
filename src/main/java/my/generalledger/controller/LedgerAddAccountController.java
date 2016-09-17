package my.generalledger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import my.generalledger.domain.ledger.LedgerAccount.Type;
import my.generalledger.service.ledger.LedgerAccountService;

@Controller
public class LedgerAddAccountController {
	
	private final Logger logger = LoggerFactory.getLogger(LedgerAddAccountController.class);
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger/addaccount", method = RequestMethod.GET)
	public String getAddAccountPage() {
		logger.info("retrieving view for adding new ledger accounts");
		return "ledger/addaccount";
	}
	
	@RequestMapping(value = "/ledger/addaccount", method = RequestMethod.POST)
	public String addAccount(@RequestParam(value = "name") String name,
								   @RequestParam(value = "type") Type type,
							       @RequestParam(value = "number") String number) {
		logger.debug("creating a new ledger account");
		ledgerAccountService.saveAccount(name, type, number);
		return "redirect:/ledger";
	}
}
