package com.wse.api.pdfHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import com.wse.api.dto.FileAsBlob;

@Service
public class PDFHandlerService implements IPDFHandler {
	private static Logger logger = Logger.getLogger(PDFHandlerService.class);

	@Override
	public String mergeDocuments(List<FileAsBlob> ls) throws IOException {
		PDFMergerUtility pdfMU;
		pdfMU = new PDFMergerUtility();
		ls.forEach(element ->{
			pdfMU.addSource(new ByteArrayInputStream(Base64.getDecoder().decode(element.getFileContent())));
		});
		pdfMU.setDestinationFileName(ls.get(0).getFileName()+ls.size());
		logger.debug("file name: "+ pdfMU.getDestinationFileName());
		pdfMU.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		logger.debug("merge fait");
		return pdfMU.getDestinationFileName();
	}

}
