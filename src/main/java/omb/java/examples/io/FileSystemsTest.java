package omb.java.examples.io;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class FileSystemsTest {

	public static void main(String[] args) {
		for (Iterator<Path> iter = FileSystems.getDefault().getRootDirectories().iterator(); iter.hasNext(); ) {
			System.out.println(iter.next());
		}
		
		PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.jar");
		Path workspace = Paths.get("D:\\3-Workspace\\iOPCIS");
		
		Stack<File> folderTree = new Stack<>();
		folderTree.push(workspace.toFile());
		while(!folderTree.isEmpty()) {
			File file = folderTree.pop();
			Path path = Paths.get(file.getAbsolutePath());
//			System.out.println("inspecting "+path);
			
			if (file.isDirectory()) {
				folderTree.addAll(Arrays.asList(file.listFiles()));
			} else if (file.isFile() && pathMatcher.matches(path)){
				System.out.println("Found "+path+"!!!");
			}
		}
	}
}
