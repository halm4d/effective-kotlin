package com.example

import org.junit.jupiter.api.Test

abstract class BaseUser {
    abstract fun of(id: String): User
}

class User(
    private val id: String,
    private var name: String?
) : Role {

    companion object : BaseUser() {
        override fun of(id: String): User = User(id, null)
    }

    override fun getRole(): String = "READ"

    override fun toString(): String {
        return "User(id='$id', name=$name)"
    }
}

interface Role {
    fun getRole(): String
}

inline fun Role(size: Int, action: (User) -> Any): List<User> {
    val users = mutableListOf<User>()
    for (i in 0 until size) {
        val element = User(i.toString(), null)
        action(element)
        users.add(element)
    }
    return users
}

class UserFactory {
    var nextId: Int = 0
    fun next(name: String? = null) = User(nextId++.toString(), name)
}

class Item33 {

    @Test
    internal fun `basic constructor`() {
        val user = User("1", null)
        println(user)
    }

    @Test
    internal fun `top-level functions`() {
        // perfect choice for small and commonly created objects
        // disadvantage: top-level functions should be named wisely
        fun basicUser(id: String): User = User(id, null) // pl: listOf

        val basicUser = basicUser("1")
        println(basicUser)

//        listOf<String>()
    }

    @Test
    internal fun `companion object factory function`() {
        val withId = User.of("1")
        println(withId)
//        Date.from(Instant.now())
//        EnumSet.of(HttpStatus.OK, HttpStatus.BAD_REQUEST)
//        BigInteger.valueOf(1L)
    }

    @Test
    internal fun `extension factory functions`() {
        // ilyen csak akkor lehet ha legalább egy üres compaion object létre van hozva az osztályban
        fun User.Companion.withName(name: String): User = User("0", name)

        val withName = User.withName("name")
        println(withName)
    }

    @Test
    internal fun `fake constructors`() {
        // they are top-level functions under the hood. This is why they are often called fake constructors.
        Role(2) { println(it) }
//        List(4) { "Users$it" }
    }

    @Test
    internal fun `methods on a factory class`() {
        val userFactory = UserFactory()
        val u1 = userFactory.next()
        println(u1)
        val u2 = userFactory.next()
        println(u2)
    }
}
