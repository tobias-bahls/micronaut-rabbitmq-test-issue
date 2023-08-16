package example.micronaut
import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel
import io.micronaut.rabbitmq.connect.ChannelInitializer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Singleton
import org.junit.jupiter.api.Test

@MicronautTest
class Repro {
    @Singleton
    class ChannelPoolListener : ChannelInitializer() {
        override fun initialize(channel: Channel, name: String) {
            channel.exchangeDeclare("micronaut", BuiltinExchangeType.DIRECT, true)
            channel.queueDeclare("test", true, false, false, null)
            channel.queueBind("test", "micronaut", "analytics")
        }
    }

    @Test
    fun test() {
        // noop
    }
}
