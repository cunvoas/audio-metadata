package com.github.cunvoas.audio.file;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

class ImageFileFilter implements FileFilter, IOFileFilter {

	/**
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File file) {
		return accept(file.getName());
	}

	/**
	 * @see org.apache.commons.io.filefilter.IOFileFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File dir, String name) {
		return accept(name);
	}
	
	/**
	 * @param finename
	 * @return
	 */
	private boolean accept(String finename) {
		String ext = FilenameUtils.getExtension(finename).toLowerCase();
		return AUTHORIZED_EXTENTIONS.contains(ext);
	}
	
	private static final String AUTHORIZED_EXTENTIONS="jpg,jpeg,png";

}
