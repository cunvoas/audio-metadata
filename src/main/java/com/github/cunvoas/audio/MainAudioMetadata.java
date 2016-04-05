package com.github.cunvoas.audio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobUpdate;
import com.github.cunvoas.audio.walker.MusicDirectoryWalker;

public class MainAudioMetadata {

	public static void main(String[] args) {
		String metaData = "I:/Music/split/meta.csv";
		String musicFolder = "I:/Music/split/";
		
		
		
		Job jobProcess = null;
		try {
			MusicDirectoryWalker walker = new MusicDirectoryWalker();
//			jobProcess = new JobExtraction(new File(metaData));
			jobProcess = new JobUpdate(new File(metaData));
			

			walker.setJobProcess(jobProcess);
			walker.perform(new File(musicFolder));
			
			
		} catch (FileNotFoundException e) {
			System.err.println(e);;
		} catch (IOException e) {
			System.err.println(e);;
		} finally {
			jobProcess.finish();
		}
	}

}