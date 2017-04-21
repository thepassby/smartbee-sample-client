package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-05T13:50:22.207Z")

@RequestMapping(value = "/api")
public interface ClientApi {

	@RequestMapping(value = "/client/send", produces = { MediaType.TEXT_HTML_VALUE }, method = RequestMethod.PUT)
	@ApiOperation(value = "no", notes = "no", response = String.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "An string that", response = String.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Order not found") })
	public ResponseEntity<String> send(
			@NotNull @ApiParam(value = "the message that will be sended.", required = true) @RequestParam(value = "message", required = true) String message
			) throws NotFoundException;
}
