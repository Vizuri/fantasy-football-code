/**
 * 
 */
package com.vizuri.fantasy.types;

/**
 * @author amirge
 *
 */
public enum Severity {
	INFO("INFO"),
	WARNING("WARNING"),
	ERROR("ERROR");
	
	private String name;
	
	Severity(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
