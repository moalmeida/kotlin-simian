package simian.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VerifyGemsInput(@JsonProperty("dna") val dna: List<String>)