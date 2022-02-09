package com.example

import org.junit.jupiter.api.Test

class Item34 {

    /*Telescoping constructor pattern*/
    class Pizza {
        val size: String
        val cheese: Int
        val olives: Int
        val bacon: Int

        constructor(
            size: String, cheese: Int, olives: Int,
            bacon: Int
        ) {
            this.size = size
            this.cheese = cheese
            this.olives = olives
            this.bacon = bacon
        }

        constructor(size: String, cheese: Int, olives: Int) :
                this(size, cheese, olives, 0)

        constructor(size: String, cheese: Int) :
                this(size, cheese, 0)

        constructor(size: String) : this(size, 0)
    }

    class Pizza3(
        val size: String = "L",
        val cheese: Int = 0,
        val olives: Int = 0,
        val bacon: Int = 0
    ) {
        class Builder {
            private val size: String = "L"
            private var cheese: Int = 0
            private var olives: Int = 0
            private var bacon: Int = 0

            fun setCheese(value: Int): Builder = apply {
                cheese = value
            }

            fun setOlives(value: Int): Builder = apply {
                olives = value
            }

            fun setBacon(value: Int): Builder = apply {
                bacon = value
            }

            fun build() = Pizza3(size, cheese, olives, bacon)
        }

        override fun toString(): String {
            return "Pizza3(size='$size', cheese=$cheese, olives=$olives, bacon=$bacon)"
        }

    }

    val myFavorite3 = Pizza3.Builder().setBacon(3).build()

    @Test
    internal fun `print myFavorite3`() {
        println(myFavorite3)
    }

    /*instead, we can use default arguments*/
    class Pizza2(
        val size: String,
        val cheese: Int = 0,
        val olives: Int = 0,
        val bacon: Int = 0
    ) {
        override fun toString(): String {
            return "Pizza2(size='$size', cheese=$cheese, olives=$olives, bacon=$bacon)"
        }
    }

    val myFavorite = Pizza2(size = "L", bacon = 3)

    @Test
    internal fun `print myFavorite`() {
        println(myFavorite)
    }






    class PizzaTower(
        val pizzas: List<Pizza3>
    ) {
        class Builder {
            private val pizzas = mutableListOf<Pizza3>()
            fun addPizza(
                size: String = "L", cheese: Int = 0, olives: Int = 0, bacon: Int = 0
            ): Builder = apply {
                pizzas += Pizza3(size, cheese, olives, bacon)
            }
            fun build() = PizzaTower(pizzas)
        }
        override fun toString(): String {
            return "PizzaTower(pizzas=$pizzas)"
        }
    }

    val pizzaTower = PizzaTower.Builder().addPizza(size = "L", cheese = 4).build()
    val pizzaTower2 = PizzaTower(pizzas = listOf(Pizza3(size = "L", cheese = 3)))

    @Test
    internal fun `print pizzaTower`() {
        println(pizzaTower)
        println(pizzaTower2)
    }
}
