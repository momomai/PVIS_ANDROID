package com.psu.entry;

public class HelpingEntry {
	private String HelpingName;
	private String HelpingID;
	public String getHelpingName() {
		return HelpingName;
	}
	public void setHelpingName(String helpingName) {
		HelpingName = helpingName;
	}
	public String getHelpingID() {
		return HelpingID;
	}
	public void setHelpingID(String helpingID) {
		HelpingID = helpingID;
	}
	public HelpingEntry(String helpingName, String helpingID) {
		super();
		HelpingName = helpingName;
		HelpingID = helpingID;
	}


	

}
