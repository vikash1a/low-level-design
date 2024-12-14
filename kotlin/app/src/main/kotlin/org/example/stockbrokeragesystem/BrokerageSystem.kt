package org.example.stockbrokeragesystem

import java.time.LocalDateTime
import java.util.UUID

/*
TODO
1. Settler Job
2. Job Which update company service
3. Executer design pattern -> decoupled vs completely independent
 */

class BrokerageSystem(
    private val accountService: AccountService,
    private val transactionService: TransactionService,
    private val companyService: CompanyService
) {
    fun createAccount(userName: String){
        accountService.createAccount(userName)
    }

    fun viewPrice(){
        companyService.viewPrice()
    }

    fun buy(account: Account, company: Company, quantity: Int){
        transactionService.buy(account, company, quantity)
    }

    fun sell(account: Account, company: Company, quantity: Int){
        transactionService.sell(account, company, quantity)
    }
}


// service

class AccountService{
    private val accounts: MutableMap<String, Account> = mutableMapOf()

    fun createAccount(userName: String){
        accounts[userName] = Account(userName, 0)
    }
}

class TransactionService(
    private val portfolioService: PortfolioService,
    private val executorService: ExecutorService
){
    private val transactions: MutableMap<UUID, Transaction> = mutableMapOf()

    fun buy(account: Account, company: Company, quantity: Int){
        val transaction = Transaction(UUID.randomUUID(), TransactionType.BUY, account, company, quantity, LocalDateTime.now(), TransactionStatus.PLACED)
        transactions[transaction.id] = transaction
        executorService.executeBuy(transaction)
    }

    fun sell(account: Account, company: Company, quantity: Int){
        if(portfolioService.get(account).companies[company]==null)throw Exception("Cannot sell, company not in portfolio")
        if(portfolioService.get(account).companies[company]!! < quantity)throw Exception("Cannot sell, not sufficient quantity")
        val transaction = Transaction(UUID.randomUUID(), TransactionType.SELL, account, company, quantity, LocalDateTime.now(), TransactionStatus.PLACED)
        transactions[transaction.id] = transaction
        executorService.executeSell(transaction)
    }

    fun viewHistory(){
        TODO()
    }
}

class PortfolioService{
    private val portfolios: MutableMap<Account, Portfolio> = mutableMapOf()

    fun view(account: Account){
        val portfolio = portfolios[account]!!
        println("Total value: ${portfolio.currentValue}")
        portfolio.companies.forEach{
            println("company - ${it.key.name}, quantity - ${it.value}")
        }
    }

    fun update(account: Account, company: Company, quantity: Int){
        val portfolio = portfolios[account]!!
        if(portfolio.companies[company] == null)portfolio.companies[company] = quantity
        else portfolio.companies[company] = portfolio.companies[company]!! + quantity
    }
    fun get(account: Account) = portfolios[account]!!
}

class CompanyService{
    private val companies: MutableList<Company> = mutableListOf()

    fun viewPrice(){
        companies.forEach {
            println("company - ${it.name}, price- ${it.currentPrice}")
        }
    }
}

class ExecutorService(
    private val portfolioService: PortfolioService
){
    fun executeBuy(transaction: Transaction){
        portfolioService.update(transaction.account, transaction.company, transaction.quantity)
        transaction.status = TransactionStatus.EXECUTED
    }
    fun executeSell(transaction: Transaction){
        portfolioService.update(transaction.account, transaction.company, -transaction.quantity)
        transaction.status = TransactionStatus.EXECUTED
    }
}

// Models

data class Account(
    val userName: String,
    val cashAmount: Int
)

data class Transaction(
    val id: UUID,
    val type: TransactionType,
    val account: Account,
    val company: Company,
    val quantity: Int,
    val time: LocalDateTime,
    var status: TransactionStatus
)

enum class TransactionStatus{
    PLACED,
    EXECUTED,
    SETTLED
}

enum class TransactionType {
    BUY,
    SELL
}

data class Company(
    val name: String,
    val currentPrice: Int
)

data class Portfolio(
    val account: Account,
    val currentValue: Int,
    val companies: MutableMap<Company, Int> // company quantity
)
