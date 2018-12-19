package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ResponseUtils {
	private static HttpHeaders extraHeaders = new HttpHeaders();
	
	static {
		
	}
	
	public static <T> ResponseEntity<T> makeOk(T payload) {
		return new ResponseEntity<T>(payload, extraHeaders, HttpStatus.OK);
	}
	
	public static ResponseEntity<Void> makeOkEmpty() {
		return new ResponseEntity<Void>(extraHeaders, HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<T> make404() {
		return new ResponseEntity<T>(extraHeaders, HttpStatus.NOT_FOUND);
	}
}
