package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import cc.nolink.client.service.AmqpService;

//import cc.nolink.amqp.service.AmqpService;

import static io.swagger.Responses.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-05T13:50:22.207Z")

@Controller
public class ClientApiController implements ClientApi {
	@Autowired
	AmqpService amqpService;
	
	public ResponseEntity<String> send(String message) {
		// do some magic!
		amqpService.sendMsg(message);
		return ok(message);
	}
}
