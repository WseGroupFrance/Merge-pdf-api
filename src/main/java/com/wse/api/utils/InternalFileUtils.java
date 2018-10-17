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
		fblob.setName(path.split("/")[path.split("/").length-1]);
		byte[] bytesNonEncoded = Files.readAllBytes(new File(fblob.getName()).toPath());
		fblob.setBody((new String(Base64.encodeBase64(bytesNonEncoded))));
		fblob.setContentType("application/pdf");
		return fblob;
	}
	
	public static void deleteLocalFile(String path) throws IOException, SecurityException {
		if (!new File(path).delete()) throw new IOException("Le fichier ne semble pas accessible, pas de suppression");
	}
	
}
