package simian.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VerifyGemsOutput(@JsonProperty("is_simian") val isSimian: Boolean)