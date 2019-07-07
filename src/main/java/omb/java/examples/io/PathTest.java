package omb.java.examples.io;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
	static Path p = Paths.get("d:\\temp\\data.bin");
	static Path p1 = Paths.get("c:\\finance\\data\\reports\\daily\\pnl.txt");
	static Path p2 = Paths.get("c:\\finance\\data\\reports\\weekly\\wr35.txt");
	static Path p3 = Paths.get("some\\folder");

	public static void main(String[] args) {
//		testBasics();
//		testSubPaths();
//		testResolve();
//		testResolveSibling();
//		testGetRoots();
//		testRelativize();

		System.out.println(p.startsWith("d:\\temp"));
		System.out.println(p.startsWith("d:\\tem"));
		System.out.println(p1.startsWith(p2.resolveSibling("..").normalize()));
	}

	private static void testRelativize() {
		// System.out.println(p1.relativize(p)); // IllegalArgumentException
		System.out.println(p1.relativize(p2));
		System.out.println(p1.relativize(p1));
		// System.out.println(p1.relativize(Paths.get("reports\\weekly"))); //
		// IllegalArgumentException
		// System.out.println(Paths.get("reports\\weekly").relativize(p2)); //
		// IllegalArgumentException
		System.out.println(Paths.get("reports\\weekly").relativize(Paths.get("reports\\monthly")));
		System.out.println(Paths.get("reports\\weekly").relativize(Paths.get("data\\customerdata")));
		
		Path p1 = Paths.get("c:\\personal\\.\\photos\\..\\readme.txt"); 
		Path p2 = Paths.get("c:\\personal\\index.html"); 
		System.out.printf("%s.relativize(%s) = %s\n",p1, p2,  p1.relativize(p2));
		System.out.printf("%s.normalize().relativize(%s) = %s",p1, p2, p1.normalize().relativize(p2));
	}

	private static void testGetRoots() {
		for (File file : File.listRoots()) {
			System.out.println(file.getAbsolutePath());
		}
		for (Path path : FileSystems.getDefault().getRootDirectories()) {
			System.out.println(path);
		}
		System.out.printf("%s.getRoot() = %s\n", p1, p1.getRoot());
		System.out.printf("Paths.get(%s, hello, world) = %s\n", p1.getRoot(), Paths.get(p1.getRoot().toString(), "hello", "world"));

	}

	private static void testSubPaths() {
		System.out.printf("%s.subpath(0,2) = %s\n",p1, p1.subpath(0, 2));
		System.out.printf("%s.subpath(2,3) = %s\n", p1, p1.subpath(2, 3));
		System.out.printf("%s.subpath(0,5) = %s\n", p1, p1.subpath(0, 5));
//		System.out.printf("%s.subpath(0,6) = %s\n", p1, p1.subpath(0, 6)); // IllegalArgumentException
	}

	private static void testBasics() {
		System.out.println("Root: " + p.getRoot());
		System.out.println("NameCount: " + p.getNameCount());
		System.out.println("[0]: " + p.getName(0));
		System.out.println("[1]: " + p.getName(1));
		// System.out.println("[2]: "+p.getName(2)); // IllegalArgumentException
	}

	private static void testResolve() {
		System.out.printf("An absolute path resolves as itself: %s.resolve(%s) = %s\n", p1, p, p1.resolve(p));
		System.out.printf("An incomplete path resolves as a subfolder: %s.resolve(%s) = %s\n", p, p3, p.resolve(p3));
		System.out.printf("%s.resolve(%s) = %s\n", p1, p3, p1.resolve(p3));
	}
	
	private static void testResolveSibling() {
		System.out.println(p1);
		System.out.println(p1.resolveSibling(Paths.get("some\\folder")));
		System.out.println(p1.resolveSibling(Paths.get("otherfile.txt")));
		System.out.println(p1.resolveSibling(Paths.get("\\otherfile.txt")));
		System.out.println(Paths.get("reports\\weekly").resolveSibling(Paths.get("reports\\monthly")));
		System.out.println(Paths.get("reports\\weekly").resolveSibling(Paths.get("data\\customerdata")));
		System.out.println(p1.resolveSibling(p2));
	}
}
