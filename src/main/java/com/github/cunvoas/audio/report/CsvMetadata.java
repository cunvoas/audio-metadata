package com.github.cunvoas.audio.report;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.job.JobMode;
import com.github.cunvoas.audio.metadata.MusicMetadata;
import com.github.cunvoas.audio.metadata.MusicMetadataHelper;

/**
 * Manage R/W for metadata in CSV file.
 * @author CUNVOAS
 * @NotThreadSafe 
 */
public class CsvMetadata implements Closeable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CsvMetadata.class);

	private MusicMetadataHelper musicMetadataHelper = null;
	
	private CSVFormat format = CSVFormat.DEFAULT.withHeader(MusicMetadata.headerCsv()).withDelimiter(';');
	
	private CSVPrinter csvPrinter = null;
	private PrintStream csvFilePrint = null;

	private CSVParser csvParser = null;
	private Reader csvReader = null;
	
	private List<String> csvCols = new ArrayList<String>(MusicMetadata.NB_TAGS);
	
	/**
	 * @param metaDataFile
	 * @param mode
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CsvMetadata(File metaDataFile, JobMode mode) throws FileNotFoundException, IOException {
		super();
		musicMetadataHelper = new MusicMetadataHelper();
		if (JobMode.EXTRACT_METADATA.equals(mode)) {
			csvFilePrint = new PrintStream(metaDataFile);
			csvPrinter = new CSVPrinter(csvFilePrint, format);
			
		} else {
			csvReader = new FileReader(metaDataFile);
			csvParser = new CSVParser(csvReader, format);
		}
	}

	
	public void append(File musicFile) throws IOException {
		MusicMetadata metadata = musicMetadataHelper.extract(musicFile);
		if (metadata!=null) {
			csvCols.addAll(metadata.toCsv());
		} else {
			csvCols.addAll(this.emptyMetaData(MusicMetadata.NB_TAGS));
		}
		this.formatLine(csvPrinter, csvCols);
		csvCols.clear();
	}
	
	private MusicMetadata parse (CSVRecord line) {
		MusicMetadata metadata = null;
		
		
		
		return metadata;
	}


	/**
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		IOUtils.closeQuietly(csvPrinter);
		IOUtils.closeQuietly(csvFilePrint);
		
		IOUtils.closeQuietly(csvFilePrint);
		IOUtils.closeQuietly(csvReader);
	}
	

	private void formatLine(CSVPrinter csvPrinter, List<String> data)
			throws IOException {
		for (String string : data) {
			csvPrinter.print(string != null ? string : "");
		}
		csvPrinter.println();
	}

	private List<String> emptyMetaData(int qte) {
		List<String> emptyList = new ArrayList<String>();
		for (int i = 0; i < qte; i++) {
			emptyList.add("");
		}
		return emptyList;
	}
}
