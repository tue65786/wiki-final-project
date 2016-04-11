/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.parser;

import edu.temple.cis3238.constants.STRINGS;
import static edu.temple.cis3238.wiki.utils.CollectionsUtilities.setToCSV;
import edu.temple.cis3238.wiki.vo.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Dan
 */
public class TagsFromContentParser {

   private static final Logger LOG = Logger.getLogger(TagsFromContentParser.class.getName());
   private boolean extracted = false;
   private static Pattern regex;

   public static TagsFromContentParser create(TopicVO _topicVO) {
	  TagsFromContentParser wfwm = new TagsFromContentParser(_topicVO);
	  return wfwm.extract();
   }

   public static TagsFromContentParser create(String _topicContent) {
	  TagsFromContentParser wfwm = new TagsFromContentParser(_topicContent);
	  return wfwm.extract();
   }

   public static boolean isStaticField(Field f) {
	  return Modifier.isStatic(f.getModifiers());
   }
   private String tagNameCSV;
   private Set<String> tagNameSet;

   private String topicContent;

   protected TagsFromContentParser() {
	  extracted = false;
   }

   private TagsFromContentParser(TopicVO _topicVO) {
	  if (_topicVO == null) {
		 throw new NullPointerException(
				 "topicVO cannot be null [edu.temple.cis3238.wiki.parser.ExtractFromWikiMarkup.<init>(TopicVO)] ");
	  }

	  this.topicContent = _topicVO.getTopicContent();
	  regex = Pattern.compile(STRINGS.REGEX_CATEGORY_TEXT);
	  tagNameSet = new TreeSet<>();
   }

   private TagsFromContentParser(String topicConent) {
	  if (topicConent == null) {
		 throw new NullPointerException(
				 "topicContent cannot be null [edu.temple.cis3238.wiki.parser.ExtractFromWikiMarkup.<init>(String)] ");
	  }
	  this.topicContent = topicConent;
	  regex = Pattern.compile(STRINGS.REGEX_CATEGORY_TEXT);
	  tagNameSet = new TreeSet<>();
   }

   public TagsFromContentParser extract() {
	  extractTagNamesCSVFromTopicContent(topicContent);
	  extracted = (tagNameCSV != null && !tagNameCSV.isEmpty());
	  return this;
   }

   /**
    * @return the tagNameCSV
    */
   public String getTagNameCSV() {
	  if (!extracted) {
		 throw new NullPointerException("Must run extract before extracting tags. ");
	  }
	  return tagNameCSV;
   }

   /**
    * @param tagNameCSV the tagNameCSV to set
    */
   public void setTagNameCSV(String tagNameCSV) {
	  this.tagNameCSV = tagNameCSV;
   }

   /**
    * @return the tagNameSet
    */
   public Set<String> getTagNameSet() {
	  if (!extracted) {
		 throw new NullPointerException("Must run extract before extracting tags. ");
	  }
	  return tagNameSet;
   }

   /**
    * @param tagNameSet the tagNameSet to set
    */
   public void setTagNameSet(Set<String> tagNameSet) {
	  this.tagNameSet = tagNameSet;
   }

   /**
    *
    * @param content
    * @return
    */
   private ArrayList<TagsVO> _extractTagsFromTopic(String content) {
	  ArrayList<TagsVO> matchArrayList = new ArrayList<TagsVO>();
	  try {
		 Matcher matcher = regex.matcher(content);
		 while (matcher.find()) {
			matchArrayList.add(TagsVO.newInstance(new TagsVO((matchArrayList.size() + 1) * -1,
					matcher.group(2), 0)));
		 }
	  } catch (NullPointerException | PatternSyntaxException | IndexOutOfBoundsException ex) {
	  }

	  return matchArrayList;
   }

   private void extractTagNamesCSVFromTopicContent(String _topicContent) {
	  if (!edu.temple.cis3238.wiki.utils.StringUtils.toS(_topicContent, "").isEmpty()) {
		 ArrayList<TagsVO> tagsVO = _extractTagsFromTopic(_topicContent);
		 try {
			setTagNameSet(edu.temple.cis3238.wiki.utils.CollectionsUtilities.pluckList(tagsVO,
					"tagname"));
			setTagNameCSV(setToCSV(getTagNameSet()));
			extracted = true;
		 } catch (IllegalArgumentException ex) {
			LOG.log(Level.SEVERE, null, ex);
		 } catch (Exception ex) {
			LOG.log(Level.SEVERE, null, ex);
		 }
	  }

   }

}