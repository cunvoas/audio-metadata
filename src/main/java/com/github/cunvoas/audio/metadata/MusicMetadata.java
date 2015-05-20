package com.github.cunvoas.audio.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Metadata processed.
 * @author CUNVOAS
 */
public class MusicMetadata {

	public static final int NB_TAGS=12;
	
	private String musicFile;
	private String year;
	private String album;
	private String albumArtist;
	private String artist;
	private String title;
	private String track;
	private String totalTracks;

	private boolean image;
	private int imgWidth;
	private int imgHeight;
	private String imageFile;

	/**
	 * Getter for year.
	 * 
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Setter for year.
	 * 
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Getter for track.
	 * 
	 * @return the track
	 */
	public String getTrack() {
		return track;
	}

	/**
	 * Setter for track.
	 * 
	 * @param track
	 *            the track to set
	 */
	public void setTrack(String track) {
		this.track = track;
	}

	/**
	 * Getter for totalTracks.
	 * 
	 * @return the totalTracks
	 */
	public String getTotalTracks() {
		return totalTracks;
	}

	/**
	 * Setter for totalTracks.
	 * 
	 * @param totalTracks
	 *            the totalTracks to set
	 */
	public void setTotalTracks(String totalTracks) {
		this.totalTracks = totalTracks;
	}

	/**
	 * Getter for artist.
	 * 
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Setter for artist.
	 * 
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Getter for album.
	 * 
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Setter for album.
	 * 
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * Getter for title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for image.
	 * 
	 * @return the image
	 */
	public boolean isImage() {
		return image;
	}

	/**
	 * Setter for image.
	 * 
	 * @param image
	 *            the image to set
	 */
	public void setImage(boolean image) {
		this.image = image;
	}

	/**
	 * Getter for imgWidth.
	 * 
	 * @return the imgWidth
	 */
	public int getImgWidth() {
		return imgWidth;
	}

	/**
	 * Setter for imgWidth.
	 * 
	 * @param imgWidth
	 *            the imgWidth to set
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * Getter for imgHeight.
	 * 
	 * @return the imgHeight
	 */
	public int getImgHeight() {
		return imgHeight;
	}

	/**
	 * Setter for imgHeight.
	 * 
	 * @param imgHeight
	 *            the imgHeight to set
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}


	public static String headerCsv() {
		return "Music file;Year;Album;Album artist;Track;Total;Title;Artist;withImg;witdh;height;Image file";
	}

	public List<String> toCsv() {
		List<String> colList = new ArrayList<String>(NB_TAGS);
		
		colList.add(getMusicFile());
		colList.add(getYear());
		colList.add(getAlbum());
		colList.add(getAlbumArtist());

		colList.add(getTrack());
		colList.add(getTotalTracks());
		colList.add(getTitle());
		colList.add(getArtist());
		
		colList.add(String.valueOf(isImage()));
		colList.add(String.valueOf(getImgWidth()));
		colList.add(String.valueOf(getImgHeight()));
		colList.add(String.valueOf(getImageFile()));
		
		return colList;
	}

	/**
	 * Getter for musicFile.
	 * @return the musicFile
	 */
	public String getMusicFile() {
		return musicFile;
	}

	/**
	 * Setter for musicFile.
	 * @param musicFile the musicFile to set
	 */
	public void setMusicFile(String musicFile) {
		this.musicFile = musicFile;
	}

	/**
	 * Getter for albumArtist.
	 * @return the albumArtist
	 */
	public String getAlbumArtist() {
		return albumArtist;
	}

	/**
	 * Setter for albumArtist.
	 * @param albumArtist the albumArtist to set
	 */
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}

	/**
	 * Getter for imageFile.
	 * @return the imageFile
	 */
	public String getImageFile() {
		return imageFile;
	}

	/**
	 * Setter for imageFile.
	 * @param imageFile the imageFile to set
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
}
