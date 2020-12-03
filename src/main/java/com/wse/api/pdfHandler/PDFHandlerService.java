package com.wse.api.pdfHandler;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import com.wse.api.dto.FileAsBlob;
import com.wse.api.utils.FileAsBlobComparator;
import com.wse.api.utils.InternalFileUtils;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.ToPdfParameterList;

@Service
public class PDFHandlerService implements IPDFHandler {
	private static Logger logger = Logger.getLogger(PDFHandlerService.class);
	
	private List<String> PDFFiles = new ArrayList<String>();

	@Override
	public String mergeDocuments(ArrayList<FileAsBlob> ls) throws IOException {
		PDFMergerUtility pdfMU;
		pdfMU = new PDFMergerUtility();
		ls.sort(new FileAsBlobComparator());
		ls.forEach(element ->{
			if ("application/pdf".equals(element.getContentType())) {
				System.out.println(String.format("Ajout du document pdf: %s", element.getName()));
				pdfMU.addSource(new ByteArrayInputStream(Base64.getDecoder().decode(element.getBody())));
			} else if(element.getContentType().endsWith("document")){
				try {
					System.out.println(String.format("Ajout du document docx: %s", element.getName()));
					String outputFile = element.getName().substring(0, element.getName().length() - 4) + "pdf";
					//create word document
					Document document = new Document();
					InputStream docFile = new ByteArrayInputStream(Base64.getDecoder().decode(element.getBody()));
					document.loadFromStream(docFile,FileFormat.Auto);
					 //create an instance of ToPdfParameterList.
					ToPdfParameterList ppl=new ToPdfParameterList();
					//embeds full fonts by default when IsEmbeddedAllFonts is set to true.
					ppl.isEmbeddedAllFonts(true);
					//save the document to a PDF file.
					document.saveToFile(outputFile, ppl);
					pdfMU.addSource(outputFile);
					PDFFiles.add(outputFile);
					} catch (FileNotFoundException e) {
						System.out.println("cannot find outputFile for convert doc to pdf"+e.getMessage());
						e.printStackTrace();
					}
			}else {
				logger.debug("Attention le fichier n'est pas conforme");
			}
			
		});
		pdfMU.setDestinationFileName(ls.get(0).getName()+ls.size());
		logger.debug("file name: "+ pdfMU.getDestinationFileName());
		pdfMU.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		logger.debug("merge fait");
		return pdfMU.getDestinationFileName();
	}
	
	@Override
	public void deletePDFFile(){
		if(PDFFiles != null && !PDFFiles.isEmpty()) {
			for(String file : PDFFiles) {
				try {
					InternalFileUtils.deleteLocalFile(file);
				} catch (Exception ex) {
					logger.warn(ex.getMessage());
				}
			}
		}
	}
}
