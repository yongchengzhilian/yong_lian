package com.aidou.util.exception;

public class WechatTokenNotFoundException extends RuntimeException{

	
	 public WechatTokenNotFoundException(){
	        super();
		    }
		    public WechatTokenNotFoundException(String message){
		        super(message);
		    }
}
