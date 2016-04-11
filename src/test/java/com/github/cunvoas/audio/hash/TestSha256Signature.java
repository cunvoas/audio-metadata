package com.github.cunvoas.audio.hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class TestSha256Signature {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHashString() {
		try {
			String sha = Sha256Signature.hash("this is a test");
			assertEquals("2e99758548972a8e8822ad47fa1017ff72f06f3ff6a016851f45c398732bc50c", sha);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}@Test
	public void testHashFile() {
		try {
			
			URL filerlUrl =  Thread.currentThread().getContextClassLoader().getResource("sha256-test.txt");
			
			String sha = Sha256Signature.hash(new File(filerlUrl.getPath()));
			assertEquals("785d3929ee14caa9b4c6c91e506e5453f2581eba8509f383bb75262056defd6f", sha);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	

}
