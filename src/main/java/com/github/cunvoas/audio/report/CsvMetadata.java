package com.github.cunvoas.audio.report;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 
 * @author CUNVOAS
 * @NotThreadSafe
 */
public class CsvMetadata implements Closeable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CsvMetadata.class);

	private CSVFormat format = CSVFormat.DEFAULT.withHeader(
			MusicMetadata.headerCsv()).withDelimiter(';');

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
	public CsvMetadata(File metaDataFile, JobMode mode)
			throws FileNotFoundException, IOException {
		super();
		if (JobMode.EXTRACT_METADATA.equals(mode)) {
			csvFilePrint = new PrintStream(metaDataFile);
			csvPrinter = new CSVPrinter(csvFilePrint, format);

		} else if (JobMode.WRITE_METADATA.equals(mode)) {
			csvReader = new FileReader(metaDataFile);
			csvParser = new CSVParser(csvReader, format);
		} else if (JobMode.WRITE_COVER.equals(mode)) {
			

		}
	}

	public void append(File musicFile) throws IOException {
		MusicMetadata metadata = MusicMetadataHelper.extract(musicFile);
		if (metadata != null) {
			csvCols.addAll(metadata.toCsv());
		} else {
			csvCols.addAll(this.emptyMetaData(musicFile, MusicMetadata.NB_TAGS));
		}
		this.formatLine(csvPrinter, csvCols);
		csvCols.clear();
	}

	public Map<String, MusicMetadata> parse() {
		Map<String, MusicMetadata> metasMap = null;
		try {
			if (csvParser != null) {
				
				List<CSVRecord> records = csvParser.getRecords();
				metasMap = new HashMap<String, MusicMetadata>(records.size());
				
				for (CSVRecord record : records) {
					if (record.getRecordNumber()!=1) {
						MusicMetadata md = parse(record);
						metasMap.put(md.getMusicFile(), md);
					}
				}
			}

		} catch (IOException e) {
			LOGGER.error("{CSV File error}", e);
		}
		return metasMap;
	}

	private static final String NULL_STRING = "null";

	private static final boolean isNotNull(String val) {
		return val != null || !NULL_STRING.equals(val)  || !val.trim().isEmpty();
	}

	/**
	 * @param record
	 * @return
	 */
	private MusicMetadata parse(CSVRecord record) {
		MusicMetadata md = new MusicMetadata();
		// Music file;Year;Album;Album artist;Track;Total;Title;Artist;withImg;witdh;height;Image file

		if (isNotNull(record.get(0))) {
			md.setMusicFile(record.get(0));
		}
		if (isNotNull(record.get(1))) {
			md.setYear(record.get(1));
		}
		if (isNotNull(record.get(2))) {
			md.setAlbum(record.get(2));
		}
		if (isNotNull(record.get(3))) {
			md.setAlbumArtist(record.get(3));
		}
		if (isNotNull(record.get(4))) {
			md.setTrack(record.get(4));
		}
		if (isNotNull(record.get(5))) {
			md.setTotalTracks(record.get(5));
		}
		if (isNotNull(record.get(6))) {
			md.setTitle(record.get(6));
		}
		if (isNotNull(record.get(7))) {
			md.setArtist(record.get(7));
		}
		if (isNotNull(record.get(8))) {
			md.setImage("true".equals(record.get(8)));
		}
		if (isNotNull(record.get(9))) {
			md.setImgWidth(Integer.valueOf(record.get(9)));
		}
		if (isNotNull(record.get(10))) {
			md.setImgHeight(Integer.valueOf(record.get(10)));
		}
		if (isNotNull(record.get(11))) {
			md.setImageFile(record.get(11));
		}
		return md;
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

	private List<String> emptyMetaData(File file, int qte) {
		List<String> emptyList = new ArrayList<String>();
		emptyList.add(file.getAbsolutePath());
		for (int i = 1; i < qte; i++) {
			emptyList.add("");
		}
		return emptyList;
	}
}
