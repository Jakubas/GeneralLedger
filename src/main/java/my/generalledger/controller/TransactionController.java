package my.generalledger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.service.ledger.LedgerAccountService;
import my.generalledger.service.ledger.TransactionService;

@Controller
public class TransactionController {
	
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
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		System.out.println(cal.toString());
		LedgerAccount creditAccount = ledgerAccountService.getAccountById(creditId);
		LedgerAccount debitAccount = ledgerAccountService.getAccountById(debitId);
		amountStr = amountStr.replace(".", "");
		int amount = Integer.valueOf(amountStr);
		transactionService.saveTransaction(cal, description, amount, creditAccount, debitAccount);
		System.out.println(accountId);
		System.out.println("--------------------------------------------------------------------------");
		int id = Integer.valueOf(accountId);
		redir.addAttribute("id", id);
	    return "redirect:/ledger/account";
	}
}
