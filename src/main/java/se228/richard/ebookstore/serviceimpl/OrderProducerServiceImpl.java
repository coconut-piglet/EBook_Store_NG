package se228.richard.ebookstore.serviceimpl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;
import se228.richard.ebookstore.service.OrderProducerService;

import java.util.Properties;

@Service
public class OrderProducerServiceImpl implements OrderProducerService {

    private Properties props = new Properties();
    private Producer<String, String> producer = null;
    public OrderProducerServiceImpl()
    {
        props.put("bootstrap.servers", "localhost:9092");
        props.setProperty("transactional.id", "my-transactional-id");
    }

    @Override
    public boolean purchase(int cartID, String time) {
        try {
            producer = new KafkaProducer<String, String>(props, new StringSerializer(), new StringSerializer());
            producer.initTransactions();

            producer.beginTransaction();
            producer.send(new ProducerRecord<>("test", Integer.toString(cartID), time));
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            producer.close();
            return false;
        } catch (KafkaException e) {
            producer.abortTransaction();
            return false;
        }
        producer.close();
        return true;
    }

}
