/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import edu.temple.cis3238.wiki.parser.*;
import edu.temple.cis3238.wiki.ui.tags.helpers.TopicByTopicIDPredicate;
import edu.temple.cis3238.wiki.vo.TagsVO;
import java.util.*;
/**
 * 
 * VO Collection Helpers
 */
public class CollectionsUtilities {

	/**
	 *
	 * @param set
	 * @return
	 */
	public static String setToCSV(Set<String> set) {
	  StringBuilder sb = new StringBuilder("");
	  Set<String> tagNameSet = new TreeSet<>();
	  for (String t : set) {
		 if (t == null) {
			continue;
		 }
		 String tag = edu.temple.cis3238.wiki.utils.StringUtils.stripInvalidChars((String) t);
		 tagNameSet.add(tag);
		 sb.append(tag);
		 sb.append(",");
	  }
	  return org.apache.commons.lang3.StringUtils.removeEndIgnoreCase(sb.toString(), ",");
   }
/**
 * Creates a Set of value for Tag Column
 * @param voList
 * @param column
 * @return 
 */
   public static  Set<String> pluckList(ArrayList<TagsVO> voList, String column) {
	  Set<String> result = new TreeSet<String>();
	  for (TagsVO element : voList) {
		 TagsVO vo = (TagsVO) element;
		 String v;
		 IPluck<TagsVO, String> transformer;
		 transformer =  new TagColumnPredicate(vo, column, true);
		 v = transformer.pluck(vo, column);

		 if (v != null) {
			result.add(v);
		 }
	  }
	  return result;
   }
   /**
	* Apply Predicate to Collection
	* @param <T> Generic Collection Type.
	* @param col Column to search for
	* @param transformer Class implementing Plucker, has apply with criteria
	* @see TagColumnPredicate
	* @see TopicByTopicIDPredicate
	* @return Sublist of items that meet criteria specified by {@linkplain IPredicate}
	*/
    public static <T> ArrayList<T> filterList( ArrayList<T> col, IPredicate<T> transformer ) {
      ArrayList<T> result = new ArrayList<T>();
      for ( T element : col ) {
         if ( transformer.apply(element) ) {
            result.add(element);
         }
      }
      return result;
   }
}
