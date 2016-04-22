package com.github.cunvoas.audio;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.cunvoas.audio.job.Job;
import com.github.cunvoas.audio.job.JobAddCoverTree;
import com.github.cunvoas.audio.job.JobCover;
import com.github.cunvoas.audio.walker.CoverDirectoryWalker;
import com.github.cunvoas.audio.walker.MusicDirectoryWalker;

public class MainAddFrontCoverTree {

	public static void main(String[] args) {
		String musicFolder = "D:/Music/MP3";
		
		Job jobProcess = null;
		JobCover jobCover=null;
		try {
			jobCover=new JobCover(null);
			CoverDirectoryWalker walkerCover = new CoverDirectoryWalker();
			walkerCover.setJobProcess(jobCover);
			walkerCover.perform(new File(musicFolder));
			
			
			MusicDirectoryWalker walker = new MusicDirectoryWalker();
			jobProcess = new JobAddCoverTree(null);
			
			walker.setJobProcess(jobProcess);
			walker.perform(new File(musicFolder));
			
			
		} catch (FileNotFoundException e) {
			System.err.println(e);;
		} catch (Exception e) {
			System.err.println(e);;
		} finally {
			jobProcess.finish();
			jobCover.finish();
		}

	}

}
