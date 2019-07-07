package omb.java.examples.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;

public class PosixAttributesTest {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("E:\\temp\\test.txt");
		PosixFileAttributeView attrv = Files.getFileAttributeView(path, PosixFileAttributeView.class);
		PosixFileAttributes attr = attrv.readAttributes();
		String ownername = attr.owner().getName();
		System.out.println(ownername);
	}
}
