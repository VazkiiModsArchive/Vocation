package vazkii.vocation.common.core;

import java.util.List;

public class Message {

	public String id;
	public String message;
	public String narrator;
	public int time;
	public String triggerMode;
	public List<Trigger> triggers;
	public List<Action> actions;
	
	@Override
	public String toString() {
		return "Message["
				+ "id=" + id
				+ " message=" + message
				+ " narrator=" + narrator
				+ " time=" + time
				+ " triggerMode=" + triggerMode
				+ " triggers=" + triggers
				+ " actions=" + actions
				+ "]";
	}
	
}
