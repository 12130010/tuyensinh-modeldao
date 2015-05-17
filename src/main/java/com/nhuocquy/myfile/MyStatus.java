package com.nhuocquy.myfile;

public class MyStatus {
	public static final int CODE_SUCCESS = 1;
	public static final int CODE_FAIL = 0;
	public static final String MESSAGE_FAIL = "fail";
	public static final String MESSAGE_SUCCESS = "success";
	
	private int code;
	private String message;
	private Object obj;
	public MyStatus() {
		
	}
	public MyStatus(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public MyStatus(int code, String message, Object obj) {
		super();
		this.code = code;
		this.message = message;
		this.obj = obj;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
