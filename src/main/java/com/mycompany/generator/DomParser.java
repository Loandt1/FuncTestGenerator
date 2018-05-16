package com.mycompany.generator;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mycompany.model.MuleApp;
import com.mycompany.model.MuleFlow;
import com.mycompany.model.MuleHttpListener;
import com.mycompany.model.MuleHttpListenerConfig;
import com.mycompany.model.MulePayload;

public class DomParser {
	private String fileName;
	public DomParser(String name) {
		fileName = name;
	}
	
	public MuleApp parseFromXml(){
		MuleApp app = new MuleApp();
		try {
			File inputFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
//			System.out.println("----------------------------");
//			System.out.println("Flow element: ");
			NodeList flowList = doc.getElementsByTagName("flow");
			
			for (int temp = 0; temp < flowList.getLength(); temp++) {
				
				Node flowNode = flowList.item(temp);
//				System.out.println("\nCurrent Element :" + flowNode.getNodeName());

				if (flowNode.getNodeType() == Node.ELEMENT_NODE) {
					MuleFlow flow = new MuleFlow();
					Element flowElement = (Element) flowNode;
					flow.setName(flowElement.getAttribute("name"));
					flow.setDocid(flowElement.getAttribute("doc:id"));
					System.out.println("Flow name : " + flowElement.getAttribute("name"));
//					System.out.println("Flow doc id : " 
//							+ flowElement.getAttribute("doc:id"));
					
//					System.out.println("----------------------------");
//					System.out.println("Flow Http Listener element: ");
					NodeList httpListenerList = flowElement.getElementsByTagName("http:listener");
					for (int i = 0; i < httpListenerList.getLength(); i++) {
						Node httpListenerNode = httpListenerList.item(i);
//						System.out.println("\nCurrent Element :" + httpListenerNode.getNodeName());

						if (httpListenerNode.getNodeType() == Node.ELEMENT_NODE) {
							MuleHttpListener httpListener = new MuleHttpListener();
							Element httpListenerElement = (Element) httpListenerNode;
							if (httpListenerElement.hasAttribute("name"))
								httpListener.setName(httpListenerElement.getAttribute("name"));
							if (httpListenerElement.hasAttribute("path"))
								httpListener.setPath(httpListenerElement.getAttribute("path"));
							if (httpListenerElement.hasAttribute("allowedMethods"))
								httpListener.setAllowedMethod(httpListenerElement.getAttribute("allowedMethods"));
							flow.setHttpListener(httpListener);
//							System.out.println("Name : " 
//									+ httpListenerElement.getAttribute("name"));
//							System.out.println("Path : " 
//									+ httpListenerElement.getAttribute("path"));
						}
					}
					
//					System.out.println("----------------------------");
//					System.out.println("Flow Payload element: ");
					NodeList payloadList = flowElement.getElementsByTagName("set-payload");
					for (int i = 0; i < payloadList.getLength(); i++) {
						Node payLoadNode = payloadList.item(i);
//						System.out.println("\nCurrent Element :" + payLoadNode.getNodeName());

						if (payLoadNode.getNodeType() == Node.ELEMENT_NODE) {
							MulePayload payload = new MulePayload();
							Element payLoadElement = (Element) payLoadNode;
							if (payLoadElement.hasAttribute("value"))
								payload.setValue(payLoadElement.getAttribute("value"));
							if (payLoadElement.hasAttribute("mimeType"))
								payload.setMimeType(payLoadElement.getAttribute("mimeType"));
							flow.setPayload(payload);
//							System.out.println("Value : " 
//									+ payLoadElement.getAttribute("value"));
//							System.out.println("Mime Type : " 
//									+ payLoadElement.getAttribute("mimeType"));
						}
					}
					
					app.appendFlow(flow);
				}
			}
			
//			System.out.println("----------------------------");
//			System.out.println("Http Listener Config element: ");
			NodeList httpListenerConnection = doc.getElementsByTagName("http:listener-connection");
			for (int temp = 0; temp < httpListenerConnection.getLength(); temp++) {
				Node httpListenerConfigNode = httpListenerConnection.item(temp);
//				System.out.println("\nCurrent Element :" + httpListenerConfigNode.getNodeName());

				if (httpListenerConfigNode.getNodeType() == Node.ELEMENT_NODE) {
					MuleHttpListenerConfig httpListenerConfig = new MuleHttpListenerConfig();
					Element httpListenerConfigElement = (Element) httpListenerConfigNode;
					if (httpListenerConfigElement.hasAttribute("host"))
						httpListenerConfig.setHost(httpListenerConfigElement.getAttribute("host"));
					if (httpListenerConfigElement.hasAttribute("port"))
						httpListenerConfig.setPort(Integer.parseInt(httpListenerConfigElement.getAttribute("port")));
					app.setListenerConfig(httpListenerConfig);
//					System.out.println("Host : " 
//							+ httpListenerConfigElement.getAttribute("host"));
//					System.out.println("Port : " 
//							+ httpListenerConfigElement.getAttribute("port"));
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return app;
	}
}
