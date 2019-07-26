package ua.step.debating;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration { //extends AbstractWebSocketMessageBrokerConfigurer  {
	
	public void configureMessageBroker(MessageBrokerRegistry confi) {
		confi.enableSimpleBroker("/debateLobby/1/chat");
		confi.setApplicationDestinationPrefixes("/debateLobby/1/app");
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/debateLobby/1/chat-messaging").withSockJS();
		
	}

}
