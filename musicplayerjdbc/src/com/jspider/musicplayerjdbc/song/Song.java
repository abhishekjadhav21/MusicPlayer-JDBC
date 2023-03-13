package com.jspider.musicplayerjdbc.song;

public class Song {
	
	private int id;
	private String songName;
	private String singerName;
	private String moveName;
	private double duration;

	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	

	@Override
	public String toString() {
		return "Song id:" + id + " \nsongName:" + songName + " \nsingerName:" + singerName + " \nmoveName=" + moveName
				+ "\nduration=" + duration;
	}


}
