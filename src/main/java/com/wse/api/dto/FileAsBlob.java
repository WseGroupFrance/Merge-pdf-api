package com.wse.api.dto;
/**
 * @author nnizard
 * @Description Class a representing a file wich content is base64 encoded String
 *
 */
public class FileAsBlob {
	private String fileName;
	private String fileContent;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public FileAsBlob(String fileName, String fileContent) {
		super();
		this.fileName = fileName;
		this.fileContent = fileContent;
	}
	public FileAsBlob() {
		super();
	}

}
