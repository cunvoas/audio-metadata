/**
 * 
 */
package com.github.cunvoas.audio.walker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.job.Job;

/**
 * Walker for music files.
 * @author CUNVOAS
 */
public class MusicDirectoryWalker extends DirectoryWalker<File> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MusicDirectoryWalker.class);

	private static final IOFileFilter webPageFilter = FileFilterUtils.or(
			FileFilterUtils.suffixFileFilter(SupportedFileFormat.MP3.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.FLAC.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.OGG.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.MP4.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.M4A.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.M4B.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.M4P.getFilesuffix(), IOCase.INSENSITIVE)
			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.WMA.getFilesuffix(), IOCase.INSENSITIVE)
//			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.WAV.getFilesuffix(), IOCase.INSENSITIVE)
//			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.RA.getFilesuffix(), IOCase.INSENSITIVE)
//			, FileFilterUtils.suffixFileFilter(SupportedFileFormat.RM.getFilesuffix(), IOCase.INSENSITIVE)
		);

	private boolean performActive=true;
	private Job jobProcess;

	public MusicDirectoryWalker() {
		super(FileFilterUtils.directoryFileFilter(), webPageFilter, -1);
	}
	
	private int nbPerformed=0;
	private int nbSkipped=0;
	private int nbError=0;

	/**
	 * @see org.apache.commons.io.DirectoryWalker#handleFile(java.io.File, int,
	 *      java.util.Collection)
	 */
	@Override
	public void handleFile(File file, int depth, Collection<File> results)
			throws IOException {

		LOGGER.debug("perform {}", file.getAbsolutePath());
		if (performActive) {
			// insert business code here
			jobProcess.process(file);
			
			nbPerformed++;
		}

	}

	/**
	 * walk in directories.
	 * @param directory
	 * @return
	 */
	public List<File> perform(File directory) {
		
		List<File> others = new ArrayList<File>();
		long start = System.currentTimeMillis();
		nbPerformed=0;
		nbSkipped=0;
		nbError=0;
		try {
			walk(directory, others);

		} catch (IOException e) {
			LOGGER.error("Problem finding configuration files!", e);
		} finally {
			long duration =  (System.currentTimeMillis()-start)/1000;
			LOGGER.info("performed: {}, Skipped: {}, Error: {}, in {}s", nbPerformed, nbSkipped, nbError, duration);
		}
		return others;
	}


	/**
	 * Setter for jobProcess.
	 * @param jobProcess the jobProcess to set
	 */
	public void setJobProcess(Job jobProcess) {
		this.jobProcess = jobProcess;
	}

}
