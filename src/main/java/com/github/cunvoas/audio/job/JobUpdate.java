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

/**
 * Jod for metadata extraction.
 * 
 * @author CUNVOAS
 */
public class JobUpdate extends Job {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JobUpdate.class);

	private Map<String, MusicMetadata> meta = null;;

	/**
	 * Constructor.
	 * 
	 * @param metaDataFile
	 */
	public JobUpdate(File metaDataFile) throws IOException,
			FileNotFoundException {
		super(metaDataFile);
		CsvMetadata csvMetadata = null;
		try {
			csvMetadata = new CsvMetadata(metaDataFile, getMode());
			meta = csvMetadata.parse();
		} catch (Exception e) {
			LOGGER.error("CSV PARSE {}", e);
		} finally {
			IOUtils.closeQuietly(csvMetadata);
		}
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#getMode()
	 */
	@Override
	public JobMode getMode() {
		return JobMode.WRITE_METADATA;
	}

	/**
	 * @see com.github.cunvoas.audio.job.Job#process(java.io.File)
	 */
	@Override
	public void process(File musicFile) throws IOException {

		MusicMetadata md = meta.get(musicFile.getAbsolutePath());
		if (md != null) {
			MusicMetadataHelper.update(musicFile, md);
			LOGGER.info("Processed {}", musicFile.getAbsolutePath());
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
