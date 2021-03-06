package com.github.cunvoas.audio.metadata;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.reference.PictureTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cunvoas.audio.cover.CoverFile;

public class MusicMetadataHelper {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MusicMetadataHelper.class);

	private static void merge(MusicMetadata currentMetadata,
			MusicMetadata newMetadata) {
		if (currentMetadata == null) {
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

			if (!currentMetadata.isImage()
					&& (newMetadata.getImageFile() != null)) {
				currentMetadata.setImageFile(newMetadata.getImageFile());
			}
		}

	}

	/**
	 * Aff front cover ton music file.
	 * @param music
	 * @param frontCover
	 */
	public static void addFrontCover(File music, File frontCover) {
		if (music.isFile()) {
			MusicMetadata currentMetadata = extract(music);
			
			
			
			AudioFile f = null;
			try {
				Artwork artwork = new Artwork();
				artwork.setPictureType(PictureTypes.DEFAULT_ID);
				artwork.setFromFile(frontCover);
				
				
				f = AudioFileIO.read(music);
				Tag tag = f.getTagOrCreateDefault();
				
			//	tag.getArtworkList();
				if (currentMetadata.isImage() ) {
					tag.deleteArtworkField();
				}
				tag.addField(artwork);
				
			} catch (Exception e) {
				LOGGER.error("Metadata update {}", music.getAbsoluteFile());
			} finally {
				try {
					f.commit();
				} catch (CannotWriteException e) {
					LOGGER.error("commit", e.getMessage());
				}
			}
			
		}
	}
	

	/**
	 * Aff front cover ton music file.
	 * @param music
	 * @param frontCover
	 */
	public static void addFrontCover(File music, CoverFile coverFile) {
		if (music.isFile()) {
			boolean applyCover=false;
			MusicMetadata currentMetadata = extract(music);
			
			if (currentMetadata!=null && currentMetadata.isImage()) {
				if (currentMetadata.getImgHeight()>coverFile.getHeight() && currentMetadata.getImgWidth()>coverFile.getWidth()) {
					applyCover=true;
					LOGGER.info("replace cover {}",  music.getPath());
				} else {
					LOGGER.info("skip cover {}",  music.getPath());
				}
			} else {
				applyCover=true;
				LOGGER.info("add cover {}",  music.getPath());
			}
			
			if (applyCover) {
				AudioFile f = null;
				try {
					Artwork artwork = new Artwork();
					artwork.setPictureType(PictureTypes.DEFAULT_ID);
					artwork.setFromFile(coverFile.getCoverFile());
					
					f = AudioFileIO.read(music);
					Tag tag = f.getTagOrCreateDefault();
					
				//	tag.getArtworkList();
					if (currentMetadata.isImage() ) {
						tag.deleteArtworkField();
					}
					tag.addField(artwork);
					
				} catch (Exception e) {
					LOGGER.error("Metadata update {}", music.getAbsoluteFile());
				} finally {
					try {
						f.commit();
					} catch (CannotWriteException e) {
						LOGGER.error("commit", e.getMessage());
					}
				}
			}
		}
	}
	
	/**
	 * @param music
	 * @param md
	 */
	public static void update(File music, MusicMetadata md) {
		if (music.isFile()) {
			MusicMetadata currentMetadata = extract(music);
			merge(currentMetadata, md);
			AudioFile f = null;
			try {

				f = AudioFileIO.read(music);
				Tag tag = f.getTagOrCreateDefault();

				tag.setField(FieldKey.YEAR, currentMetadata.getYear());
				tag.setField(FieldKey.ALBUM_ARTIST,
						currentMetadata.getAlbumArtist());
				tag.setField(FieldKey.ALBUM, currentMetadata.getAlbum());

				tag.setField(FieldKey.TITLE, currentMetadata.getTitle());
				tag.setField(FieldKey.ARTIST, currentMetadata.getArtist());

				tag.setField(FieldKey.TRACK, currentMetadata.getTrack());
				tag.setField(FieldKey.TRACK_TOTAL,
						currentMetadata.getTotalTracks());

				// FIX Artwork PictureType=3
				if (!currentMetadata.isImage()
						&& currentMetadata.getImageFile() != null) {
					Artwork artwork = new Artwork();

					// tag.getArtworkList().get(0);
					artwork.setFromFile(new File(md.getImageFile()));
					artwork.setPictureType(PictureTypes.DEFAULT_ID);
					tag.addField(artwork);
				}

			} catch (Exception e) {
				LOGGER.error("Metadata update {}", music.getAbsoluteFile());
			} finally {
				try {
					f.commit();
				} catch (CannotWriteException e) {
					LOGGER.error("commit", e.getMessage());
				}
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

				if (LOGGER.isDebugEnabled()) {
					for (Iterator<TagField> tfi = tag.getFields(); tfi
							.hasNext();) {
						TagField tf = tfi.next();
						LOGGER.debug("{}={}", tf.getId(),
								tag.getFirst(tf.getId()));
					}
				}

				md.setYear(tag.getFirst(FieldKey.YEAR));
				md.setAlbum(tag.getFirst(FieldKey.ALBUM));
				md.setAlbumArtist(tag.getFirst(FieldKey.ALBUM_ARTIST));

				md.setArtist(tag.getFirst(FieldKey.ARTIST));
				md.setTitle(tag.getFirst(FieldKey.TITLE));
				md.setTrack(tag.getFirst(FieldKey.TRACK));
				md.setTotalTracks(tag.getFirst(FieldKey.TRACK_TOTAL));

				LOGGER.info("file {} avec {} images", music.getAbsoluteFile(),
						tag.getArtworkList() != null ? tag.getArtworkList()
								.size() : 0);
				for (Artwork artwork : tag.getArtworkList()) {
					if (PictureTypes.DEFAULT_ID == artwork.getPictureType()) {
						BufferedImage img = artwork.getImage();
						if (img != null) {
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
