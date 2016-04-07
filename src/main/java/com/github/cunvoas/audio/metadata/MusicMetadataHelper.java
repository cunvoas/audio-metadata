package com.github.cunvoas.audio.metadata;

import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.reference.PictureTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicMetadataHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(MusicMetadataHelper.class);

	private static void merge(MusicMetadata currentMetadata, MusicMetadata newMetadata) {
		if (currentMetadata==null) {
			currentMetadata = newMetadata;
		} else {
		
			if (StringUtils.isNotBlank(newMetadata.getYear())) {
				currentMetadata.setYear(newMetadata.getYear());
			}
			if (StringUtils.isNotBlank(newMetadata.getAlbumArtist())) {
				currentMetadata.setAlbumArtist(newMetadata.getAlbumArtist());
			}
					
			if (StringUtils.isNotBlank(newMetadata.getAlbum())) {
				currentMetadata.setAlbum(newMetadata.getAlbum());
			}
			
			if (StringUtils.isNotBlank(newMetadata.getTitle())) {
				currentMetadata.setTitle(newMetadata.getTitle());
			}
			
			if (StringUtils.isNotBlank(newMetadata.getArtist())) {
				currentMetadata.setArtist(newMetadata.getArtist());
			}
			
			if (StringUtils.isNotBlank(newMetadata.getTrack())) {
				currentMetadata.setTrack(newMetadata.getTrack());
			}
			
			if (StringUtils.isNotBlank(newMetadata.getTotalTracks())) {
				currentMetadata.setTotalTracks(newMetadata.getTotalTracks());
			}
		}
		
	}
	
	/**
	 * @param music
	 * @param md
	 */
	public static void update (File music, MusicMetadata md) {
		if (music.isFile()) {
			MusicMetadata currentMetadata = extract(music);
			merge(currentMetadata, md);
			try {
				
				AudioFile f = AudioFileIO.read(music);
				Tag tag = f.getTagOrCreateDefault();
				
				tag.setField(FieldKey.YEAR, currentMetadata.getYear());
				tag.setField(FieldKey.ALBUM_ARTIST, currentMetadata.getAlbumArtist());
				tag.setField(FieldKey.ALBUM, currentMetadata.getAlbum());
				
				tag.setField(FieldKey.TITLE, currentMetadata.getTitle());
				tag.setField(FieldKey.ARTIST, currentMetadata.getArtist());
				
				tag.setField(FieldKey.TRACK, currentMetadata.getTrack());
				tag.setField(FieldKey.TRACK_TOTAL, currentMetadata.getTotalTracks());
				
				// FIX Artwork PictureType=3
				if (md.getImageFile()!=null && !"null".equals(md.getImageFile())) {
					Artwork artwork = tag.getArtworkList().get(0);
					artwork.setFromFile(new File(md.getImageFile()));
					artwork.setPictureType(PictureTypes.DEFAULT_ID);
				}
				
				f.commit();
			} catch (Exception e) {
				LOGGER.error("Metadata update {}", music.getAbsoluteFile());
			}
		}
	}
	
	public static MusicMetadata extract(File music) {
		MusicMetadata md = null;
		if (music.isFile()) {
			md = new MusicMetadata();
			md.setMusicFile(music.getAbsolutePath());
			try {
				AudioFile f = AudioFileIO.read(music);
				Tag tag = f.getTag();
				
				md.setYear(tag.getFirst(FieldKey.YEAR));
				md.setAlbum(tag.getFirst(FieldKey.ALBUM));
				md.setAlbumArtist(tag.getFirst(FieldKey.ALBUM_ARTIST));
				
				md.setArtist(tag.getFirst(FieldKey.ARTIST));
				md.setTitle(tag.getFirst(FieldKey.TITLE));
				md.setTrack(tag.getFirst(FieldKey.TRACK));
				md.setTotalTracks(tag.getFirst(FieldKey.TRACK_TOTAL));
				
				
				LOGGER.info("file {} avec {} images", music.getAbsoluteFile(), tag.getArtworkList()!=null?tag.getArtworkList().size():0);
				for (Artwork artwork : tag.getArtworkList()) {
					if (PictureTypes.DEFAULT_ID == artwork.getPictureType()) {
						BufferedImage img = artwork.getImage();
						if (img!=null) {
							md.setImage(true);
							md.setImgWidth(img.getWidth());
							md.setImgHeight(img.getHeight());
						}
					}
				}
				
			} catch (Exception e) {
				LOGGER.error("Metadata extraction {}", music.getAbsoluteFile());
				md = null;
			}
		}
		
		return md;
	}
}
