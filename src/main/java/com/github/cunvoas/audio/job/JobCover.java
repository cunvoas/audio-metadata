package com.github.cunvoas.audio.job;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.cover.CoverFile;
import com.github.cunvoas.audio.cover.CoverMap;

/**
 * Jod for metadata extraction.
 * 
 * @author CUNVOAS
 */
public class JobCover extends Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCover.class);
	
	/**
	 * Constructor.
	 * @param metaDataFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public JobCover(File metaDataFile) throws IOException, FileNotFoundException {
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
	public void process(File cover) throws IOException {
		int maxSize=1001;
		String folderString = cover.getParent();
		
		if (!CoverMap.getCovers().containsKey(folderString)) {
			
			BufferedImage bimg = ImageIO.read(cover);
			int width          = bimg.getWidth();
			int height         = bimg.getHeight();
			CoverFile coverFile = new CoverFile();
			coverFile.setCoverFile(cover);
			coverFile.setHeight(height);
			coverFile.setWidth(width);
			
			CoverMap.getCovers().put(folderString, coverFile);
		} else {
			int cHeight = CoverMap.getCovers().get(folderString).getHeight();
			int cWidth = CoverMap.getCovers().get(folderString).getWidth();
			
			BufferedImage bimg = ImageIO.read(cover);
			int width          = bimg.getWidth();
			int height         = bimg.getHeight();
			
			if (width<maxSize && height<maxSize) {
				
				if (width>cWidth && height>cHeight) {
					
					CoverFile coverFile = new CoverFile();
					coverFile.setCoverFile(cover);
					coverFile.setHeight(height);
					coverFile.setWidth(width);
					
					CoverMap.getCovers().put(folderString, coverFile);
				}
			}
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
