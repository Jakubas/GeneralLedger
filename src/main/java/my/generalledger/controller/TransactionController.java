package my.generalledger.controller;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.domain.ledger.Transaction;
import my.generalledger.service.ledger.LedgerAccountService;
import my.generalledger.service.ledger.TransactionService;

@Controller
public class TransactionController {
	
	private final static Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/ledger/account/addtransaction", method = RequestMethod.POST)
	public String addTransaction(RedirectAttributes redir,
								 @RequestParam(value = "accountId") String accountId,
			                     @RequestParam(value = "date") String date,
								 @RequestParam(value = "description") String description,
								 @RequestParam(value = "amount") String amountStr,
								 @RequestParam(value = "creditId") int creditId,
								 @RequestParam(value = "debitId") int debitId) {
		Calendar cal = Utils.stringToCalendar(date);
		LedgerAccount creditAccount = ledgerAccountService.getAccountById(creditId);
		LedgerAccount debitAccount = ledgerAccountService.getAccountById(debitId);
		int amount = Utils.CurrencyStringToInt(amountStr);
		logger.debug("creating a new transaction");
		transactionService.saveTransaction(cal, description, amount, creditAccount, debitAccount);
		int id = Integer.valueOf(accountId);
		redir.addAttribute("id", id);
		logger.debug("redirecting to account page");
	    return "redirect:/ledger/account";
	}
	
	@RequestMapping(value = "/ledger/account/updatetransaction", method = RequestMethod.GET)
	public String updateTransaction(Model model,
									@RequestParam(value = "redirAccountId") int redirAccountId,
									@RequestParam(value = "transactionId") int transactionId,
			                     	@RequestParam(value = "creditId") int creditId,
			                     	@RequestParam(value = "debitId") int debitId) {
		List<LedgerAccount> accounts = ledgerAccountService.getAccounts();
		model.addAttribute("ledgerAccounts", accounts);
		LedgerAccount redirAccount = ledgerAccountService.getAccountById(redirAccountId);
		model.addAttribute("redirAccount", redirAccount);
		Transaction transaction = transactionService.getTransactionById(transactionId);
		model.addAttribute("transaction", transaction);
		LedgerAccount creditAccount = ledgerAccountService.getAccountById(creditId);
		model.addAttribute("creditAccount", creditAccount);
		LedgerAccount debitAccount = ledgerAccountService.getAccountById(debitId);
		model.addAttribute("debitAccount", debitAccount);
		logger.info("retrieving view for updating transactions");
		return "/ledger/updatetransaction";
	}
	
	@RequestMapping(value = "/ledger/account/updatetransaction", method = RequestMethod.PUT)
	public String updateTransaction(RedirectAttributes redir,
									@RequestParam(value = "accountId") int accountId,
									@RequestParam(value = "transactionId") int transactionId,
			                     	@RequestParam(value = "date") String date,
			                     	@RequestParam(value = "description") String description,
			                     	@RequestParam(value = "amount") String amountStr,
			                     	@RequestParam(value = "creditId") int creditId,
			                     	@RequestParam(value = "debitId") int debitId) {
		
		Calendar cal = Utils.stringToCalendar(date);
		int amount = Utils.CurrencyStringToInt(amountStr);
		LedgerAccount creditAccount = ledgerAccountService.getAccountById(creditId);
		LedgerAccount debitAccount = ledgerAccountService.getAccountById(debitId);
		logger.debug("calling service to update transaction: " + transactionId);
		transactionService.updateTransaction(transactionId, cal, description, 
				amount, creditAccount, debitAccount);
		
		redir.addAttribute("id", accountId);
		//redirect to the account page from where transaction update was initiated
		return "redirect:/ledger/account";
	}
	
	@RequestMapping(value = "/ledger/account/deletetransaction", method = RequestMethod.DELETE)
	public String deleteTransaction(RedirectAttributes redir,
									@RequestParam(value = "accountId") int accountId,
			                        @RequestParam(value = "transactionId") int transactionId) {
		logger.debug("calling service to delete transaction: " + transactionId);
		transactionService.deleteTransaction(transactionId);
		redir.addAttribute("id", accountId);
		return "redirect:/ledger/account";
	}
}
