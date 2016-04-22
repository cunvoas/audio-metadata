/**
 * 
 */
package com.github.cunvoas.audio.walker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.job.Job;

/**
 * Walker for music files.
 * @author CUNVOAS
 */
public class CoverDirectoryWalker extends DirectoryWalker<File> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CoverDirectoryWalker.class);

	private static final IOFileFilter coverFilter = FileFilterUtils.or(
			FileFilterUtils.suffixFileFilter(".jpg"),
			FileFilterUtils.suffixFileFilter(".jpeg")
			);
	

	private boolean performActive=true;
	private Job jobProcess;

	public CoverDirectoryWalker() {
		super(FileFilterUtils.directoryFileFilter(), coverFilter, -1);
	}
	
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
		try {
			walk(directory, others);

		} catch (IOException e) {
			LOGGER.error("Problem finding configuration files!", e);
		} finally {
			long duration =  (System.currentTimeMillis()-start)/1000;
			LOGGER.info("covers performed  in {}s", duration);
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
