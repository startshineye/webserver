package com.yxm.javaserver.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析每个WebApp的web.xml
 * @author yxm
 *
 */
public class WebParse {
     public static Map<String,Map<String,String>> servletMaps=new HashMap<String,Map<String,String>>();
     public static void parse(String[] webAppNames) throws DocumentException{
    	 for(String webAppName:webAppNames){
    		 Map<String,String> servletMap = parse(webAppName);
    		 servletMaps.put(webAppName, servletMap);
    	 }
     }
     private static Map<String,String> parse(String webAppName) throws DocumentException{
    	String webFilePath = webAppName + "/WEB-INF/web.xml";
    	SAXReader reader = new SAXReader();
    	Document document = reader.read(webFilePath);
    	List<Element> servletNodes = document.selectNodes("/web-app/servlet");
    	Map<String, String> servletInfoMap = new HashMap<String,String>();
        for (Element servletNode : servletNodes) {
        	Element servletNameNode = (Element)servletNode.selectSingleNode("servlet-name");
        	String servletName = servletNameNode.getTextTrim();
        	
        	Element servletClassNode = (Element)servletNode.selectSingleNode("servlet-class");
        	String servletClass = servletClassNode.getTextTrim();
        	servletInfoMap.put(servletName, servletClass);
        }
        List<Element> servletMappingNodes = document.selectNodes("/web-app/servlet-mapping");
    	Map<String, String> servletMappingInfoMap = new HashMap<String,String>();
        for (Element servletMappingNode : servletMappingNodes) {
        	Element servletNameNode = (Element)servletMappingNode.selectSingleNode("servlet-name");
        	String servletName = servletNameNode.getTextTrim();
        	
        	Element servletPathNode = (Element)servletMappingNode.selectSingleNode("url-pattern");
        	String servletPath = servletPathNode.getTextTrim();
        	servletMappingInfoMap.put(servletName, servletPath);
        }
         Map<String,String> servletMap = new HashMap<String,String>();
         Set<String> servletNames = servletMappingInfoMap.keySet();
          for (String servletName : servletNames) {
        	  String servletPath = servletMappingInfoMap.get(servletName);
        	  String servletClassName = servletInfoMap.get(servletName);
        	  servletMap.put(servletPath, servletClassName);
          }
          return servletMap;
     }
}












