package com.github.cunvoas.audio.report;

import java.io.File;

import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.job.JobMode;
import com.github.cunvoas.audio.metadata.MusicMetadata;

/**
 * Manage R/W for metadata in CSV file.
 * @author CUNVOAS
 */
public class CsvMetadata {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CsvMetadata.class);
	
	private CSVFormat format = CSVFormat.DEFAULT.withHeader(MusicMetadata.headerCsv()).withDelimiter(';');
	
	public CsvMetadata(File metaDataFile, JobMode mode) {
		super();
		
		
	}
}
