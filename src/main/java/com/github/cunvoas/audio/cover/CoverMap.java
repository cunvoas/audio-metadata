package com.github.cunvoas.audio.cover;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CoverMap {
	
	private static final CoverMap INSTANCE = new CoverMap();

	private static final Map<String, CoverFile> COVERS=new HashMap<String, CoverFile>();
	
	/**
	 * Getter for covers.
	 * @return the covers
	 */
	public static final File getCovers(String folderPath) {
		return COVERS.containsKey(folderPath)?COVERS.get(folderPath).getCoverFile():null;
	}

	/**
	 * Getter for instance.
	 * @return the instance
	 */
	public static final CoverMap getInstance() {
		return INSTANCE;
	}

	/**
	 * Getter for covers.
	 * @return the covers
	 */
	public static final Map<String, CoverFile> getCovers() {
		return COVERS;
	}
	
}
