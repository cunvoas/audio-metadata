package com.github.cunvoas.audio.report;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.cunvoas.audio.file.MusicFileFinder;

public class TestReportCsv {
	private ReportCsv tested = new ReportCsv();

	@Test
	public void testProduceMusic() {
		MusicFileFinder finder = new MusicFileFinder();
		try {
			List<File> files = finder.getMusicFiles(new File("D:/Music/"));
			
			
			
			tested.produceMusic(files, new File("D:/Music/TestReportCsv.csv"));
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
