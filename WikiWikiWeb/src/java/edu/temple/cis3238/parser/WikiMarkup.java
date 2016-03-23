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
public enum WikiMarkup {
    
    
      FRONT_TAG("{{"), FRONT_TOPIC("[["), BACK_TAG("}}"), BACK_TOPIC("]]");


  private String nameAsString;

  private WikiMarkup(String nameAsString){
    this.nameAsString = nameAsString;
  }

  @Override
  public String toString(){
    return this.nameAsString;
  }
  
}
