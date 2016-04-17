/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.beans;

import edu.temple.cis3238.wiki.ui.tags.TagsList;
import edu.temple.cis3238.wiki.ui.tags.helpers.*;
import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 * In-memory {@linkplain HttpSession} JavaBean for Tags 
 * @author Christian Dan Doreen
 * @see TagsTagSettings
 * @see TagsVO
 * @see TagsList
 */
public class TagsCollection implements Serializable {
	private static final Logger LOG = Logger.getLogger(TagsCollection.class.getName());
	private static final long serialVersionUID = -7017039780637055790L;
	
	private TagsTagSettings settings;

	public TagsCollection() {
	}

	public TagsTagSettings getSettings() {
		return settings;
	}
/**
 * 
 * @param settings List and markup preferences
 */
	public void setSettings(TagsTagSettings settings) {
		this.settings = settings;
	}

}
