/**
 * 
 */
package com.github.cunvoas.audio.metadata;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;

/**
 * @author CUNVOAS
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File testFile = new File("D:/Music/MP3/Dagoba/(2006) What Hell Is About/01 What Hell Is About.mp3");
		
		try {
			AudioFile f = AudioFileIO.read(testFile);
			Tag tag = f.getTag();
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(tag.getFirst(FieldKey.ARTIST));
			sBuilder.append("-");
			sBuilder.append(tag.getFirst(FieldKey.ALBUM));
			sBuilder.append(".");
			
			for (Artwork artwork : tag.getArtworkList()) {
				BufferedImage img = artwork.getImage();
				File outImg = new File("d:/tmp/"+sBuilder.toString());
				
				ImageIO.write(img, "jpg", outImg);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}
	
	
	

}
