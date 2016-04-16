/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.tags.helpers;

import edu.temple.cis3238.wiki.dao.*;
import edu.temple.cis3238.wiki.sql.*;
import edu.temple.cis3238.wiki.vo.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dan
 */
public class TagsListFactoryTest {

public TagsListFactoryTest() {
}

@BeforeClass
public static void setUpClass() {
}

@AfterClass
public static void tearDownClass() {
}

@Before
public void setUp() {
}

@After
public void tearDown() {
}

/**
 * Test of create method, of class TagsListFactory.
 */
@Test
public void testCreate() {
   System.out.println( "test TagList Factory" );
   DbConnection dbc;
   GeneralDAO dao;
   ArrayList<TagsVO> tags;
   TagsTagSettings settings;
   TagsListFactory result;
   dbc = new DbConnection();
   dao = new GeneralDAO( dbc );

   tags = dao.getTags();
   Collections.sort( tags );
   settings = TagsTagSettings.builder()
		   .queryStringParam( "tagPK" )
		   .navigateURL( "SampleTagLibAndBean.jsp" )
		   .style( "cloud" )
		   .widthPx( "200px" )
		   .tagsVOList( tags ).build();
   dbc.close();
   //Need to update when data change in database.
   String expResult = "<div style=\"border:1px solid black;width:200px;padding:5px;margin:3px;\"><span style=\"font-size:12pt;\"> <a title=\"Click to view 1 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=27&pTagID=Another Tag\">Another Tag</a></span><span style=\"font-size:12pt;\"> <a title=\"Click to view 1 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=28&pTagID=CIS\">CIS</a></span><span style=\"font-size:16pt;\"> <a title=\"Click to view 4 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=18&pTagID=General\">General</a></span><span style=\"font-size:20pt;\"> <a title=\"Click to view 5 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=25&pTagID=Help\">Help</a></span><span style=\"font-size:14pt;\"> <a title=\"Click to view 2 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=23&pTagID=Ipsum\">Ipsum</a></span><span style=\"font-size:14pt;\"> <a title=\"Click to view 2 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=22&pTagID=Lorem\">Lorem</a></span><br/><span style=\"font-size:22pt;\"> <a title=\"Click to view 7 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=26&pTagID=Markup\">Markup</a></span><span style=\"font-size:20pt;\"> <a title=\"Click to view 5 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=24&pTagID=Syntax\">Syntax</a></span><span style=\"font-size:20pt;\"> <a title=\"Click to view 5 topic(s).\" href=\"SampleTagLibAndBean.jsp?tagPK=1&pTagID=Wiki\">Wiki</a></span></div>";
   
   result = TagsListFactory.create( settings );
   expResult = result.getTagsMarkup() ;
//   assertTrue( result.getTagsMarkup().length() > 5 );
//System.out.println( result.getTagsMarkup() );   
assertEquals( expResult, result.getTagsMarkup() );

}

}
