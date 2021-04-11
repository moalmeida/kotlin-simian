package simian.repository

import simian.entity.DNA
import simian.entity.Gems
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InMemoryDNARepositoryTest {

    private val dnaRepository: DNARepository = InMemoryDNARepository.INSTANCE
    private val dna = arrayListOf("AAAA", "TTTT", "CCCC", "GGGG")

    @Test
    fun generateId_WithCorrectParams_ReturnsOK() {
        val expectedOutput = "AAAATTTTCCCCGGGG"

        assertEquals(expectedOutput, dnaRepository.generateId(dna))
    }

    @Test
    fun create_WithCorrectParams_ReturnsOK() {
        val id = dnaRepository.generateId(dna)
        val model = DNA(id, dna, Gems.SIMIAN)

        dnaRepository.create(model)
        val output = dnaRepository.findById(id)

        assertEquals(output, model)
    }

    @Test
    fun countByGems_WithCorrectParams_ReturnsOK() {
        assertEquals(0, dnaRepository.countByGems(Gems.HUMAN))
        assertEquals(1, dnaRepository.countByGems(Gems.SIMIAN))
    }

}