package my.generalledger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import my.generalledger.domain.ledger.LedgerAccount;
import my.generalledger.service.ledger.LedgerAccountService;
import my.generalledger.service.ledger.TransactionService;

@Controller
public class LedgerController {
	
	@Autowired
	private LedgerAccountService ledgerAccountService;
	@Autowired
	private TransactionService transactionService;
	
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
	
	@RequestMapping(value = "/ledger/account/addtransaction", method = RequestMethod.POST)
	public String addTransaction(@RequestParam(value = "date") String date,
								 @RequestParam(value = "description") String description,
								 @RequestParam(value = "amount") int amount,
								 @RequestParam(value = "creditId") int creditId,
								 @RequestParam(value = "debitId") int debitId) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(cal.toString());
		LedgerAccount creditAccount = ledgerAccountService.getAccountById(creditId);
		LedgerAccount debitAccount = ledgerAccountService.getAccountById(debitId);
		transactionService.saveTransaction(cal, description, amount, creditAccount, debitAccount);
		return "ledger/account";
	}
}
