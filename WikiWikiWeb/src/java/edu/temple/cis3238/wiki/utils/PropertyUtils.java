/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.utils;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Doreen, Dan, Christian
 */
public class PropertyUtils {
 public static Properties getFromClassPath(String fileName){
      Properties properties = new Properties();
      try {
         ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
         properties.load(classLoader.getResourceAsStream(fileName));
         
      }
      catch ( IOException ex ) {
         Logger.getLogger(PropertyUtils.class.getName()).log(Level.SEVERE, null, ex);
         ex.printStackTrace();
         
      }
      
      return properties;
   }
   
   public static String getValue(Properties properties, String key){
     if (properties == null || key == null || key.isEmpty()){
        return "";
     }
	 
         return properties.getProperty(key, "");
      
   }
}
