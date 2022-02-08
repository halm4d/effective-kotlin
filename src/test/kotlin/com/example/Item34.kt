package com.example

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

    /*instead, we can use default arguments*/
    class Pizza2(
        val size: String,
        val cheese: Int = 0,
        val olives: Int = 0,
        val bacon: Int = 0
    )

    val myFavorite = Pizza2(size = "L", bacon = 3)


    class Pizza3 private constructor(
        val size: String,
        val cheese: Int,
        val olives: Int,
        val bacon: Int
    ) {
        class Builder(private val size: String) {
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

            fun build() = Pizza(size, cheese, olives, bacon)
        }
    }

    val myFavorite3 = Pizza3.Builder("L").setBacon(3).build()
}