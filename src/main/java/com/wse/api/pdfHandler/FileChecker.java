package com.wse.api.pdfHandler;

import java.io.File;
import java.io.FilenameFilter;

public class FileChecker {
    
   private static final String FILE_DIR = "/root";
   private static final String FILE_TEXT_EXT = ".pdf";
    
   public void deletePDFFile(){
        
     GenericExtFilter filter = new GenericExtFilter(FILE_TEXT_EXT);
     File dir = new File(FILE_DIR);
    
     //list out all the file name with .pdf extension
     String[] list = dir.list(filter);
         
     if (list.length == 0) return;

     File fileDelete;
        
     for (String file : list){
   	String temp = new StringBuffer(FILE_DIR)
                      .append(File.separator)
                      .append(file).toString();
    	fileDelete = new File(temp);
    	boolean isdeleted = fileDelete.delete();
    	System.out.println("file : " + temp + " is deleted : " + isdeleted);
     }
   }
  
   //inner class, generic extension filter 
   public class GenericExtFilter implements FilenameFilter {
    
       private String ext;
    
       public GenericExtFilter(String ext) {
         this.ext = ext;             
       }
           
       public boolean accept(File dir, String name) {
         return (name.endsWith(ext));
       }
    }
}