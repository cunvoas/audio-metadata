package com.github.cunvoas.audio.metadata;

import java.awt.image.BufferedImage;
import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicMetadataHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(MusicMetadataHelper.class);
	
	public  MusicMetadata extract(File music) {
		MusicMetadata md = null;
		if (music.isFile()) {
			md = new MusicMetadata();
			try {
				AudioFile f = AudioFileIO.read(music);
				Tag tag = f.getTag();
				
				md.setYear(tag.getFirst(FieldKey.YEAR));
				md.setArtist(tag.getFirst(FieldKey.ARTIST));
				md.setAlbum(tag.getFirst(FieldKey.ALBUM));
				md.setTitle(tag.getFirst(FieldKey.TITLE));
				md.setTrack(tag.getFirst(FieldKey.TRACK));
				md.setTotalTracks(tag.getFirst(FieldKey.TRACK_TOTAL));
				
				
				LOGGER.info("file {} avec {} images", music.getAbsoluteFile(), tag.getArtworkList()!=null?tag.getArtworkList().size():0);
				for (Artwork artwork : tag.getArtworkList()) {
					BufferedImage img = artwork.getImage();
					if (img!=null) {
						md.setImage(true);
						md.setImgWidth(img.getWidth());
						md.setImgHeight(img.getHeight());
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
