package com.github.cunvoas.audio.hash;

import java.util.Formatter;

import org.apache.commons.io.IOUtils;

class HashHex {

	static String medium(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int lng = bytes.length;
		for (int i = 0; i < lng; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

	static String fast(byte[] bytes) {
		StringBuffer hexString = new StringBuffer();
		int lng = bytes.length;
		for (int i = 0; i < lng; i++) {
			hexString.append(Integer.toHexString(0xff & bytes[i]));
		}
		return hexString.toString();
	}

	/**
	 * @param bytes
	 * @return
	 */
	static String slow(byte[] bytes) {
		String ret = null;
		Formatter formatter = null;
		try {
			formatter = new Formatter();
			for (byte b : bytes) {
				formatter.format("%02x", b);
			}
			ret = formatter.toString();
		} finally {
			IOUtils.closeQuietly(formatter);
		}
		return ret;

	}
}
