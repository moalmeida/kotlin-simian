package example.simian.controller

import example.simian.entity.Gems
import example.simian.exception.NotContainsOnlyNitroWordsException
import example.simian.model.VerifyGemsInput
import example.simian.model.VerifyGemsOutput
import example.simian.repository.DNARepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class MainControllerTest {

    private val minimalRequiredChar = 4
    private val requiredMatchWords = arrayListOf("AAAA", "TTTT", "CCCC", "GGGG")
    private val minimalMatchSimian = 2

    @Test
    fun verifyGems_WithSimianParams_ReturnsTrue() {

        val verifyGemsInput = VerifyGemsInput(arrayListOf("CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"))
        val expectedOutput = VerifyGemsOutput(true)
        val dnaRepository = mock<DNARepository> {
            on {
                findById(any())
            } doReturn null
            on {
                generateId(any())
            } doReturn "fake-uuid"
        }

        val mainController = MainController(
            dnaRepository,
            minimalRequiredChar,
            requiredMatchWords,
            minimalMatchSimian
        )

        val output = mainController.verifyGems(verifyGemsInput)

        assertEquals(expectedOutput, output)

    }

    @Test
    fun verifyGems_WithSimianParams_ThrowsError() {

        val verifyGemsInput = VerifyGemsInput(arrayListOf("QQQQQQ", "WWWWWW", "EEEEEE", "RRRRRR", "TTTTTT", "YYYYYY"))
        val dnaRepository = mock<DNARepository>()
        val mainController = MainController(
            dnaRepository,
            minimalRequiredChar,
            requiredMatchWords,
            minimalMatchSimian
        )

        assertThrows<NotContainsOnlyNitroWordsException> { mainController.verifyGems(verifyGemsInput) }
    }

    @Test
    fun verifyGems_WithHumanParams_ReturnsFalse() {

        val verifyGemsInput = VerifyGemsInput(arrayListOf("ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"))
        val expectedOutput = VerifyGemsOutput(false)
        val dnaRepository = mock<DNARepository> {
            on {
                findById(any())
            } doReturn null
            on {
                generateId(any())
            } doReturn "fake-uuid"
        }

        val mainController = MainController(
            dnaRepository,
            minimalRequiredChar,
            requiredMatchWords,
            minimalMatchSimian
        )

        val output = mainController.verifyGems(verifyGemsInput)

        assertEquals(expectedOutput, output)

    }

    @Test
    fun countGems_WithoutParams_ReturnsRatio05() {

        val dnaRepository = mock<DNARepository> {
            on {
                countByGems(Gems.SIMIAN)
            } doReturn 1
            on {
                countByGems(Gems.HUMAN)
            } doReturn 2
        }

        val mainController = MainController(
            dnaRepository,
            minimalRequiredChar,
            requiredMatchWords,
            minimalMatchSimian
        )

        val output = mainController.countGems()

        assertEquals(0.5, output.ratio)

    }

    @Test
    fun countGems_WithoutParams_ReturnsRatio00() {

        val dnaRepository = mock<DNARepository> {
            on {
                countByGems(Gems.SIMIAN)
            } doReturn 0
            on {
                countByGems(Gems.HUMAN)
            } doReturn 2
        }

        val mainController = MainController(
            dnaRepository,
            minimalRequiredChar,
            requiredMatchWords,
            minimalMatchSimian
        )

        val output = mainController.countGems()

        assertEquals(0.0, output.ratio)

    }

}