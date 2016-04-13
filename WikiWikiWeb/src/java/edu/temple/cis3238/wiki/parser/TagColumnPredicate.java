/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.parser;

import static edu.temple.cis3238.wiki.parser.TagsFromContentParser.isStaticField; 
import edu.temple.cis3238.wiki.vo.TagsVO;
import java.lang.reflect.Field;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Guiding Technologies
 */
public class TagColumnPredicate implements //IPredicate<TagsVO>, 
										   IPluck<TagsVO, String> {

   String column;
   boolean excludeStaticFields;
   TagsVO vo;
   private String csv;

   public TagColumnPredicate(TagsVO _vo, String _column, boolean _includeStaticFields) {
	  vo = _vo;
	  this.column = _column;
	  this.excludeStaticFields = _includeStaticFields;
   }

   //@Override
   public boolean apply(TagsVO _type) {
	  for (Field f : vo.getClass().getDeclaredFields()) {
		 if (((excludeStaticFields && !isStaticField(f)) 
				 || !excludeStaticFields)
				 && f.getName().toLowerCase().equalsIgnoreCase(
						 column)) {
			return true;
		 }
	  }
	  return false;
   }
   private static final Logger LOG = Logger.getLogger(TagColumnPredicate.class.getName());

   @Override
   public String pluck(TagsVO _o, String _fieldName) {
	  for (Field f : vo.getClass().getDeclaredFields()) {
		 if (((excludeStaticFields && !isStaticField(f))
				 || !excludeStaticFields)
				 && f.getName().toLowerCase().equalsIgnoreCase(
						 column)) {
			try {
			   return f.get(vo).toString();
			} catch (IllegalArgumentException ex) {
			   LOG.log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
			   LOG.log(Level.SEVERE, null, ex);
			}
		 }
	  }
	  return null;
   }
}
