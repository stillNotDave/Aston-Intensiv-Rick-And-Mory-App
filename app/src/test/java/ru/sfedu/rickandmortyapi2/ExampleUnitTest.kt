package ru.sfedu.rickandmortyapi2

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    @Throws(Exception::class)
    fun addition_isNotCorrect() {
        assertNotEquals("Numbers isn't equals!", 5, 2 + 2)
    }

    @Test(expected = NullPointerException::class)
    fun nullStringTest() {
        val str: String? = null
        assertTrue(str!!.isEmpty())
    }

}