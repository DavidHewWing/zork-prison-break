package com.bayviewglen.prsnbrk;

public class Prompts {
	private String messageToPrint="";
		
	public Prompts(){
		
	}
	
	public void setMessage(String message){
		
		messageToPrint = message;

	}
	
	public void printMessage(){
		System.out.println(messageToPrint);
	}
	
}
