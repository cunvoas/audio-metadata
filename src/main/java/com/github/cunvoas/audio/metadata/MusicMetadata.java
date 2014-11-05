package com.github.cunvoas.audio.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Metadata processed.
 * @author CUNVOAS
 */
public class MusicMetadata {
	private String year;
	private String artist;
	private String album;
	private String title;
	private String track;
	private String totalTracks;

	private boolean image;
	private int imgWidth;
	private int imgHeight;
	private String imgLocation;

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

	/**
	 * Getter for imgLocation.
	 * 
	 * @return the imgLocation
	 */
	public String getImgLocation() {
		return imgLocation;
	}

	/**
	 * Setter for imgLocation.
	 * 
	 * @param imgLocation
	 *            the imgLocation to set
	 */
	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	public static String headerCsv() {
		return "Artist;Year;Album;Title;Track;Total;withImg;witdh;height;imgLocation";
	}

	public List<String> toCsv() {
		List<String> colList = new ArrayList<String>(10);
		
		colList.add(getArtist());
		colList.add(getYear());
		colList.add(getAlbum());
		colList.add(getTitle());
		colList.add(getTrack());
		colList.add(getTotalTracks());
		colList.add(String.valueOf(isImage()));
		colList.add(String.valueOf(getImgWidth()));
		colList.add(String.valueOf(getImgHeight()));
		colList.add(String.valueOf(getImgLocation()));
		
		return colList;
	}
}
