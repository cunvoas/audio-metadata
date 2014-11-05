package com.github.cunvoas.audio.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author CUNVOAS
 */
public class TestMusicFileFinder {

	@Test
	public void testGetMusicFiles() {
		MusicFileFinder tested = new MusicFileFinder();
		
		try {
			List<File> files = tested.getMusicFiles(new File("D:/Music/FLAC/Madball"));
			
			Assert.assertNotNull(files);
			Assert.assertFalse(files.isEmpty());
			
			
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
		
		
	}

}
