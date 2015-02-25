package com.github.cunvoas.audio.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FilePrefixVisitor implements FileVisitor<Path> {
	private String prefix;
	
	private File wrtFile = new File("d:/temp/dirs.txt");
	private FileWriter fWriter = null;

	public FilePrefixVisitor(String prefix) {
		this.prefix = prefix;
		
		try {
			fWriter = new FileWriter(wrtFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is triggered before visiting a directory.
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	/**
	 * This is triggered when we visit a file.
	 */
	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
		
//		if (path.getFileName().startsWith(this.prefix)) {
//			System.out.println("delete;"+ path);
//			//Files.delete(path);
//		}
		//System.out.println("scan;"+ path);
		return FileVisitResult.CONTINUE;
	}

	/**
	 * This is triggered if we cannot visit a Path We log the fact we cannot
	 * visit a specified path .
	 */
	@Override
	public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
		// We print the error
		System.err.println("ERROR: Cannot visit path: " + path);
		// We continue the folder walk
		return FileVisitResult.CONTINUE;
	}

	/**
	 * This is triggered after we finish visiting a specified folder.
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path path, IOException exc) throws IOException {
		// We continue the folder walk
		//System.out.format("Directory: %s%n", path);
		fWriter.append(path.toString());
		fWriter.append("\r\n");
		return FileVisitResult.CONTINUE;
	}

}
