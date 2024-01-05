package org.acme

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response

@Path("/subscribe")
class PubSubResource {

    @Inject
    lateinit var pubSubSubscriber: PubSubSubscriber

    @GET
    @Path("/start")
    fun startSubscriber(): Response {
        pubSubSubscriber.startSubscriber()
        return Response.ok("Pub/Sub subscriber started").build()
    }
}