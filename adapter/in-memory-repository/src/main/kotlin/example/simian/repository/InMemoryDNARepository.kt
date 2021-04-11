package example.simian.repository

import example.simian.entity.DNA
import example.simian.entity.Gems
import java.util.*

class InMemoryDNARepository private constructor() : DNARepository {

    private val inMemoryDb: MutableMap<String, DNA> = HashMap()

    override fun generateId(dna: List<String>): String = dna.joinToString("")

    override fun create(model: DNA) {
        inMemoryDb[model.id] = model
    }

    override fun findById(id: String): DNA? = inMemoryDb[id]

    override fun countByGems(gems: Gems): Int = inMemoryDb.values.stream()
        .filter { dna: DNA -> dna.gems === gems }.count().toInt()

    companion object {
        val INSTANCE = InMemoryDNARepository()
    }

}

