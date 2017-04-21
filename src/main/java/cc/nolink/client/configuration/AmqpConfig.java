/**
 * 
 */
package cc.nolink.client.configuration;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import cc.nolink.client.adapter.listener.ClientMessageListener;

/**
 * @author reset
 *
 */
@Configuration
public class AmqpConfig {
	@Autowired
	Environment env;
    
    @Bean
    public ConnectionFactory connectionFactory() {
    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    	
    	connectionFactory.setAddresses(env.getProperty("custom.rabbitmq.address"));
    	connectionFactory.setUsername(env.getProperty("custom.rabbitmq.username"));
    	connectionFactory.setPassword(env.getProperty("custom.rabbitmq.password"));
    	connectionFactory.setVirtualHost(env.getProperty("custom.rabbitmq.virtualHost"));
    	connectionFactory.setPublisherConfirms(true); // 消息的回调, 不能通過自動配置的ConnectionFactory來設置
    	
    	return connectionFactory;
    }
    
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    	return new RabbitTemplate(connectionFactory);
    }
    
//    @Bean
//    public DirectExchange defaultExchange() {
//    	return new DirectExchange(env.getProperty("custom.rabbitmq.exchange"));
//    }
    
    @Bean
    public TopicExchange topicExchange() {
    	return new TopicExchange(env.getProperty("custom.rabbitmq.exchange.rcv"));
    }
    
    @Bean
    public Queue queue() {
    	return new Queue(env.getProperty("custom.rabbitmq.queue"), true); //队列持久
    }
    
    @Bean
    public Binding binding() {
    	return BindingBuilder.bind(queue()).to(topicExchange()).with(env.getProperty("custom.rabbitmq.bindingKey"));
    }
    
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
    	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
    	
    	container.setQueues(queue());
    	container.setExposeListenerChannel(true);
    	container.setMaxConcurrentConsumers(1);
    	container.setConcurrentConsumers(1);
    	container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    	container.setMessageListener(new ClientMessageListener()); // custom listener
    	
    	return container;
    }
}
