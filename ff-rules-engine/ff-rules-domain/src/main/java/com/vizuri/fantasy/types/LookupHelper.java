package com.vizuri.fantasy.types;

import java.util.ArrayList;
import java.util.List;

public class LookupHelper {
	private static List<String> severityValues = new ArrayList<String>();
	private static List<String> statusTypeValues = new ArrayList<String>();
	private static List<String> violationTitleValues = new ArrayList<String>();
	
	public static List<String> getSeverityValues() {
		if (severityValues.isEmpty()) {
			for (Severity severity : Severity.values()) {
				severityValues.add(severity.getName());
			}
		}
		return severityValues;
	}
	
	public static List<String> getStatusTypeValues() {
		if (statusTypeValues.isEmpty()) {
			for (PlayerStatusType statusType : PlayerStatusType.values()) {
				statusTypeValues.add(statusType.getDescription());
			}
		}
		return statusTypeValues;
	}
	
	public static List<String> getViolationTitleValues() {
		if (violationTitleValues.isEmpty()) {
			for (ViolationTitleType type : ViolationTitleType.values()) {
				violationTitleValues.add(type.getTitle());
			}
		}
		return violationTitleValues;
	}
}
