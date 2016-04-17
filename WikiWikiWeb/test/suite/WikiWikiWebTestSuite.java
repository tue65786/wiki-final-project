/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suite;

import edu.temple.cis3238.parser.ParserTest;
import edu.temple.cis3238.security.*;
import edu.temple.cis3238.wiki.dao.GeneralDAOTest;
import edu.temple.cis3238.wiki.parser.TagsFromContentParserTest;
import edu.temple.cis3238.wiki.ui.tags.helpers.*;
import edu.temple.cis3238.wiki.utils.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * Test suite across packages
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
GeneralDAOTest.class,
ParserTest.class,
HashTest.class,
PasswordTest.class,
TagsFromContentParserTest.class,
TopicByTopicIDPredicateTest.class,
TagsListFactoryTest.class,
CollectionsUtilitiesTest.class,
StringUtilsTest.class	
})
public class WikiWikiWebTestSuite {

	/**
	 *
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	/**
	 *
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 *
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
   
}
