package example.simian.controller

import example.simian.model.CountGemsOutput
import example.simian.model.VerifyGemsInput
import example.simian.model.VerifyGemsOutput
import example.simian.repository.DNARepository
import example.simian.usecase.CountGemsUseCase
import example.simian.usecase.VerifyGemsUseCase

class MainController(
    private val dnaRepository: DNARepository,
    private val minimalRequiredChar: Int,
    private val requiredMatchWords: List<String>,
    private val minimalMatchSimian: Int
) {

    private val countGemsUseCase = CountGemsUseCase(dnaRepository)
    private val verifyGemsUseCase =
        VerifyGemsUseCase(dnaRepository, minimalRequiredChar, requiredMatchWords, minimalMatchSimian)

    fun verifyGems(input: VerifyGemsInput): VerifyGemsOutput = verifyGemsUseCase.execute(input)

    fun countGems(): CountGemsOutput = countGemsUseCase.execute()

}