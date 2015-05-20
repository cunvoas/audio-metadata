package com.github.cunvoas.audio;

import java.io.File;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobExtraction;
import com.github.cunvoas.audio.job.JobMode;
import com.github.cunvoas.audio.walker.MusicDirectoryWalker;

public class MainAudioMetadata {

	public static void main(String[] args) {
		
		Job jobProcess = new JobExtraction(null);
		
		MusicDirectoryWalker walker = new MusicDirectoryWalker(JobMode.EXTRACT_METADATA);
		walker.setJobProcess(jobProcess);
		
		//walker.setPerformActive(false);
		walker.perform(new File("D:/Music/"));
 
	}

}
