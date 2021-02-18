package com.wse.api.controllers;
/**
 * Class describing webservices using the PDFHandlerService
 * mainly used by Salesforce
 * @author nnizard
 *  fzef
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wse.api.dto.FileAsBlob;
import com.wse.api.pdfHandler.IPDFHandler;
import com.wse.api.utils.InternalFileUtils;

@RestController
@RequestMapping(path = "/pdf")
public class PDFMergerController {

	private static Logger logger = Logger.getLogger(PDFMergerController.class);

	@Autowired
	private IPDFHandler pdfHandler;
	private ObjectMapper mapper;

	public PDFMergerController(){
		super();
		this.mapper = new ObjectMapper();
	}
	
	@PostMapping(path = "/merge", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String mergePdfs(@RequestBody String jsonEntry) throws Exception {
		// getting request body as list of items
		ArrayList<FileAsBlob> fabs = mapper.readValue(jsonEntry,
				mapper.getTypeFactory().constructCollectionType(List.class, FileAsBlob.class));
		FileAsBlob fob = new FileAsBlob();
		String fileName;
		// creating document and retrieving its path
		try {
			fileName = pdfHandler.mergeDocuments(fabs);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		// generating salesforce usable encoded version of the document
		try {
			fob = InternalFileUtils.PathToEncoded(fileName);
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		}
		// generating response json
		String returned = mapper.writer().writeValueAsString(fob);
		System.out.println(returned);
		// deleting document
		pdfHandler.deletePDFFile();
		try {
			InternalFileUtils.deleteLocalFile(fileName);
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
		}
		return returned;
	}
	
}
