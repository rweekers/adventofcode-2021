package adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Exercise09Test {

    @Test
    fun `exercise 9 silver test`() {
        val exc9 = Exercise09("/test09.txt")
        assertThat(exc9.silverExercise()).isEqualTo(15)
    }

    @Test
    fun `exercise 9 gold test`() {
        val exc9 = Exercise09("/test09.txt")
        assertThat(exc9.goldExercise()).isEqualTo(1134)
    }
}
