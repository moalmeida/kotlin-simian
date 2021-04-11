package example.simian.config

import io.micronaut.context.annotation.ConfigurationProperties
import javax.validation.constraints.NotBlank


@ConfigurationProperties("simian")
class SimianConfigurationProperties {
    @NotBlank
    lateinit var minimalRequiredChar: String

    @NotBlank
    lateinit var minimalMatchSimian: String

    @NotBlank
    lateinit var requiredMatchWords: List<String>
}
