/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.temple.cis3238.wiki.parser;

/**
 * Interface to retrieve field value from Object
 * @see TagsFromContentParser
 * @see TagColumnPredicate
 * @author dan
 */
public interface IPluck<VO, VI> {
   
VI pluck(VO element, String fieldName);
   

}
