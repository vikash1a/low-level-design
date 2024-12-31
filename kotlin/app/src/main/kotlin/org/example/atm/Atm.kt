package org.example.atm

interface AtmService{
    fun login(accountNumber: String, cardNumber: String, pin: Int): Pair<Boolean, String?>
    fun getBalance(accountNumber: String): Int
    fun withdraw(accountNumber: String, amount: Int)
    fun deposit(accountNumber: String, amount: Int)
}

class AtmServiceImpl(
    private val accountService: AccountService,
    private val authService: AuthService,
    private val transactionService: TransactionService
): AtmService{
    override fun login(accountNumber: String, cardNumber: String, pin: Int): Pair<Boolean, String?>{
        return authService.login(accountNumber, cardNumber, pin)
    }
    override fun getBalance(accountNumber: String): Int {
        return accountService.getBalance(accountNumber)
    }

    override fun withdraw(accountNumber: String, amount: Int) {
        synchronized(Any()){
            val account = accountService.getAccount(accountNumber)
            transactionService.addCreditTransaction(account,amount)
            accountService.updateBalance(account, -amount)
        }
    }

    override fun deposit(accountNumber: String, amount: Int) {
        synchronized(Any()) {
            val account = accountService.getAccount(accountNumber)
            transactionService.addDebitTransaction(account,amount)
            accountService.updateBalance(account, amount)
        }
    }
}

class AuthService(
    private val accountService: AccountService,
    private val cardService: CardService
){
    fun login(accountNumber: String, cardNumber: String, pin: Int): Pair<Boolean, String?>{
        val account = accountService.getAccount(accountNumber)
        val card = cardService.getCard(account)
        return if(card.cardNumber == cardNumber && card.pin == pin){
            Pair(true, createJwtToken())
        } else{
            Pair(false, null)
        }
    }

    private fun createJwtToken(): String{
        return "dummy" // dummy
    }

    private fun validateJwtToken(token: String): Boolean{
        return true // dummy
    }
}

// Dao Service
class AccountService() {
    private val accounts: MutableList<Account> = mutableListOf()
    init { seed()}
    private fun seed(){
        accounts.add(Account("123",100))
    }
    fun getBalance(accountNumber: String): Int{
        return accounts.find { it.accountNumber == accountNumber }?.balance ?: throw Exception("username not found")
    }
    fun updateBalance(account: Account, amount: Int){
        account.balance += amount;
    }
    fun getAccount(accountNumber: String): Account{
        return accounts.find { it.accountNumber == accountNumber }?: throw Exception("username not found")
    }
}

class CardService(
    private val accountService: AccountService
){
    private val cards: MutableList<Card> = mutableListOf()
    init { seed()}
    private fun seed(){
        cards.add(Card(accountService.getAccount("123"),"12345",1234))
    }
    fun getCard(account: Account): Card{
        return cards.find { it.account == account }?: throw Exception("account card not found")
    }
}

class TransactionService{
    private val transactions: MutableList<Transaction> = mutableListOf()
    fun addCreditTransaction(account: Account, amount: Int){
        transactions.add(Transaction(account, TransactionType.CREDIT, amount))
    }
    fun addDebitTransaction(account: Account, amount: Int){
        transactions.add(Transaction(account, TransactionType.DEBIT, amount))
    }
}


// models
data class Account(
    val accountNumber: String,
    var balance: Int
)

data class Transaction(
    val account: Account,
    val transactionType: TransactionType,
    val amount: Int
)

data class Card(
    val account: Account,
    val cardNumber: String,
    val pin: Int
)

enum class TransactionType{
    DEBIT,
    CREDIT
}