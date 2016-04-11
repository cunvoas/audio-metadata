package com.github.cunvoas.audio.job;

public enum JobMode {

	EXTRACT_METADATA("EXTRACT"),
	WRITE_METADATA("WRITE"),
	WRITE_COVER("COVER");

	private String jobMode;

	JobMode(String mode) {
		jobMode = mode;
	}

	public String valueOf() {
		return jobMode;
	}

}
