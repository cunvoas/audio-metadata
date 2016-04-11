package com.github.cunvoas.audio;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileExistsException;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobAddCover;
import com.github.cunvoas.audio.walker.MusicDirectoryWalker;

public class MainAddFrontCover {

	public static void main(String[] args) {
		String coverFile = "D:/Music/FLAC/Idensity/2013- Chronicles/Chronicles.jpg";
		String musicFolder = "D:/logs";
		
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
