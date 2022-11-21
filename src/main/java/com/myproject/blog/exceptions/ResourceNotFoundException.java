package com.myproject.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resource;
	private String fieldName;
	private Long fieldValue;
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Long fieldValue) {
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException(String resource, String fieldName, Long fieldValue) {
		super(String.format("%s not found with %s : %s",resource,fieldName,fieldValue));
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
