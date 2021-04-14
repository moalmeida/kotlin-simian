package simian.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import simian.model.VerifyGemsInput
import javax.inject.Inject

@MicronautTest
class MicronautMainControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: RxHttpClient

    @Test
    fun testSimian() {
        val request: HttpRequest<Any> = HttpRequest.POST(
            "/simian",
            VerifyGemsInput(arrayListOf("ATGCGA", "CAGTGC", "TTATTT", "AGACAG", "GCGTCA", "TAACTG"))
        )
        val body = client.toBlocking().retrieve(request)
        Assertions.assertNotNull(body)
        Assertions.assertEquals("""{"is_simian":false}""", body)
    }

    @Test
    fun testStats() {
        val request: HttpRequest<Any> = HttpRequest.GET("/stats")
        val body = client.toBlocking().retrieve(request)
        Assertions.assertNotNull(body)
        Assertions.assertEquals("""{"count_simian_dna":0,"count_human_dna":0,"ratio":0.0}""", body)
    }

}