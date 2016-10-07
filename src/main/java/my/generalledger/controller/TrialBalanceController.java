package my.generalledger.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.generalledger.domain.ledger.Transaction;
import my.generalledger.service.ledger.TransactionService;
import my.generalledger.service.ledger.TrialBalance;

@Controller
public class TrialBalanceController {
	
	private final static Logger logger = LoggerFactory.getLogger(TrialBalanceController.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TrialBalance trialBalanceService;
	
	@RequestMapping(value = "/ledger/trialbalance", method = RequestMethod.GET)
	public String getTrialBalance(Model model) {
		List<Transaction> transactions = transactionService.getTransactions();
		int balance = trialBalanceService.calculateBalance(transactions);
		Set<Transaction> debitTransactions =
				trialBalanceService.getDebitTransactions(transactions);
		Set<Transaction> creditTransactions =
				trialBalanceService.getCreditTransactions(transactions);
		model.addAttribute("balance", balance);
		model.addAttribute("debitTransactions", debitTransactions);
		model.addAttribute("creditTransactions", creditTransactions);
		logger.info("retrieving view for displaying trial balance");
		return "ledger/trialbalance";
	}
}
