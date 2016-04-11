package com.github.cunvoas.audio.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;

/**
 * @author CUNVOAS
 */
public class Sha256Signature {
	
	/**
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String hash(String data) throws NoSuchAlgorithmException {
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(data.getBytes());
		byte[] digest = sha.digest();
		return HashHex.fast(digest);
	}
	
	/**
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException 
	 */
	public static String hash(File data) throws NoSuchAlgorithmException, IOException {
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] dataBytes = new byte[1024];
		int nread = 0; 
		InputStream fis = null;
		try {
			fis = new FileInputStream(data);
			while ((nread = fis.read(dataBytes)) != -1) {
				sha.update(dataBytes, 0, nread);
			};
		} finally {
			IOUtils.closeQuietly(fis);
		}
	        
		byte[] digest = sha.digest();
		return HashHex.slow(digest);
	}
}
