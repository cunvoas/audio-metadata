package com.github.cunvoas.audio.job;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import com.github.cunvoas.audio.report.CsvMetadata;

/**
 * @author CUNVOAS
 */
public abstract class Job {

	protected CsvMetadata csvMetadata = null;

	public Job(File metaDataFile) throws IOException, FileNotFoundException {
		super();
		csvMetadata = new CsvMetadata(metaDataFile, getMode());
	}

	public abstract JobMode getMode();

	public abstract Logger getLogger();

	public void process(File musicFile) throws IOException {
		getLogger().info(musicFile.getAbsolutePath());
		csvMetadata.append(musicFile);
	}

	public void finish() {
		IOUtils.closeQuietly(csvMetadata);
	}
}
