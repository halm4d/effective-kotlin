package com.example

import org.junit.jupiter.api.Test

class DSL {

    val p = pizza {
        slice {
            size = "L"
            cheese = 1
            olives = 2
            bacon = 3
        }
        slice(olives = 3) {
            size = "L"
            olives = 4
        }
        slice("L", 4)
        slice(size = "L", bacon = 1)
        for (i in 0 until 5) {
            slice { cheese = i }
        }
    }

    @Test
    internal fun `print p`() {
        println(p)
    }
}

data class Slice(
    val size: String = "L",
    val cheese: Int = 0,
    val olives: Int = 0,
    val bacon: Int = 0
) {
    class SliceBuilder(
        var size: String = "L",
        var cheese: Int = 0,
        var olives: Int = 0,
        var bacon: Int = 0
    ) {
        fun build(): Slice {
            return Slice(size, cheese, olives, bacon)
        }
    }
}

data class Pizza(val slices: List<Slice>) {
    class PizzaBuilder {
        private val slices = mutableListOf<Slice>()
        fun build(): Pizza {
            return Pizza(slices)
        }
        fun slice(
            size: String = "L", cheese: Int = 0, olives: Int = 0, bacon: Int = 0, init: Slice.SliceBuilder.() -> Unit = {}
        ) {
            val sliceBuilder = Slice.SliceBuilder(size, cheese, olives, bacon)
            sliceBuilder.init()
            slices += sliceBuilder.build()
        }
    }
}

fun pizza(init: Pizza.PizzaBuilder.() -> Unit): Pizza {
    val pizzaBuilder = Pizza.PizzaBuilder()
    pizzaBuilder.init()
    return pizzaBuilder.build()
}