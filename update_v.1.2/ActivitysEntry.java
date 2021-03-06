package com.psu.entry;

public class ActivitysEntry {

	private String activityName;
	private String detailActivity;
	private String solutions;
	private String objective;
	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getDetailActivity() {
		return detailActivity;
	}
	public void setDetailActivity(String detailActivity) {
		this.detailActivity = detailActivity;
	}
	public String getSolutions() {
		return solutions;
	}
	public void setSolutions(String solutions) {
		this.solutions = solutions;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public ActivitysEntry(String activityName, String detailActivity,
			String solutions, String objective) {
		super();
		this.activityName = activityName;
		this.detailActivity = detailActivity;
		this.solutions = solutions;
		this.objective = objective;
	}
}
