/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.parser;

/**
 *
 * @author CAP
 */
import java.util.Scanner;
import java.lang.StringBuilder;

public class Parser{
    
  

  public static String parseAndAnnotate(String inputWikiText){

      //if we encounter [[,]],{{,}} then we need to remove and put html tags there
      
    
      
      Scanner scanner = new Scanner(inputWikiText);
      //scanner.useDelimiter(" "); <<<<THIS THING OMG
      
      StringBuilder result = new StringBuilder();
      
      String s = null;
      int end = 0;
      
      while(scanner.hasNext()){
          
          s = scanner.next();

          if (s.substring(0, 2).equals(WikiMarkup.FRONT_TOPIC.toString())){
              //we konw we are in a topic expression 
              end = s.indexOf(WikiMarkup.BACK_TOPIC.toString());
              result.append("<a style=\"font-weight:bold\" href=\"wiki.jsp?id=");
              result.append(s.substring(2, end));
              result.append("\">");
              result.append(s.substring(2, end));
              result.append("</a> ");

          }
          else if (s.substring(0, 2).equals(WikiMarkup.FRONT_TAG.toString())){
              //we konw we are in a topic expression
              end = s.indexOf(WikiMarkup.BACK_TAG.toString());
              result.append("<a href=\"wiki.jsp?id=");
              result.append(s.substring(2, end));
              result.append("\">");
              result.append(s.substring(2, end));
              result.append("</a> ");

          }
           //add more else if for more regexes
          else
            result.append(s + " ");
          
          
      }
//                  
//                  
//      beginning = inputWikiText.indexOf(WikiMarkup.FRONT_TOPIC.toString());
//      end = inputWikiText.indexOf(WikiMarkup.BACK_TOPIC.toString());
//      
//      String whatWeCareAbout= inputWikiText.substring(beginning+ 2, end);
      
//
//      inputWikiText = inputWikiText.replace(WikiMarkup.FRONT_TOPIC.toString(), "<a href=\"whereeveritslocated\">");
//      inputWikiText = inputWikiText.replace(WikiMarkup.FRONT_TAG.toString(), "<a href=\"whereeveritslocated\">");
//      inputWikiText = inputWikiText.replace(WikiMarkup.BACK_TAG.toString(), "</a>");
//      inputWikiText = inputWikiText.replace(WikiMarkup.BACK_TOPIC.toString(), "</a>");
      
      return result.toString();
  }
}