package simian.entity

data class DNA(
    val id: String,
    val dna: List<String>,
    val gems: Gems
) {

    override fun toString(): String {
        return "DNA(id='$id', dna=$dna, gems=$gems)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DNA

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
