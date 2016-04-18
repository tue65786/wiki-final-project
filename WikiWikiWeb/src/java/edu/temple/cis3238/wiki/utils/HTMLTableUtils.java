/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.utils;

/**
 * Markup helper for HTML tables
 * @author (c)2016
 */
public class HTMLTableUtils {
private String rowHeaderCSS;
private String rowCSS;
private String rowAltCSS;
private String tableROWFromArray(Object[] columnEntries,
                           String style){
    StringBuilder sb = new StringBuilder("");
   sb.append("<tr" + style + ">");
    for(Object columnEntry: columnEntries) {
      sb.append("    <td>" + columnEntry + "</td>");
    }
    sb.append("  </tr>");
	return sb.toString();
  }

  private String getStyle(String className) {
    if (className == null) {
      return("");
    } else {
      return(" class=\"" + rowAltCSS + "\"");
    }
  }

}
