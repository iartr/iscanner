package io.github.sample

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(listOf(1, 2, 3, 4).map { item -> item * 2 })
    }

    fun <T, R> List<T>.map(mapper: (item: T) -> R): List<R> {
        val result = mutableListOf<R>()
        for (item in this) {
            result.add(mapper(item))
        }
        return result
    }
}
