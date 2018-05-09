package com.sinotn.common.listener;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

public class ProxoolListener implements ServletContextListener {
	
	private static final Logger logger =  LogManager.getLogger(ProxoolListener.class);
	private static final String XML_FILE_PROPERTY = "xmlFile";

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("destroy database pool....");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		String appDir = arg0.getServletContext().getRealPath("/");
		Enumeration<?> names = context.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = context.getInitParameter(name);
			if (name.equals(XML_FILE_PROPERTY)) {
				try {
					File file = new File(value);
					if (file.isAbsolute()) {
						JAXPConfigurator.configure(value, false);
					} else {
						JAXPConfigurator.configure(appDir + File.separator
								+ value, false);
					}
					System.out.println("load database pool successful....");
				} catch (ProxoolException e) {
					System.out.println("load database pool error....");
					logger.error("Problem configuring " + value, e);
				}
			}
		}
	}

}
