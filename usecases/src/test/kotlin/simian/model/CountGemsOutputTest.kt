package simian.model


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CountGemsOutputTest {

    @Test
    fun onCreate_WithCorrectParams_ReturnsExpectedOutput() {

        val expectedRatioOutput = 0.5
        val countGemsOutput = CountGemsOutput(1, 2)

        assertEquals(expectedRatioOutput, countGemsOutput.ratio)

    }


}