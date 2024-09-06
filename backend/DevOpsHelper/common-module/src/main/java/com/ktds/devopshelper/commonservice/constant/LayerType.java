package com.ktds.devopshelper.commonservice.constant;

public enum LayerType {
	PO("PO", "ProObject"),
	UI("UI", "Nexacro"),
	ESB("ESB", "Jdeveloper");
	
	private String cd;
	private String desc;
	
	LayerType(String cd, String desc){
		this.cd = cd;
		this.desc = desc;
	}
	
	public boolean hasLayerCode(String cd) {
		return this.cd.equals(cd);
	}
}
