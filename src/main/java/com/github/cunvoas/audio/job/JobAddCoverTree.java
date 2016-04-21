package com.github.cunvoas.audio.job;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.metadata.MusicMetadata;
import com.github.cunvoas.audio.metadata.MusicMetadataHelper;
import com.github.cunvoas.audio.report.CsvMetadata;
import com.github.cunvoas.audio.walker.MusicCoverDirectoryWalker;

/**
 * Jod for metadata extraction.
 * 
 * @author CUNVOAS
 */
public class JobAddCoverTree extends Job {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JobAddCoverTree.class);

	
	/**
	 * Constructor.
	 * 
	 * @param metaDataFile
	 */
	public JobAddCoverTree(File metaDataFile) throws IOException, FileNotFoundException {
		super(metaDataFile);
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#getMode()
	 */
	@Override
	public JobMode getMode() {
		return JobMode.WRITE_COVER;
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#process(java.io.File)
	 */
	@Override
	public void process(File musicFile) throws IOException {
		File coverFile = MusicCoverDirectoryWalker.getCovers(musicFile.getParent());
		if (coverFile!=null) {
			MusicMetadataHelper.addFrontCover(musicFile, coverFile);
		}
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return LOGGER;
	}

}
