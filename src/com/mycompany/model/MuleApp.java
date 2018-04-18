package com.mycompany.model;

import java.util.ArrayList;

public class MuleApp {
//	private MuleFlow flow;
	private MuleHttpListenerConfig listenerConfig;
	private ArrayList<MuleFlow> listFlows;
	
	public MuleApp() {
		listFlows = new ArrayList<MuleFlow>();
	}
	
//	public MuleFlow getFlow() {
//		return flow;
//	}
//	public void setFlow(MuleFlow flow) {
//		this.flow = flow;
//	}
	public MuleHttpListenerConfig getListenerConfig() {
		return listenerConfig;
	}
	public void setListenerConfig(MuleHttpListenerConfig listenerConfig) {
		this.listenerConfig = listenerConfig;
	}
	public void appendFlow(MuleFlow flow) {
		listFlows.add(flow);
	}

	public ArrayList<MuleFlow> getListFlows() {
		return listFlows;
	}

	public void setListFlows(ArrayList<MuleFlow> listFlows) {
		this.listFlows = listFlows;
	}
}
