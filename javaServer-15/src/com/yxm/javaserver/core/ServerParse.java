package com.yxm.javaserver.core;

import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/*
 * 服务器启动阶段解析server.xml
 * @
 */
public class ServerParse {
  public static int getPort(){
	  int port = 8080;//默认800
	  try {
		SAXReader reader = new SAXReader();
		  Document document = reader.read(new File("conf/server.xml"));
	      String xPath = "/server/service/connector";
	      Element connectorElt = (Element)document.selectSingleNode(xPath);
	      Attribute portAttr = connectorElt.attribute("port");
	      port = Integer.parseInt(portAttr.getValue());
	  } catch (DocumentException e) {
		e.printStackTrace();
	  }
	  return port;
  }
}
