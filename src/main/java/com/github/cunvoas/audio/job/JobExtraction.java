package com.github.cunvoas.audio.job;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public JobExtraction(File metaDataFile) {
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
	public void process(File musicFile) {
		LOGGER.info(musicFile.getAbsolutePath());

	}

}
