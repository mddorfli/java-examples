package omb.java.examples.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;

public class FilesTest {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("E:\\temp\\test.txt");
		DosFileAttributes attr = Files.readAttributes(p, DosFileAttributes.class);
		System.out.println("created "+ attr.creationTime());
		System.out.println("accessed "+ attr.lastAccessTime());
		System.out.println("modified "+ attr.lastModifiedTime());
		System.out.println("hidden: "+attr.isHidden());
		
		Files.setAttribute(p, "dos:hidden", true);
		
		DosFileAttributeView dosAttributesView = Files.getFileAttributeView(p, DosFileAttributeView.class);
		System.out.println("DosFileAttributes view name is "+ dosAttributesView.name());
	}
}
