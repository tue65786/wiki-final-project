/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.ui.servlets;

import edu.temple.cis3238.wiki.vo.*;
import javax.servlet.http.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import static org.easymock.EasyMock.*;

/**
 *
 * @author
 */
public class UploaderServletTest {

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
   HttpServletRequest request = createMock( HttpServletRequest.class );
   HttpServletResponse response = createMock( HttpServletResponse.class );
 //  HttpSession session = createMock( MockServletSession.class );

   RequestDispatcher rdMock = createMock( RequestDispatcher.class );
   response.setContentType( "UTF-8" );

   expect( request.getContentType() ).andReturn( "multipart/form-data; boundary=---------------------------7dd2ad38581480" );
   request.setAttribute( (String) anyObject(), anyObject() );

   expect( request.getRequestDispatcher( "/" + UploaderServlet.REDIRECT_ON_COMPLETE_PAGE ) ).andReturn( rdMock );
   expect( request.getSession() ).andReturn( new MockServletSession() );
   expect( request.getMethod() ).andReturn( "post" );
   expect( request.getInputStream() ).andReturn( new MockServletInputStream( "UploaderServletTestFile.txt" ) );
   expect( request.getCharacterEncoding() ).andReturn( "UTF-8" );
   expect( request.getContentLength() ).andReturn( 1024 );
   replay( request );

   UploaderServlet testServlet = new UploaderServlet();
   testServlet.doPost( request, response );

   assertTrue( testServlet.isSuccess() );
}

private class MockServletInputStream extends javax.servlet.ServletInputStream {

InputStream fis = null;

@Override
public void setReadListener(ReadListener _readListener) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isFinished() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isReady() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

public MockServletInputStream(String fileName) {
   try {
	  fis = new FileInputStream( fileName );
   } catch (Exception genExe) {
	  genExe.printStackTrace();
   }
}

@Override
public int read() throws IOException {
   if ( fis.available() > 0 ) {
	  return fis.read();
   }
   return 0;
}

@Override
public int read(byte[] bytes, int len, int size) throws IOException {
   if ( fis.available() > 0 ) {
	  int length = fis.read( bytes, len, size );
	  return length;
   }
   return -1;
}
}

private class MockServletSession implements HttpSession {

@Override
public Object getAttribute(String _name) {
   return new TopicVOBuilder().setTopicID( 1 ).build();
}

@Override
public Enumeration<String> getAttributeNames() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public long getCreationTime() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public String getId() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public long getLastAccessedTime() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public int getMaxInactiveInterval() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void setMaxInactiveInterval(int _interval) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public ServletContext getServletContext() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public HttpSessionContext getSessionContext() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public Object getValue(String _name) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public String[] getValueNames() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void invalidate() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public boolean isNew() {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void putValue(String _name, Object _value) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void removeAttribute(String _name) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void removeValue(String _name) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}

@Override
public void setAttribute(String _name, Object _value) {
   throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
}
}

}
