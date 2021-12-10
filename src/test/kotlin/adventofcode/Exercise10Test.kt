package adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise10Test {

    @Test
    fun `exercise 10 silver test`() {
        val exc10 = Exercise10("/test10.txt")
        assertThat(exc10.silverExercise()).isEqualTo(26397)
    }

    @Test
    fun `exercise 10 gold test`() {
        val exc10 = Exercise10("/test10.txt")
        assertThat(exc10.goldExercise()).isEqualTo(288957)
    }
}
