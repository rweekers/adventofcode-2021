package adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise05Test {

    @Test
    fun `exercise 5 silver test`() {
        val exc5 = Exercise05("/test05.txt")
        assertThat(exc5.solve { it.first.isVerticalOrHorizontalWith(it.second) }).isEqualTo(5)
    }

    @Test
    fun `exercise 5 gold test`() {
        val exc5 = Exercise05("/test05.txt")
        assertThat(exc5.solve { it.first.isVerticalOrHorizontalWith(it.second) || it.first.isDiagonalTo(it.second) }).isEqualTo(12)
    }
}
