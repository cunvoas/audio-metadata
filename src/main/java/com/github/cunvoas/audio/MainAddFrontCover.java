package com.github.cunvoas.audio;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileExistsException;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobAddCover;
import com.github.cunvoas.audio.walker.MusicDirectoryWalker;

public class MainAddFrontCover {

	public static void main(String[] args) {
		String coverFile = "D:/Music/_GSM/Music_Card/WMA/Dagoba/2015-Tales of the Black Dawn/Dagoba-Tales-of-the-Black-Dawn.png";
		String musicFolder = "D:/Music/_GSM/Music_Card/WMA/Dagoba/2015-Tales of the Black Dawn";
		
		Job jobProcess = null;
		try {
			File fileCover = new File(coverFile);
			if (!fileCover.isFile()) {
				throw new FileExistsException("cover is not a file");
			}
			
			MusicDirectoryWalker walker = new MusicDirectoryWalker();
			jobProcess = new JobAddCover(null);
			((JobAddCover)jobProcess).setCoverFile(fileCover);
			
			
			walker.setJobProcess(jobProcess);
			walker.perform(new File(musicFolder));
			
			
		} catch (FileNotFoundException e) {
			System.err.println(e);;
		} catch (Exception e) {
			System.err.println(e);;
		} finally {
			jobProcess.finish();
		}

	}

}
