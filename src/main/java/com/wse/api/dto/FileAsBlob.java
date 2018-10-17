package com.wse.api.dto;

import java.io.Serializable;

/**
 * @author nnizard
 * @Description Class a representing a file wich content is base64 encoded String
 *
 */
public class FileAsBlob implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String body;
	private String idSalesforce;
	private String length;
	private String contentType;
	
	@Override
	public String toString() {
		return "FileAsBlob [name=" + name + ", body=" + body + ", idSalesforce=" + idSalesforce + ", length=" + length
				+ ", contentType=" + contentType + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getIdSalesforce() {
		return idSalesforce;
	}
	public void setIdSalesforce(String idSalesforce) {
		this.idSalesforce = idSalesforce;
	}
	public FileAsBlob() {
		super();
	}
	public FileAsBlob(String name, String body, String idSalesforce, String length, String contentType) {
		super();
		this.name = name;
		this.body = body;
		this.idSalesforce = idSalesforce;
		this.length = length;
		this.contentType = contentType;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
