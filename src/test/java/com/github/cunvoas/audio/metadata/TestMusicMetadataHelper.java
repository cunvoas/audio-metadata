package com.github.cunvoas.audio.metadata;

import java.io.File;

import org.junit.Test;

public class TestMusicMetadataHelper {
	@Test
	public void testExtract() {
		
		String mm3 = "I:/Music/Eths/01-eths-ex_umbra_in_solem-spank.mp3";
		MusicMetadataHelper.extract(new File(mm3));
	}

}
