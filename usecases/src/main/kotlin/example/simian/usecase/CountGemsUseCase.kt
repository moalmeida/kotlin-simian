package example.simian.usecase

import example.simian.entity.Gems
import example.simian.model.CountGemsOutput
import example.simian.repository.DNARepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CountGemsUseCase(
    private val dnaRepository: DNARepository
) : UseCase<Void, CountGemsOutput> {

    private val log: Logger = LoggerFactory.getLogger(CountGemsUseCase::class.java)

    fun execute(): CountGemsOutput {
        val countSimianDNA = countByGemsOnDatabase(Gems.SIMIAN)
        val countHumanDNA = countByGemsOnDatabase(Gems.HUMAN)
        log.info("[CountGemsOutput] countSimianDNA {} countHumanDNA {}", countSimianDNA, countHumanDNA)
        return CountGemsOutput(
            countSimianDNA,
            countHumanDNA
        )
    }

    private fun countByGemsOnDatabase(gems: Gems): Int = dnaRepository.countByGems(gems)

}