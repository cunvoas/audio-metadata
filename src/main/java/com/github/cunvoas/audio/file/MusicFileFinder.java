package com.github.cunvoas.audio.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;

/**
 * @author CUNVOAS
 */
public class MusicFileFinder extends DirectoryWalker<File> {
	
	public MusicFileFinder() {
		super(new MusicFileFilter(), 4);
	}
	
	/**
	 * @param directory
	 * @return
	 * @throws IOException
	 */
	public List<File> getMusicFiles(File directory) throws IOException {
		List<File> files = new ArrayList<File>();
		this.walk(directory, files);
		return files;
	}

	/**
	 * @see org.apache.commons.io.DirectoryWalker#handleFile(java.io.File, int, java.util.Collection)
	 */
	@Override
	protected void handleFile(File file, int depth, Collection<File> results)
			throws IOException {
		super.handleFile(file, depth, results);
		results.add(file);
	}


}
