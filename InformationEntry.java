package com.psu.entry;

public class InformationEntry {
	private String  ActivityName, DetailActivity, Objective;
	private String ActivityID,AddressID;
	
	
	
	
	public String getActivityName() {
		return ActivityName;
	}
	public void setActivityName(String activityName) {
		ActivityName = activityName;
	}
	public String getDetailActivity() {
		return DetailActivity;
	}
	public void setDetailActivity(String detailActivity) {
		DetailActivity = detailActivity;
	}
	public String getObjective() {
		return Objective;
	}
	public void setObjective(String objective) {
		Objective = objective;
	}
	public String getActivityID() {
		return ActivityID;
	}
	public void setActivityID(String activityID) {
		ActivityID = activityID;
	}
	public String getAddressID() {
		return AddressID;
	}
	public void setAddressID(String addressID) {
		AddressID = addressID;
	}
	

	public InformationEntry(String activityName, String detailActivity, String objective,
			String activityID, String addressID) {
		super();
		this.ActivityName = activityName;
		this.DetailActivity = detailActivity;
		this.Objective = objective;
		this.ActivityID = activityID;
		this.AddressID = addressID;
	}

}
