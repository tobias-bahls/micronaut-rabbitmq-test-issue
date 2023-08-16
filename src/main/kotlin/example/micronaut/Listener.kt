package example.micronaut

import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class Listener {
    @Queue("test", executor = "test")
    fun receive(data: String) {
    }
}