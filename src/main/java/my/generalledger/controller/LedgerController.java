package my.generalledger.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.service.ledger.LedgerAccountService;

@Controller
public class LedgerController {
	
	private final static Logger logger = LoggerFactory.getLogger(LedgerController.class);
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	
	@RequestMapping(value = "/ledger", method = RequestMethod.GET)
	public String getLedger(Model model) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		logger.info("retrieving view for displaying ledger accounts");
		return "ledger/ledger";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLanding() {
		return "index";
	}
}
