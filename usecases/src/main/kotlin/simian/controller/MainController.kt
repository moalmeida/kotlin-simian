package simian.controller

import simian.model.CountGemsOutput
import simian.model.VerifyGemsInput
import simian.model.VerifyGemsOutput
import simian.repository.DNARepository
import simian.usecase.CountGemsUseCase
import simian.usecase.VerifyGemsUseCase

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