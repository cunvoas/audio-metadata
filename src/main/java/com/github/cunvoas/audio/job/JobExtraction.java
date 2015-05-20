package com.github.cunvoas.audio.job;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.report.CsvMetadata;

/**
 * Jod for metadata extraction.
 * @author CUNVOAS
 */
public class JobExtraction extends Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobExtraction.class);
	
	/**
	 * Constructor.
	 * @param metaDataFile
	 */
	public JobExtraction(File metaDataFile) throws IOException, FileNotFoundException {
		super(metaDataFile);
		
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#getMode()
	 */
	@Override
	public JobMode getMode() {
		return JobMode.EXTRACT_METADATA;
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#process(java.io.File)
	 */
	@Override
	public void process(File musicFile) throws IOException{
		LOGGER.info(musicFile.getAbsolutePath());
		csvMetadata.append(musicFile);
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return LOGGER;
	}
	
	

}
