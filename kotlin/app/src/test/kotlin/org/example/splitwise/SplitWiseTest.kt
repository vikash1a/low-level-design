package org.example.splitwise

import kotlin.test.Test
import kotlin.test.assertEquals

class SplitWiseTest {
    @Test
    fun `SplitWise app works`(){
        val name = "Kerala"
        val splitWise = SplitWiseApp()
        val id = splitWise.createGroup(name)
        assertEquals(name, splitWise.getGroup(id).name)
    }
}