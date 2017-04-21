package io.swagger.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-02T17:01:50.367Z")

public class ApiException extends Exception {
	private int code;

	public ApiException(int code, String msg) {
		super(msg);
		this.code = code;
	}
}
