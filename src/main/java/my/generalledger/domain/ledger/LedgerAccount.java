package my.generalledger.domain.ledger;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class LedgerAccount {
	
	public enum Type {
		CREDIT,
		DEBIT,
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    @NotNull
    @Size(max = 64)
	private String name;
	
    @NotNull
    @Enumerated(EnumType.STRING)
	private Type type;
	
	@NotNull
	@Size(max = 16)
	@Column(unique=true)
	private String number;
		
	protected LedgerAccount() {}
	
	public LedgerAccount(String name, Type type, String number) {
		this.name = name;
		this.type = type;
		this.number = number;
		System.out.println(name);
		System.out.println(type.toString());
		System.out.println(number);
		System.out.println(id);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "LedgerAccount [id=" + id + ", name=" + name + ", type=" + type + ", number=" + number + "]";
	}
}
