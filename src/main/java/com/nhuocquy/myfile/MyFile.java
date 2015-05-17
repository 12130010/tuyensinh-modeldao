package com.nhuocquy.myfile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyFile {
	private byte[] data;
	private String fileName;

	public MyFile() {

	}

	public MyFile(String fileName) {
		this.fileName = fileName;
		try {
			getData();
		} catch (MyFileException e) {
			e.printStackTrace();
		}
	}

	public MyFile(byte[] data) {
		this.data = data;
	}

	public byte[] getData() throws MyFileException {
		if (data == null) {
			File file = new File(fileName);
			long lenLong = file.length();
			if (lenLong > Integer.MAX_VALUE)
				throw new MyFileException("File too large");
			int lenInt = (int) lenLong;
			data = new byte[lenInt];
			try {
				DataInputStream dis = new DataInputStream(new FileInputStream(
						fileName));
				dis.readFully(data);
				dis.close();
			} catch (IOException e) {
			}
		}
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String retrieveExtendName(){
		return fileName.substring(fileName.lastIndexOf('.'));
	}
	
}
