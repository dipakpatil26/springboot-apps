package com.filter.data;


/**
 * Value Object for JobData
 * 
 * @author Dipak Patil
 */
public class JobData {
	String uuid;
	String suggestion;
	String normalizedJobTitle;
	String parentUuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getNormalizedJobTitle() {
		return normalizedJobTitle;
	}

	public void setNormalizedJobTitle(String normalizedJobTitle) {
		this.normalizedJobTitle = normalizedJobTitle;
	}

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}
}
