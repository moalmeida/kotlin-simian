package example.simian.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VerifyGemsOutput(
    @JsonProperty("is_simian") val isSimian: Boolean
) {
    override fun toString(): String {
        return "VerifyGemsOutput(isSimian=$isSimian)"
    }
}