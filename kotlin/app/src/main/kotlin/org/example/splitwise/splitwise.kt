package org.example.splitwise

import java.util.UUID

// interface
interface SplitWise{
    fun createGroup(name: String): String
    fun getGroup(id: String): Group
}

// Models
data class Group(
    val id: String,
    val name: String
)

// class

class SplitWiseApp: SplitWise {
    private val groups: MutableList<Group> = mutableListOf()
    override fun createGroup(name: String): String {
        val id = UUID.randomUUID().toString()
        groups.add(Group(id, name))
        return id
    }

    override fun getGroup(id: String): Group {
        return groups.find { it.id == id }!!
    }

}
