package com.wse.api.pdfHandler;

import java.io.IOException;
import java.util.List;

import com.wse.api.dto.FileAsBlob;

public interface IPDFHandler {
	/**
	 * @throws IOException 
	 * @input array of pdf documents as blobs
	 * @output a pdf file representing all the pdfs merged
	 */
	public String mergeDocuments(List<FileAsBlob> ls) throws IOException;
}
