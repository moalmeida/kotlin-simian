package example.simian.usecase

import example.simian.entity.DNA
import example.simian.entity.Gems
import example.simian.entity.Nitro
import example.simian.exception.NotContainsOnlyNitroWordsException
import example.simian.model.VerifyGemsInput
import example.simian.model.VerifyGemsOutput
import example.simian.repository.DNARepository
import example.simian.util.StreamsTransform
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VerifyGemsUseCase(
    private val dnaRepository: DNARepository,
    private val minimalRequiredChar: Int,
    private val requiredMatchWords: List<String>,
    private val minimalMatchSimian: Int
) : UseCase<VerifyGemsInput, VerifyGemsOutput> {

    private val log: Logger = LoggerFactory.getLogger(VerifyGemsUseCase::class.java)

    fun execute(input: VerifyGemsInput): VerifyGemsOutput {

        validateContainsOnlyNitroWords(input.dna)

        val isSimian = verifyIsSimian(input.dna)

        createOnDatabase(input.dna, isSimian)

        log.info("[VerifyGemsOutput] isSimian {}", isSimian)
        return VerifyGemsOutput(isSimian)
    }

    private fun validateContainsOnlyNitroWords(dna: List<String>) {
        val receiptChars: List<String> =
            dna.joinToString("")
                .toList().map { it.toString().toUpperCase() }
                .distinct()
                .sorted()
        if (!receiptChars.deepEquals(Nitro.values().map { it.toString() }.sorted())) {
            throw NotContainsOnlyNitroWordsException("ERROR: $receiptChars")
        }
    }

    private fun verifyIsSimian(dna: List<String>): Boolean =
        StreamsTransform.countAllMatchingSlots(dna, minimalRequiredChar, requiredMatchWords) >= minimalMatchSimian


    private fun createOnDatabase(dna: List<String>, isSimian: Boolean) {
        val id = dnaRepository.generateId(dna)
        if (dnaRepository.findById(id) === null) {
            dnaRepository.create(
                DNA(
                    id,
                    dna,
                    when {
                        isSimian -> Gems.SIMIAN
                        else -> Gems.HUMAN
                    }
                )
            );
        }
    }


}

fun List<*>.deepEquals(other: List<*>) =
    this.size == other.size && this.mapIndexed { index, element -> element == other[index] }.all { it }