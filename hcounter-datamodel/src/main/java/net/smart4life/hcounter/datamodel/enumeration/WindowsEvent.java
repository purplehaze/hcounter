package net.smart4life.hcounter.datamodel.enumeration;

import java.util.Arrays;

public enum WindowsEvent {
	
	SYSTEM_START(6005, WorkEventType.START),
	SYSTEM_STOP(6006, WorkEventType.END);
	
	private final int code;
	private final WorkEventType workEventType;
	
	private WindowsEvent(int code, WorkEventType workEventType){
		this.code = code;
		this.workEventType = workEventType;
	}

	public int getCode() {
		return code;
	}

	public WorkEventType getWorkEventType() {
		return workEventType;
	}
	
	public static WindowsEvent getByCode(int windowsEventCode){
		return Arrays.asList(values()).stream()
				.filter(e -> e.code == windowsEventCode)
				.findFirst()
				.orElse(null);
	}
}
