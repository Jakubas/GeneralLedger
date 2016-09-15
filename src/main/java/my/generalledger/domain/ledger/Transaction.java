package my.generalledger.domain.ledger;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar date;
	
	@NotNull
	@Size(max = 128)
	private String description;
	
	@NotNull
	private int amount;
	
	//the credit entry for this transaction
	@ManyToOne
	@JoinColumn(name = "credit_account_id")
	private LedgerAccount creditAccount;
	
	//the debit entry for this transaction
	@ManyToOne
	@JoinColumn(name = "debit_account_id")
	private LedgerAccount debitAccount;
	
	protected Transaction() {}
	
	public Transaction(Calendar date, String description, int amount, 
			LedgerAccount creditAccount, LedgerAccount debitAccount) {
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.creditAccount = creditAccount;
		this.debitAccount = debitAccount;
	}

	public int getId() {
		return id;
	}
	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public LedgerAccount getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(LedgerAccount creditAccount) {
		this.creditAccount = creditAccount;
	}

	public LedgerAccount getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(LedgerAccount debitAccount) {
		this.debitAccount = debitAccount;
	}
	
	
	public String amountToString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
		String s = n.format(amount / 100.0);
		return s;
	}
}
