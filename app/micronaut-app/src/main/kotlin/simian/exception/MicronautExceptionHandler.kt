package simian.exception


import io.micronaut.context.annotation.Requires
import io.micronaut.core.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.HateoasErrorResponseProcessor
import io.micronaut.jackson.JacksonConfiguration
import io.micronaut.validation.exceptions.ConstraintExceptionHandler

@Requires(classes = [ExceptionHandler::class])
open class MicronautExceptionHandler : ConstraintExceptionHandler(
    HateoasErrorResponseProcessor(
        JacksonConfiguration()
    )
)