package com.github.cunvoas.audio.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.DirectoryWalker;

/**
 * @author CUNVOAS
 */
public class ImageFileFinder extends DirectoryWalker<File> {
	
	public ImageFileFinder() {
		super(new ImageFileFilter(), 4);
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
	 * @param files
	 * @return
	 */
	public Map<String, List<File>> indexDirectory(List<File> files) {
		Map<String, List<File>> map = new HashMap<String, List<File>>();
		
		for (File file : files) {
			if (!map.containsKey(file.getParent())) {
				map.put(file.getParent(), new ArrayList<File>());
			}
			map.get(file.getParent()).add(file);
		}
		return map; 
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
