/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.parser;

/**
 *
 * @author (c)2016 C D D
 */
public interface IPredicate<T> {
   boolean apply(T type);
}
