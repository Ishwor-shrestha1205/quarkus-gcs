package org.acme

import com.google.cloud.pubsub.v1.MessageReceiver
import com.google.cloud.pubsub.v1.Subscriber
import com.google.pubsub.v1.PubsubMessage
import com.google.pubsub.v1.ProjectSubscriptionName

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PubSubSubscriber {

    private val projectId = System.getenv("PROJECT_ID")
    private val subscriptionId = System.getenv("SUBSCRIPTION_ID")
    private val subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId)

    fun startSubscriber() {
        val queue = LinkedBlockingQueue<PubsubMessage>()
        val receiver = MessageReceiver { message, consumer ->
            queue.offer(message)
            consumer.ack()
        }
        val subscriber = Subscriber.newBuilder(subscriptionName, receiver).build()
        subscriber.startAsync().awaitRunning()
        // Process messages from the queue
        while (true) {
            val message = queue.poll(1, TimeUnit.SECONDS) ?: continue
            // Process the message (e.g., log it to a local file)
            println("Received message: ${message.data.toStringUtf8()}")
        }
    }
}
