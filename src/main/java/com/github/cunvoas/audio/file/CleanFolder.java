package com.github.cunvoas.audio.file;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CleanFolder {

	public static void main(String[] args) {
		List<String> folders = Arrays.asList("Z:/Poste1", "Z:/Poste2", "Z:/Poste3", "Z:/Poste4", "Z:/Poste5", "Z:/Poste6");
		
		FileVisitor<Path> visitor = new FilePrefixVisitor("~$");
		
		for (String folder : folders) {
			Path path = Paths.get(folder);
			
			try {
				Files.walkFileTree(path, visitor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
