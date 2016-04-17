/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.constants;

public class STRINGS {

   /**
    *
    * (\{\{)(.+?)(}})
    *
    * Match the regular expression below and capture its match into backreference number 1 «(\{\{)»
    * Match the character “{” literally «\{» Match the character “{” literally «\{» Match the
    * regular expression below and capture its match into backreference number 2 «(.+?)» Match any
    * single character that is not a line break character «.+?» Between one and unlimited times, as
    * few times as possible, expanding as needed (lazy) «+?» Match the regular expression below and
    * capture its match into backreference number 3 «(}})» Match the characters “}}” literally «}}»
    *
    */
   public static final String REGEX_CATEGORY_TEXT = "(\\{\\{)(.+?)(\\}\\})";
   /**
    ** <sty.*<.*>|<scr.* /script>|</?[a-z][a-z0-9]*[^<>]*>|<!--.*?-->
    *
    * Options: dot matches newline; case insensitive
    *
    * Match either the regular expression below (attempting the next alternative only if this one
    * fails) «<sty.*<.*>» Match the characters “<sty” literally «<sty» Match any single character
    * «.*» Between zero and unlimited times, as many times as possible, giving back as needed
    * (greedy) «*» Match the character “<” literally «<»
    *    Match any single character «.*»
    *       Between zero and unlimited times, as many times as possible, giving back as needed (greedy) «*»
    *    Match the character “>” literally «>» Or match regular expression number 2 below (attempting
    * the next alternative only if this one fails) «<scr.* /script>» Match the characters “<scr”
    * literally «<scr»
    *    Match any single character «.*»
    *       Between zero and unlimited times, as many times as possible, giving back as needed (greedy) «*»
    *    Match the characters “/script>” literally «/script>» Or match regular expression number 3
    * below (attempting the next alternative only if this one fails) «</?[a-z][a-z0-9]*[^<>]*>»
    * Match the character “<” literally «<» Match the character “/” literally «/?» Between zero and
    * one times, as many times as possible, giving back as needed (greedy) «?» Match a single
    * character in the range between “a” and “z” «[a-z]» Match a single character present in the
    * list below «[a-z0-9]*» Between zero and unlimited times, as many times as possible, giving
    * back as needed (greedy) «*» A character in the range between “a” and “z” «a-z» A character in
    * the range between “0” and “9” «0-9» Match a single character NOT present in the list “<>”
    * «[^<>]*» Between zero and unlimited times, as many times as possible, giving back as needed
    * (greedy) «*» Match the character “>” literally «>» Or match regular expression number 4 below
    * (the entire match attempt fails if this one fails to match) «
    * <!--.*?-->» Match the characters “<!--” literally «<!--»
    *    Match any single character «.*?»
    *       Between zero and unlimited times, as few times as possible, expanding as needed (lazy) «*?»
    *    Match the characters “-->” literally «-->»
    */
   public static final String REGEX_HTML_MARKUP_CHARS = "<sty.*<.*>|<scr.*/script>|</?[a-z][a-z0-9]*[^<>]*>|<!--.*?-->";
 
   private static final String CamelCaseRegEx = "(?<!\\[\\[\\[)[A-Z|\\?][A-Za-z0-9]*[a-z][A-Za-z0-9]*[A-Z][A-Za-z0-9]*(?!\\]\\]\\])(?=\\b)(?![\\\\]|[/]|[\\.]\\w)";
   public static final String REGEX_TAG_MATCHER = "(\\{\\{)(.?[^}]*)(\\}\\})";
   
   public static final String REGEX_TOPIC_MATCHER = "(\\[\\[)(.?[^\\]]*)(\\]\\])";
   
}
