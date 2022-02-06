package com.example

import org.junit.jupiter.api.Test

abstract class BaseUser {
    abstract fun of(id: String):User
}

class User(
    private val id: String,
    private var name: String?
) {

    companion object: BaseUser() {
        override fun of(id: String): User = User(id, null)
    }

    override fun toString(): String {
        return "User(id='$id', name=$name)"
    }
}




class Item33 {

    @Test
    internal fun name() {
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

    private var instance: User? = null
    private fun singletonUser(id: String): User = instance ?: User(id, null).also { instance = it }

    @Test
    internal fun `singleton user`() {
        val singletonUser = singletonUser("1")
        println(singletonUser)
        val singletonUser2 = singletonUser("2")
        println(singletonUser2)
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
        User(2) { println(it) }
    }
}

inline fun User(size: Int, action: (User) -> Any): MutableList<User> {
    val users = mutableListOf<User>()
    for (i in 0 until size) {
        val element = User(i.toString(), null)
        action(element)
        users.add(element)
    }
    return users
}
