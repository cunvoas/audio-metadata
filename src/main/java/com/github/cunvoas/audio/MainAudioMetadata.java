package com.github.cunvoas.audio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobExtraction;
import com.github.cunvoas.audio.job.JobMode;
import com.github.cunvoas.audio.walker.MusicDirectoryWalker;

public class MainAudioMetadata {

	public static void main(String[] args) {
		
		String metaData = "c:/Music/meta.csv";
		Job jobProcess = null;
		try {
			jobProcess = new JobExtraction(new File(metaData));
			
			MusicDirectoryWalker walker = new MusicDirectoryWalker(JobMode.EXTRACT_METADATA);
			walker.setJobProcess(jobProcess);
			
			//walker.setPerformActive(false);
			walker.perform(new File("C:/Music/"));
 
			
		} catch (FileNotFoundException e) {
			System.err.println(e);;
		} catch (IOException e) {
			System.err.println(e);;
		} finally {
			jobProcess.finish();
		}
	}

}
