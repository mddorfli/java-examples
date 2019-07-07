package omb.java.examples.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOTest {
	public static void main(String[] args) throws IOException {

		String s = "hello";
		byte i = 100;
		FileOutputStream fos = new FileOutputStream("d:\\temp\\data.bin");
		DataOutputStream dos = new DataOutputStream(fos);
		// WRITE s to file
		dos.writeUTF(s);
		
		// WRITE i to file
		dos.writeByte(i);
		
		dos.flush();
		dos.close();
		fos.close();
		DataInputStream dis = new DataInputStream(new FileInputStream("d:\\temp\\data.bin"));
		// READ s from file
		System.out.println(dis.readUTF());
		
		// READ i from file
		System.out.println( dis.readByte());
	}
}
