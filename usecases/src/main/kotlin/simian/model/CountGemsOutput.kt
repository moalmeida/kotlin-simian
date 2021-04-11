package simian.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CountGemsOutput(
    @JsonProperty("count_simian_dna") val countSimianDNA: Int,
    @JsonProperty("count_human_dna") val countHumanDNA: Int
) {

    val ratio: Double
        get() = (countSimianDNA.toFloat() / countHumanDNA.toFloat())
            .let {
                when {
                    it.isNaN() || it.isInfinite() -> 0.0
                    else -> (it * 100) / 100
                }
            }.toDouble()

    override fun toString(): String {
        return "CountGemsOutput(countSimianDNA=$countSimianDNA, countHumanDNA=$countHumanDNA, ratio=$ratio)"
    }
}