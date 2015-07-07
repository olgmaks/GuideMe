package com.epam.gm.web.servlets.frontcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FrontUtil {

	private static boolean checkInterface(Class<?> clazz, String interfaceName) {

		boolean found = false;
		Class<?>[] interfaces = clazz.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			if (interfaces[i].getName().equals(interfaceName)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public static Map<String, HttpRequestHandler> buildHandlers(String propsFile) throws Exception {
		Map<String, HttpRequestHandler> handlers = new HashMap<>();
		Properties props = new Properties();
		FileInputStream proStr = null;
		try {
			proStr = new FileInputStream(propsFile);
			props.load(proStr);
			Enumeration<?> enKeys = props.propertyNames();
			while (enKeys.hasMoreElements()) {
				String key = (String) enKeys.nextElement();
				String clazz = props.getProperty(key);
				
				System.out.println("key:" + key);
				System.out.println("clazz:" + clazz);
				
				Class<?> handClazz = Class.forName(clazz);
				
				System.out.println("handClazz=" + handClazz);
				
				if (checkInterface(handClazz, "com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler")) {
					HttpRequestHandler handler = (HttpRequestHandler) handClazz.newInstance();
					handlers.put(key, handler);
				} else {
					throw new Exception("com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler interface ");
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				proStr.close();
			} catch (IOException e) {
				throw new Exception(e);
			}
		}
		return handlers;
	}
	

}
