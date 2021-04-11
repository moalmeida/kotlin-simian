package simian.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import simian.config.SimianConfigurationProperties
import simian.model.CountGemsOutput
import simian.model.VerifyGemsInput
import simian.model.VerifyGemsOutput
import simian.repository.DNARepository
import javax.inject.Inject

@Controller
class MicronautMainController(
    @Inject private val dnaRepository: DNARepository,
    @Inject private val simianConfigurationProperties: SimianConfigurationProperties
) {

    private val mainController = MainController(
        dnaRepository,
        simianConfigurationProperties.minimalRequiredChar.toInt(),
        simianConfigurationProperties.requiredMatchWords,
        simianConfigurationProperties.minimalMatchSimian.toInt()
    )

    @Post("/simian", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun verifyGems(input: VerifyGemsInput): VerifyGemsOutput = mainController.verifyGems(input)

    @Get("/stats", produces = [MediaType.APPLICATION_JSON])
    fun countGems(): CountGemsOutput = mainController.countGems()

}