package example.simian.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import example.simian.config.SimianConfigurationProperties
import example.simian.model.CountGemsOutput
import example.simian.model.VerifyGemsInput
import example.simian.model.VerifyGemsOutput
import example.simian.repository.DNARepository
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