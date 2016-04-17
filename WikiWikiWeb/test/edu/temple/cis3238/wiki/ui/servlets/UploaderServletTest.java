/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.servlets;

import edu.temple.cis3238.wiki.ui.beans.*;
import edu.temple.cis3238.wiki.vo.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import static org.easymock.EasyMock.*;

/**
 *
 * @author Christian, Doreen, Dan
 */
public class UploaderServletTest {

	private final String INPUT_TEXT = "-----------------------------FFF\n" +
			 "Content-Disposition: form-data; name=\"topicID\"\n" +
			 " \n" +
			 "1\n" +
			 "Upload\n" +
			 "-----------------------------FFF--";

	public UploaderServletTest() {
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

	@Test
	public void testUpload() throws Exception {
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = createMock(RequestDispatcher.class);
		ServletContext context = createMock(ServletContext.class);
		response.setContentType("text/html;charset=UTF-8");

		expect(request.getContentType()).andReturn("multipart/form-data;UTF-8");
		expect(request.getRequestDispatcher("/" + UploaderServlet.REDIRECT_ON_COMPLETE_PAGE)).andReturn(
				requestDispatcher);
		expect(request.getSession()).andReturn(new MockServletSession()).times(3);
		//Added servlet context.. still throwing exception. 
		expect(request.getServletContext()).andReturn(context);
		expect(request.getMethod()).andReturn("post");
		expect(request.getInputStream()).andReturn(new MockServletInputStream(INPUT_TEXT));
		expect(request.getCharacterEncoding()).andReturn("UTF-8");
		expect(request.getContentLength()).andReturn(256);
		replay(request);

		UploaderServlet testServlet = new UploaderServlet();
		testServlet.processRequest(request, response);

		//assertTrue( testServlet.isSuccess() );
	}

// <editor-fold defaultstate="collapsed" desc="Mocked Input Steam. Click on the + sign on the left to edit the code.">
	private class MockServletInputStream extends javax.servlet.ServletInputStream {

		InputStream inputStream = null;

		@Override
		public void setReadListener(ReadListener _readListener) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public boolean isFinished() {
			return true;// throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public boolean isReady() {
			return true;//throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public int read() throws IOException {
			if (inputStream.available() > 0) {
				return inputStream.read();
			}
			return 0;
		}

		@Override
		public int read(byte[] bytes,
						int len,
						int size) throws IOException {
			if (inputStream.available() > 0) {
				int length = inputStream.read(bytes, len, size);
				return length;
			}
			return -1;
		}

		public MockServletInputStream(String inputFileString) {
			try {
				inputStream = new ByteArrayInputStream(inputFileString.getBytes());///FileInputStream( fileName );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Mocked Session methods. Click on the + sign on the left to edit the code.">
	private class MockServletSession implements HttpSession {

		@Override
		public Object getAttribute(String _name) {
			if (_name.equals("topicCollection")) {
				TopicCollection collection = new TopicCollection();
				collection.setCurrentTopic(new TopicVOBuilder().setTopicID(1).build());
				return collection;
			}

			return new TopicVOBuilder().setTopicID(1).build();
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public long getCreationTime() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public String getId() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public long getLastAccessedTime() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public int getMaxInactiveInterval() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void setMaxInactiveInterval(int _interval) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public ServletContext getServletContext() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public HttpSessionContext getSessionContext() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public Object getValue(String _name) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public String[] getValueNames() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void invalidate() {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public boolean isNew() {
			return false;//throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void putValue(String _name,
							 Object _value) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void removeAttribute(String _name) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void removeValue(String _name) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void setAttribute(String _name,
								 Object _value) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}
// </editor-fold>
}
