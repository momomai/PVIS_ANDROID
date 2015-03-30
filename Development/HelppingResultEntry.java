package com.psu.entry;

public class HelppingResultEntry {
	private String RequestID,HelpingName,HelpingID,ProjectName,RequestName,RequestDetail,RequestImage,DateTimeStart,DateTimeStop,Counter,Activate;

	

	

	public String getRequestID() {
		return RequestID;
	}

	public void setRequestID(String requestID) {
		RequestID = requestID;
	}

	public String getHelpingName() {
		return HelpingName;
	}

	public void setHelpingNAME(String helpingName) {
		HelpingName = helpingName;
	}

	public String getHelpingID() {
		return HelpingID;
	}

	public void setHelpingID(String helpingID) {
		HelpingID = helpingID;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getRequestName() {
		return RequestName;
	}

	public void setRequestName(String requestName) {
		RequestName = requestName;
	}

	public String getRequestDetail() {
		return RequestDetail;
	}

	public void setRequestDetail(String requestDetail) {
		RequestDetail = requestDetail;
	}

	public String getRequestImage() {
		return RequestImage;
	}

	public void setRequestImage(String requestImage) {
		RequestImage = requestImage;
	}

	public String getDateTimeStart() {
		return DateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		DateTimeStart = dateTimeStart;
	}

	public String getDateTimeStop() {
		return DateTimeStop;
	}

	public void setDateTimeStop(String dateTimeStop) {
		DateTimeStop = dateTimeStop;
	}

	public String getCounter() {
		return Counter;
	}

	public void setCounter(String counter) {
		Counter = counter;
	}
	public String getActivate() {
		return Activate;
	}

	public void setActivate(String activate) {
		Activate = activate;
	}

	public HelppingResultEntry(String requestID, String helpingName,
			String helpingID, String projectName, String requestName,
			String requestDetail, String requestImage, String dateTimeStart,
			String dateTimeStop, String counter, String activate) {
		super();
		RequestID = requestID;
		HelpingName = helpingName;
		HelpingID = helpingID;
		ProjectName = projectName;
		RequestName = requestName;
		RequestDetail = requestDetail;
		RequestImage = requestImage;
		DateTimeStart = dateTimeStart;
		DateTimeStop = dateTimeStop;
		Counter = counter;
		Activate = activate;
	}
	
}
