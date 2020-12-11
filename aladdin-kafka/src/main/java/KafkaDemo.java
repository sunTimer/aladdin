import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaDemo {

    public static void main(String[] args) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("","","");
        Producer producer;
    }
}
