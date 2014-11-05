package com.github.cunvoas.audio.report;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.metadata.MusicMetadata;
import com.github.cunvoas.audio.metadata.MusicMetadataHelper;

public class ReportCsv {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ReportCsv.class);

	public void produceImage(List<File> files, File filename) {
		CSVFormat format = CSVFormat.DEFAULT.withHeader("Image file;Width;Height").withDelimiter(';');
		CSVPrinter csvPrinter = null;
		PrintStream csvFilePrint = null;
		try {
			csvFilePrint = new PrintStream(filename);
			csvPrinter = new CSVPrinter(csvFilePrint, format);
			
			for (File file : files) {
				csvPrinter.print(file.getAbsolutePath());
				
				BufferedImage image = ImageIO.read(file);
				if (image!=null) {
					csvPrinter.print(image.getWidth());
					csvPrinter.print(image.getHeight());
				} else {
					csvPrinter.print("");
					csvPrinter.print("");
				}
				csvPrinter.println();
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("", e);
		} catch (IOException e) {
			LOGGER.error("", e);
		} finally {
			IOUtils.closeQuietly(csvFilePrint);
			IOUtils.closeQuietly(csvPrinter);
		}
	}
	
	public void produceMusic(List<File> files, File filename) {

		CSVFormat format = CSVFormat.DEFAULT.withHeader(
				"Music file;" + MusicMetadata.headerCsv()).withDelimiter(';');

		CSVPrinter csvPrinter = null;
		PrintStream csvFilePrint = null;
		try {
			csvFilePrint = new PrintStream(filename);
			csvPrinter = new CSVPrinter(csvFilePrint, format);

			List<String> colsList = new ArrayList<String>();

			MusicMetadataHelper musicMetadataHelper = new MusicMetadataHelper();
			for (File file : files) {
				colsList.add(file.getAbsolutePath());
				// image existante
				MusicMetadata metadata = musicMetadataHelper.extract(file);
				if (metadata!=null) {
					colsList.addAll(metadata.toCsv());
				} else {
					colsList.addAll(this.emptyMetaData());
				}
				this.formatLine(csvPrinter, colsList);
				colsList.clear();
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("", e);
		} catch (IOException e) {
			LOGGER.error("", e);
		} finally {
			IOUtils.closeQuietly(csvFilePrint);
			IOUtils.closeQuietly(csvPrinter);
		}
	}

	private void formatLine(CSVPrinter csvPrinter, List<String> data)
			throws IOException {
		for (String string : data) {
			csvPrinter.print(string != null ? string : "");
		}
		csvPrinter.println();
	}

	private List<String> emptyMetaData() {
		List<String> emptyList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			emptyList.add("");
		}
		return emptyList;
	}

}
