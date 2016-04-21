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
public class MusicCoverDirectoryWalker extends DirectoryWalker<File> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MusicCoverDirectoryWalker.class);

	private static final IOFileFilter webPageFilter = FileFilterUtils.or(
			FileFilterUtils.suffixFileFilter(".mp3"),
			FileFilterUtils.suffixFileFilter(".flac"),
			FileFilterUtils.suffixFileFilter(".ogg")
			);
	
	private static final IOFileFilter coverFilter = FileFilterUtils.or(
			FileFilterUtils.suffixFileFilter(".jpg"),
			FileFilterUtils.suffixFileFilter(".jpeg")
			);
	
	private static final Map<String, File> COVERS=new HashMap<String, File>();

	private boolean performActive=true;
	private Job jobProcess;

	public MusicCoverDirectoryWalker() {
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

	private void performCover(File directory) {
		List<File> covers = FileFilterUtils.filterList(coverFilter, directory);
		for (File cover : covers) {
			COVERS.put(directory.getAbsolutePath(), cover);
		}
	}
	
	/**
	 * walk in directories.
	 * @param directory
	 * @return
	 */
	public List<File> perform(File directory) {
		performCover(directory);
		
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

	/**
	 * Getter for covers.
	 * @return the covers
	 */
	public static final File getCovers(String folderPath) {
		return COVERS.get(folderPath);
	}

}