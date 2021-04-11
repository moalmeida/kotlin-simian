package example.simian.config

import io.micronaut.context.annotation.Factory
import example.simian.repository.DNARepository
import example.simian.repository.InMemoryDNARepository
import javax.inject.Singleton


@Factory
class MicronautConfig {
    @Singleton
    fun dnaRepository(): DNARepository = InMemoryDNARepository.INSTANCE
}