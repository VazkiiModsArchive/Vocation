package vazkii.vocation.common.core;

public class Trigger {

	public String trigger;
	public String key;
	public String value;
	public StackWrapper stack = null;
	
	@Override
	public String toString() {
		return "Trigger["
				+ "trigger=" + trigger
				+ " key=" + key
				+ " value=" + value
				+ " stack=" + stack
				+ "]";
	}
}
