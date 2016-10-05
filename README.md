# General Ledger

A general ledger is a company's set of numbered accounts for its accounting records. The ledger provides
a complete record of financial transactions over the life of the company. The ledger holds account 
information that is needed to prepare financial statements and includes accounts for assets, liabilities, 
owners' equity, revenues and expenses. (Source: http://www.investopedia.com/terms/g/generalledger.asp)

This project is a general ledger web application. Users can add accounts and transactions to the
general ledger. Transactions are added in accordance to the double-entry bookkeeping system, 
where every transaction has to be recorded twice, once as a debit entry and once as a credit entry.

Accounts can be deleted if they have no transactions. Transactions can be deleted or updated. When a transaction is deleted or updated then both corresponding entries are modified as well.
 
A user can view the trial balance, which is a statement of all credits and debits in the general ledger. 
The trial balance and general ledger can be viewed in the application and they can be exported to a .csv file.

## Technologies Used
- Java, HTML, CSS
- Spring(Spring Boot, Spring MVC)
- JPA/Hibernate
- MySQL
- SLF4J
- Thymeleaf
- Bootstrap
