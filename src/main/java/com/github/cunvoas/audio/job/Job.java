package com.github.cunvoas.audio.job;

import java.io.File;

/**
 * @author CUNVOAS
 */
public abstract class Job {
	
	public Job(File metaDataFile) {
		super();
	}
	
	public abstract JobMode getMode();
	
	public abstract void process(File musicFile);

}
