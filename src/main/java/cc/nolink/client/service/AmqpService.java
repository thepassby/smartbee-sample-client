/**
 * 
 */
package cc.nolink.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.nolink.client.adapter.AmqpAdapter;

/**
 * @author reset
 *
 */
@Service
public class AmqpService {
	private static final Logger logger = LoggerFactory.getLogger(AmqpService.class);
	
	@Autowired
	AmqpAdapter amqpAdapter;
	
	public void sendMsg(String message) {
		amqpAdapter.sendMessage(message);
		logger.info("send message: " + message);
	}
}
