package omb.java.examples.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

	public static void main(String[] args) throws IOException {
		DataOutput raf = new RandomAccessFile("E:\\temp\\random.txt", "rw");
		raf.writeBytes("Hello World!");
	}
	
}
