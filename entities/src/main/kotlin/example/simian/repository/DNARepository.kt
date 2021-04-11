package example.simian.repository

import example.simian.entity.DNA
import example.simian.entity.Gems

interface DNARepository {
    fun generateId(dna: List<String>): String
    fun create(model: DNA)
    fun findById(id: String): DNA?
    fun countByGems(gems: Gems): Int
}