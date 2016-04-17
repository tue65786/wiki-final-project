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
import edu.temple.cis3238.constants.STRINGS;
import java.util.Scanner;
import java.util.ArrayList;

public class Parser {

	public static String parseAndAnnotate(String inputWikiText) {
		return parseAndAnnotate(inputWikiText, "wiki.jsp", "id", "tag.jsp", "id");
	}
/**
 * Parses tags and topics from topicContent
 * @param inputWikiText input
 * @param topicURL topic page
 * @param topicParam topic request query parameter
 * @param tagURL tag page
 * @param tagParam tag request parameter 
 * @return Parsed content
 */
	public static String parseAndAnnotate(String inputWikiText,
										  String topicURL,
										  String topicParam,
										  String tagURL,
										  String tagParam) {
		String output = inputWikiText.replaceAll(STRINGS.REGEX_TOPIC_MATCHER,
				"<a style='font-weight:bold' href=\"" +
				 topicURL + "?" + topicParam + "=$2\">$2</a>");
		output = output.replaceAll(STRINGS.REGEX_TAG_MATCHER, "<a href=\"" +
				 tagURL + "?" + tagParam + "=$2\">$2</a>");

		return output;

	}

	/*returns an arrary of arrayLists, the ArrayList at index 0 contains an array list of 
    topics and the ArrayList at index 1 contains an array list of tags
	 */
	public static ArrayList<String>[] parseAndCategorize(String inputWikiText) {

		ArrayList<String>[] topicsNtags = new ArrayList[2];
		topicsNtags[0] = new ArrayList<>();
		topicsNtags[1] = new ArrayList<>();

		Scanner scanner = new Scanner(inputWikiText);

		String s = null;
		int end = 0;

		while (scanner.hasNext()) {

			s = scanner.next();
			if (s.length() > 1) {
				if (s.substring(0, 2).equals(WikiMarkup.FRONT_TOPIC.toString())) {

					end = s.indexOf(WikiMarkup.BACK_TOPIC.toString());
					topicsNtags[0].add(s.substring(2, end));

				} else if (s.substring(0, 2).equals(WikiMarkup.FRONT_TAG.toString())) {

					end = s.indexOf(WikiMarkup.BACK_TAG.toString());
					topicsNtags[1].add(s.substring(2, end));

				} //add more else if for more regexes
				else {
					//do nothing ..this only use for annotate method...but keep for logic's sake
				}
			}
		}

		return topicsNtags;
	}

}
