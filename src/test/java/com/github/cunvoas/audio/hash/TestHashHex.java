package com.github.cunvoas.audio.hash;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestHashHex {
	
	static byte[] bytes = "ceci est un test".getBytes();

	@Test
	public void testM2() {
		Assert.assertEquals("636563692065737420756e2074657374", HashHex.medium(bytes));
	}

	@Test
	public void testM3() {
		Assert.assertEquals("636563692065737420756e2074657374", HashHex.fast(bytes));
	}

	@Test
	public void testToHexString() {
		Assert.assertEquals("636563692065737420756e2074657374", HashHex.slow(bytes));
	}

	@Test
	public void testPerf() {
		int nbLoop = 20000;
		long start=0;
		long t1, t2, t3;

		System.gc();
		start = System.nanoTime();
		for (int i = 0; i < nbLoop; i++) {
			HashHex.medium(bytes);
		}
		t1 = System.nanoTime()- start;
		
		
		System.gc();
		start = System.nanoTime();
		for (int i = 0; i < nbLoop; i++) {
			HashHex.fast(bytes);
		}
		t2 = System.nanoTime()- start;

		System.gc();
		start = System.nanoTime();
		for (int i = 0; i < nbLoop; i++) {
			HashHex.slow(bytes);
		}
		t3 = System.nanoTime()- start;

		System.out.println(t1/1e6);
		System.out.println(t2/1e6);
		System.out.println(t3/1e6);
		Assert.assertTrue("t1>t2", t1>t2);
		Assert.assertTrue("t3>t1", t3>t1);
		Assert.assertTrue("t3>t2", t3>t2);

	}
	

}
