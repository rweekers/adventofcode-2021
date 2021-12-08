package adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise08Test {

    @Test
    fun `exercise 8 silver test`() {
        val exc8 = Exercise08("/test08.txt")
        assertThat(exc8.silverExercise()).isEqualTo(26)
    }

    @Test
    fun `exercise 8 gold test`() {
        val exc8 = Exercise08("/test08.txt")
        assertThat(exc8.goldExercise()).isEqualTo(61229)
    }
}
