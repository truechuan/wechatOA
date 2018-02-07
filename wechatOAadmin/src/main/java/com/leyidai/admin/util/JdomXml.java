package com.leyidai.admin.util;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
/**
 * read xml
 * @author song
 */
@Component
public class JdomXml{
	private static final Logger log = LoggerFactory.getLogger(JdomXml.class);
	
	public Element getRoot(String response) throws IOException {
    	log.debug("read xml start");
    	log.debug("read this xml {}", response);
      
     StringReader read = new StringReader(response);
      
     InputSource source = new InputSource(read);
      
     SAXBuilder sb = new SAXBuilder();
     Element root = null;
     try{
        Document doc = sb.build(source);
        root = doc.getRootElement();
         
      }catch(JDOMException e){
            e.printStackTrace();
      }catch(IOException e){
            e.printStackTrace();
      }
     
     log.debug("read xml end");
     return root;
   }
}
