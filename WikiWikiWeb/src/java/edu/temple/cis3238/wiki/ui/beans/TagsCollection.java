/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.ui.beans;

import edu.temple.cis3238.wiki.ui.tags.helpers.*;
import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Christian Dan Doreen
 */
public class TagsCollection implements Serializable {
private static final long serialVersionUID = -7017039780637055790L;
private TagsTagSettings settings;

   

   public TagsTagSettings getSettings() {
	  return settings;
   }

   public void setSettings(TagsTagSettings settings) {
	  this.settings = settings;
   }

   public TagsCollection() {
   }



}
