package org.example.atm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AtmTest{
    private val accountService = AccountService()
    private val cardService = CardService(accountService)
    private val transactionService = TransactionService()
    private val authService = AuthService(accountService, cardService)
    private val atmService = AtmServiceImpl(accountService, authService, transactionService)
    private val accountNumber = "123"
    @Test
    fun `atm works`(){
        assertEquals(atmService.login(accountNumber,"12345", 1234).first, true)
        assertEquals(atmService.getBalance(accountNumber), 100)
        atmService.deposit(accountNumber, 200)
        assertEquals(atmService.getBalance(accountNumber), 300)
        atmService.withdraw(accountNumber, 100)
        assertEquals(atmService.getBalance(accountNumber), 200)
    }
}