package com.psu.entry;

public class FacultyEntry {

	private String FacName;

	public String getFacName() {
		return FacName;
	}

	public void setFacName(String facName) {
		FacName = facName;
	}

	public FacultyEntry(String facName) {
		super();
		FacName = facName;
	}
}
