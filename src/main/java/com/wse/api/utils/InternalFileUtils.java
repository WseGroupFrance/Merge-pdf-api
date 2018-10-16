package com.wse.api.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.tomcat.util.codec.binary.Base64;

import com.wse.api.dto.FileAsBlob;

/**
 * Classe utilitaire possédant des méthodes de traitement de fichiers
 * @author nnizard
 *
 */
public class InternalFileUtils {
	/**
	 * @param path to the file ([path/]filename
	 * @return a FileAsBlob objet representing the file
	 * @throws IOException 
	 */
	public static FileAsBlob PathToEncoded(String path) throws IOException {
		FileAsBlob fblob = new FileAsBlob();
		fblob.setFileName(path.split("/")[path.split("/").length-1]);
		byte[] bytesNonEncoded = Files.readAllBytes(new File(fblob.getFileName()).toPath());
		fblob.setFileContent(new String(Base64.encodeBase64(bytesNonEncoded)));
		
		return fblob;
	}
	
}
