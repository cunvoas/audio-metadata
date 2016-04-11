package com.github.cunvoas.audio.hash;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHmacSha1Signature {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateRFC2104HMAC() {

		try {
			String hmac = HmacSha1Signature.hash("data", "key");

			Assert.assertEquals("104152c5bfdca07bc633eebd46199f0255c9f49d", hmac);
		} catch (Exception e) {
			Assert.fail(e.getMessage()) ;
		}
	}

}
