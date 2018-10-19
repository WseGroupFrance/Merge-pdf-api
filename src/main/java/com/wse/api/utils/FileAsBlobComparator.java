package com.wse.api.utils;

import java.util.Comparator;

import com.wse.api.dto.FileAsBlob;

public class FileAsBlobComparator implements Comparator<FileAsBlob> {

	@Override
	public int compare(FileAsBlob o1, FileAsBlob o2) {
		if (o1.getIsFirst()){
			if (o2.getIsFirst()) {
				return 0;
			} else {
				return -1;
			}
		}
		if(o2.getIsFirst()) {
			return 1;
		}
		return 0;
	}

}
