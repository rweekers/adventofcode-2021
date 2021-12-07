package adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise07Test {

    @Test
    fun `exercise 7 silver test`() {
        val exc7 = Exercise07("/test07.txt")
        assertThat(exc7.solve()).isEqualTo(37)
    }

    @Test
    fun `exercise 7 gold test`() {
        val exc7 = Exercise07("/test07.txt")
        assertThat(exc7.solve(true)).isEqualTo(168)
    }
}
