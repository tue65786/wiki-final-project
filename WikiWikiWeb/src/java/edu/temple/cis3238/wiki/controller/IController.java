/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.controller;

import javax.servlet.http.*;

public interface IController {

   public void setAction(int action);

   public void setPageContexts(HttpServletRequest request, HttpServletResponse response);
}