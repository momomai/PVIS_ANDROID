package com.psu.entry;

public class ScrollEntry {
	public int position;
	public int y;
	
	public ScrollEntry(int position, int y) {
		super();
		this.position = position;
		this.y = y;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
