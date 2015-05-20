/**
 * 
 */
package com.github.cunvoas.audio.walker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobMode;

/**
 * Walker for music files.
 * @author CUNVOAS
 */
public class MusicDirectoryWalker extends DirectoryWalker<File> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MusicDirectoryWalker.class);

	private static final IOFileFilter webPageFilter = FileFilterUtils.or(
			FileFilterUtils.suffixFileFilter(".mp3"),
			FileFilterUtils.suffixFileFilter(".flac"),
			FileFilterUtils.suffixFileFilter(".ogg")
			);

	private boolean performActive=true;
	private JobMode mode = JobMode.EXTRACT_METADATA;
	private Job jobProcess;

	public MusicDirectoryWalker(JobMode mode) {
		super(FileFilterUtils.directoryFileFilter(), webPageFilter, -1);
		this.mode = mode;
		
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
		
		if (! mode.equals(jobProcess.getMode())) {
			LOGGER.error("Bad mode configuration");
			return null;
		}
		
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
