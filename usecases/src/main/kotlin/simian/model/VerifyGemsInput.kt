package simian.model

data class VerifyGemsInput(
    val dna: List<String>
) {

    override fun toString(): String {
        return "VerifyGemsInput(dna=$dna)"
    }
}