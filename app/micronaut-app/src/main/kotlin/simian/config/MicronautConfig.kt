package simian.config

import io.micronaut.context.annotation.Factory
import simian.repository.DNARepository
import simian.repository.InMemoryDNARepository
import javax.inject.Singleton


@Factory
class MicronautConfig {
    @Singleton
    fun dnaRepository(): DNARepository = InMemoryDNARepository.INSTANCE
}