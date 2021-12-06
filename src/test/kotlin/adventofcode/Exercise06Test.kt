package adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise06Test {

    @Test
    fun `exercise 6 silver test`() {
        val exc6 = Exercise06("/test06.txt")
        assertThat(exc6.solve(18)).isEqualTo(26)
        assertThat(exc6.solve(80)).isEqualTo(5934)
    }

    @Test
    fun `exercise 6 gold test`() {
        val exc6 = Exercise06("/test06.txt")
        assertThat(exc6.solve(256)).isEqualTo(26984457539)
    }
}
