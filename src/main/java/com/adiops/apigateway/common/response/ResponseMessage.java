package com.adiops.apigateway.common.response;

/**
 * The puspose this class to build response message 
 * @author Deepak Pal
 *
 */
public class ResponseMessage {
	 private String message;

	  public ResponseMessage(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
