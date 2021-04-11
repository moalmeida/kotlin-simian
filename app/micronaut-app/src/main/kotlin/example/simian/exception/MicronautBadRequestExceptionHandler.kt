package example.simian.exception

import io.micronaut.context.annotation.Replaces
import io.micronaut.context.annotation.Requires
import io.micronaut.core.exceptions.ExceptionHandler
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.validation.exceptions.ConstraintExceptionHandler
import javax.validation.ConstraintViolationException

@Produces
@Replaces(ConstraintExceptionHandler::class)
@Requires(classes = [ConstraintViolationException::class, ExceptionHandler::class, NotContainsOnlyNitroWordsException::class])
class MicronautBadRequestExceptionHandler : ConstraintExceptionHandler() {
    override fun handle(request: HttpRequest<*>?, exception: ConstraintViolationException): HttpResponse<*> {
        return HttpResponse.badRequest<Any>()
    }
}