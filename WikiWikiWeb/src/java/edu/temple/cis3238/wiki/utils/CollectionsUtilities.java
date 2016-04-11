/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.utils;

import edu.temple.cis3238.wiki.parser.*;
import edu.temple.cis3238.wiki.vo.TagsVO;
import java.util.*;

public class CollectionsUtilities {

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
		 IPluck<TagsVO, String> predicate;
		 predicate =  new TagColumnPredicate(vo, column, true);
		 v = predicate.pluck(vo, column);

		 if (v != null) {
			result.add(v);
		 }
	  }
	  return result;
   }
}
