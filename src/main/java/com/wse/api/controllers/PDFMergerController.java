package com.wse.api.controllers;
/**
 * Class describing webservices using the PDFHandlerService
 * mainly used by Salesforce
 * @author nnizard
 *  fzef
 */

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wse.api.dto.FileAsBlob;
import com.wse.api.pdfHandler.IPDFHandler;
import com.wse.api.utils.InternalFileUtils;

@RestController
@RequestMapping(path = "/pdf")
public class PDFMergerController {

	@Autowired
	private IPDFHandler pdfHandler;
	
	@PostMapping(path = "/merge",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public FileAsBlob mergePdfs(List<FileAsBlob> ls) throws Exception {
		String fileName;
		try {
			fileName = pdfHandler.mergeDocuments(ls);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		try {
			return InternalFileUtils.PathToEncoded(fileName);
		} catch(IOException ex) {
			throw new IOException("Une erreur est survenue lors de l'envoi du fichier PDF");
		}
	}

}
