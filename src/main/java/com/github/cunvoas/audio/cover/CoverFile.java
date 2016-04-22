/**
 * 
 */
package com.github.cunvoas.audio.cover;

import java.io.File;

/**
 * @author CUNVOAS
 */
public class CoverFile {
	private File coverFile;
	private int width ;
	private int height ;
	
	/**
	 * Getter for coverFile.
	 * @return the coverFile
	 */
	public final File getCoverFile() {
		return coverFile;
	}
	/**
	 * Setter for coverFile.
	 * @param coverFile the coverFile to set
	 */
	public final void setCoverFile(File coverFile) {
		this.coverFile = coverFile;
	}
	/**
	 * Getter for width.
	 * @return the width
	 */
	public final int getWidth() {
		return width;
	}
	/**
	 * Setter for width.
	 * @param width the width to set
	 */
	public final void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Getter for height.
	 * @return the height
	 */
	public final int getHeight() {
		return height;
	}
	/**
	 * Setter for height.
	 * @param height the height to set
	 */
	public final void setHeight(int height) {
		this.height = height;
	}

}
