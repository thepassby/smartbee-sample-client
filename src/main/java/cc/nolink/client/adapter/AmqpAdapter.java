/**
 * 
 */
package cc.nolink.client.adapter;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author reset
 *
 */
@Component
public class AmqpAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AmqpAdapter.class);
	
	private RabbitTemplate rabbitTemplate;
	private String exchange, routingKey;
	
	@Autowired
	public AmqpAdapter(RabbitTemplate rabbitTemplate, Environment env) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setConfirmCallback(new ConfirmCallback() {
			
			@Override
			public void confirm(CorrelationData cdata, boolean ack, String cause) {
				logger.info("CorrelationData: " + cdata);
				
				if(ack) {
					logger.info("send success.");
				} else {
					logger.error("send failed:" + cause);
				}
			}
		});
		
		this.exchange = env.getProperty("custom.rabbitmq.exchange.snd");
		this.routingKey = env.getProperty("custom.rabbitmq.routingKey");
	}
	
	// send
	public void sendMessage(String message) {
		//logger.info("exchange:" + exchange + ", routingKey:" + routingKey);
		
		CorrelationData cdata = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(exchange, routingKey, message, cdata);
	}
}
