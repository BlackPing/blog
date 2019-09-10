package com.blackping.shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) throws IOException {
		// FileNotFoundException
		BufferedReader br = new BufferedReader(new FileReader("test.txt"));
		
		// 한줄씩 읽음 readLine() 없으면 null
		String read = null;
		while((read = br.readLine()) != null) {
			System.out.println(read);
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write("test");
		bw.newLine();
		bw.write("toto");
		bw.newLine();
		bw.write("okay");
		
		br.close();
		bw.close();
		
		// byte file 정보
		FileInputStream fis = new FileInputStream("test.txt");
		FileOutputStream fos = new FileOutputStream("testcopy.txt");
		byte[] buf = new byte[fis.available()];
		System.out.println(buf[0]);
		fis.read(buf);
		System.out.println(buf[0]);
		fos.write(buf);
		
		fis.close();
		fos.close();
		
		FileWriter fw = new FileWriter("../tttt.txt", true);
		fw.write("test");
		fw.close();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		System.out.println(sdf.format(new Date()));
	}
}
