/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis3238.wiki.dao;

import edu.temple.cis3238.wiki.sql.*;
import edu.temple.cis3238.wiki.utils.*;
import edu.temple.cis3238.wiki.vo.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author (c)2016 Doreen, Dan, Christian
 */
public class GeneralDAO implements IGeneralDAO {

private static final Logger LOG = Logger.getLogger( GeneralDAO.class.getName() );
private static boolean DEBUG = true;
private DbConnection dbc;

@Override
public int addTag(TagsVO _vo) {
   CallableStatement cs = null;
   int insertedId = 0;
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TAG_ADD );
	  cs.setString( 1, _vo.getTagName() );
	  cs.setNull( 2, java.sql.Types.INTEGER );

	  //Retrieve generated ID from database
	  cs.registerOutParameter( 3, java.sql.Types.INTEGER );

	  cs.executeUpdate();
	  insertedId = cs.getInt( 3 );
	  if ( !cs.isClosed() ) {
		 cs.close();
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return insertedId;
}

@Override
public int addTopic(TopicVO _vo) {
   CallableStatement cs = null;
   int insertedId = 0;
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_ADD );
	  cs.setString( 1, _vo.getTopicName() );
	  cs.setString( 2, _vo.getTopicContent() );

	  //Retrieve generated ID from database
	  cs.registerOutParameter( 3, java.sql.Types.INTEGER );

	  cs.executeUpdate();
	  insertedId = cs.getInt( 3 );
	  if ( !cs.isClosed() ) {
		 cs.close();
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return insertedId;
}

@Override
public int addUser(UsersVO _vo) {
   CallableStatement cs = null;
   int insertedId = 0;
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USER_INSERT );
	  cs.setString( 1, _vo.getUserName() );
	  cs.setString( 2, _vo.getPassword() );
	  cs.setString( 3, _vo.getUserRole() );
	  cs.setString( 4, _vo.getEmailAddress() );

	  //Retrieve generated ID from database
	  cs.registerOutParameter( 5, java.sql.Types.INTEGER );

	  cs.executeUpdate();
	  insertedId = cs.getInt( 5 );
	  if ( !cs.isClosed() ) {
		 cs.close();
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return insertedId;
}

/**
 * Updates {@linkplain TagsVO} assigned to {@linkplain TopicVO}
 *
 * @param _topicVO Topic to assign or unassign tags
 * @param _tagsVO  list of tags to assign or unassign to topic
 * @param assign   true to assign, false to remove
 * @return true if update count > 0 [Could change to check expected vs actual]
 */
private boolean changeTopicTagsBinding(TopicVO _topicVO, ArrayList<TagsVO> _tagsVO, boolean assign) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   int[] updateCounts = { 0 };

   if ( _topicVO == null || _tagsVO == null || _tagsVO.isEmpty() ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "changeTopicTagsBinding(TopicVO,TagsVO[],boolean)", "Invalid params. Topic and Tags Array must not be null." );
	  return false;
   }
   try {
	  dbc.getConn().setAutoCommit( false );
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_CHANGE_TAG_BINDING );
	  cs.setInt( 1, _topicVO.getTopicID() );
	  cs.setBoolean( 3, assign );
	  for ( TagsVO vo : _tagsVO ) {
		 try {
			cs.setInt( 2, vo.getTagID() );
			cs.addBatch();
		 } catch (SQLException ex) {
			LOG.log( Level.SEVERE, "Batch Item", ex );
		 } catch (Exception ex) {
			LOG.log( Level.SEVERE, "Batch Item", ex );
		 }
	  }

	  updateCounts = cs.executeBatch();
	  dbc.getConn().commit();
	  dbc.getConn().setAutoCommit( true );

	  if ( !cs.isClosed() ) {
		 cs.close();
	  }

	  for ( int i : updateCounts ) {
		 rowsAffected += i;
	  }

   } catch (BatchUpdateException ex) {
	  System.err.println( DbUtils.printBatchUpdateException( ex ) );
	  LOG.log( Level.SEVERE, null, ex );

   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }

   return rowsAffected > 0;
}

@Override
public boolean assignTopicTags(TopicVO _topicVO, ArrayList<TagsVO> _tagsVOList) {
   return changeTopicTagsBinding( _topicVO, _tagsVOList, true );
}

@Override
public boolean deleteTag(TagsVO _vo) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   if ( _vo == null || _vo.getTagID() <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "deleteTag(TagsVO)", "Invalid params. TagsVO not be null and tagID must be a positive integer." );
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TAG_DELETE );
	  cs.setInt( 1, _vo.getTagID() );
	  rowsAffected = cs.executeUpdate();
	  if ( DEBUG ) {
		 System.out.println( "Tags deleted = " + rowsAffected );
	  }
	  try {
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (NullPointerException ex) {
	  LOG.log( Level.SEVERE, null, ex );
	  ex.printStackTrace();
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
	  ex.printStackTrace();
   }
   return rowsAffected > 0;
}

public boolean deleteTopic(TopicVO _vo) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   if ( _vo == null || _vo.getTopicID() <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "deleteTopic(int)", "Invalid params. TopicVO must not be null and TopicID must be a positive integer." );
	  return false;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_DELETE );
	  cs.setInt( 1, _vo.getTopicID() );
	  rowsAffected = cs.executeUpdate();
	  if ( DEBUG ) {
		 System.out.println( "Topics deleted = " + rowsAffected );
	  }
	  try {

		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return rowsAffected > 0;
}

public boolean deleteUser(UsersVO _vo) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   if ( _vo == null || _vo.getUserID() <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "deleteUser(int)", "Invalid params. UsersVO can not be null and UserID must be a positive integer." );
	  return false;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USER_DELETE );
	  cs.setInt( 1, _vo.getUserID() );
	  rowsAffected = cs.executeUpdate();
	  if ( DEBUG ) {
		 System.out.println( "Users deleted = " + rowsAffected );
	  }
	  try {

		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return rowsAffected > 0;
}

@Override
public UsersVO findUserByName(String _username) {
   CallableStatement cs = null;
   UsersVO vo;
   ResultSet rs = null;
   if ( StringUtils.toS( _username ).isEmpty() ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "findUserByUserName(String)", "Invalid params" );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USERS_SELECT_BY_USERNAME );
	  cs.setString( 1, _username );

	  rs = cs.executeQuery();

	  //Retrieve ResultSet
	  if ( rs.next() ) {
		 vo = new UsersVOBuilder()
				 .setUserID( rs.getInt( 1 ) )
				 .setUserName( rs.getString( 2 ) )
				 .setPassword( rs.getString( 3 ) )
				 .setUserRole( rs.getString( 4 ) )
				 .setEmailAddress( rs.getString( 5 ) )
				 .build();
		 try {
			if ( !rs.isClosed() ) {
			   rs.close();
			}
			if ( !cs.isClosed() ) {
			   cs.close();
			}
		 } catch (SQLException ex) {
			LOG.log( Level.WARNING, "Problem closing Db objects", ex );
		 }
		 return UsersVO.newInstance( vo );
	  } else {
		 LOG.logp( Level.INFO, this.getClass().getName(), "findUserByUserNameAndPassword(String, String)", "User not found" );
	  }

   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return null;
}

@Override
public UsersVO findUserByUserNameAndPassword(String _username, String _password) {
   CallableStatement cs = null;
   UsersVO vo;
   ResultSet rs = null;
   _username = StringUtils.toS( _username );
   _password = StringUtils.toS( _password );

   //Has input
   if ( _username.length() * _password.length() == 0 ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "findUserByUserNameAndPassword(String, String)", "Invalid params. Username and Password can not be null" );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USER_SELECT_BY_USERNAME_PASSWORD );
	  cs.setString( 1, _username );
	  cs.setString( 2, _password );
	  rs = cs.executeQuery();

	  //Retrieve ResultSet
	  if ( rs.next() ) {
		 vo = new UsersVOBuilder()
				 .setUserID( rs.getInt( 1 ) )
				 .setUserName( rs.getString( 2 ) )
				 .setPassword( rs.getString( 3 ) )
				 .setUserRole( rs.getString( 4 ) )
				 .setEmailAddress( rs.getString( 5 ) )
				 .build();
		 try {
			if ( !rs.isClosed() ) {
			   rs.close();
			}
			if ( !cs.isClosed() ) {
			   cs.close();
			}
		 } catch (SQLException ex) {
			LOG.log( Level.WARNING, "Problem closing Db objects", ex );
		 }
		 return UsersVO.newInstance( vo );
	  } else {
		 LOG.logp( Level.INFO, this.getClass().getName(), "findUserByUserNameAndPassword(String, String)", "User not found" );
	  }

   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return null;
}

/**
 * Coalesce method for select single TagsVO
 *
 * @param _id
 * @param _name
 * @return
 */
private TagsVO getTag(int _id, String _name) {

   CallableStatement cs = null;
   ResultSet rs = null;
   TagsVO vo = null;
   if ( StringUtils.toS( _name ).isEmpty() && _id <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "getTag(int, String)", "Invalid params. Either TagID or TagName must be set." );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TAG_SELECT );
	  if ( _id > 0 ) {
		 cs.setInt( 1, _id );
	  } else {
		 cs.setNull( 1, java.sql.Types.INTEGER );
	  }

	  if ( _name != null && !_name.isEmpty() ) {
		 cs.setString( 2, _name );
	  } else {
		 cs.setNull( 2, java.sql.Types.NVARCHAR );
	  }
	  rs = cs.executeQuery();
	  if ( rs.next() ) {
		 vo = TagsVO.newInstance( new TagsVO( rs.getInt( 1 ), rs.getString( 2 ), rs.getInt( 3 ),rs.getInt( 4) ) );
		 try {
			if ( !rs.isClosed() ) {
			   rs.close();
			}
			if ( !cs.isClosed() ) {
			   cs.close();
			}
		 } catch (SQLException ex) {
			LOG.log( Level.WARNING, "Problem closing Db objects", ex );
		 }
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return vo;
}

@Override
public TagsVO getTagByID(int _id) {
   return getTag( _id, null );
}

@Override
public TagsVO getTagByName(String _name) {
   return getTag( 0, _name );
}

@Override
public ArrayList<TagsVO> getTags() {
   ArrayList<TagsVO> voList = new ArrayList<TagsVO>();
   CallableStatement cs = null;
   ResultSet rs = null;
   TagsVO vo = null;

   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TAG_SELECT );
	  cs.setNull( 1, java.sql.Types.INTEGER );
	  cs.setNull( 2, java.sql.Types.NVARCHAR );
	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 vo = new TagsVO( rs.getInt( 1 ), rs.getString( 2 ), rs.getInt( 3 ),rs.getInt( 4) );
		 voList.add( TagsVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}

@Override
public ArrayList<TagsVO> getTagsByTopicID(int _topicid) {
   ArrayList<TagsVO> voList = new ArrayList<TagsVO>();

   CallableStatement cs = null;
   ResultSet rs = null;
   TagsVO vo = null;
   if ( _topicid <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "getTagsByTopicID(int)", "Invalid params. TopicID must be a positive int." );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TAG_SELECT_BY_TOPIC_ID );
	  cs.setInt( 1, _topicid );
	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 vo = new TagsVO( rs.getInt( 1 ), rs.getString( 2 ), rs.getInt( 3 ),rs.getInt( 4) );
		 voList.add( TagsVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}
private ArrayList<TopicVO> getTopic(int _id, String _name){
   return getTopic( _id, _name, true );
}
/**
 * Retrieves Topics
 *
 * @param _id   primary key
 * @param _name case insensitive name
 * @return Single or list of {@linkplain TopicVO TopicVOs}
 */
private ArrayList<TopicVO> getTopic(int _id, String _name,boolean loadCollections) {
   CallableStatement cs = null;
   ResultSet rs = null;
   TopicVO vo = null;
   ArrayList<TopicVO> voList = new ArrayList<TopicVO>();
   ArrayList<TagsVO> topicTagsVOList = null;

   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_SELECT );
	  if ( _id > 0 ) {
		 cs.setInt( 1, _id );
	  } else {
		 cs.setNull( 1, java.sql.Types.INTEGER );
	  }

	  if ( _name != null && !_name.isEmpty() ) {
		 cs.setString( 2, _name );
	  } else {
		 cs.setNull( 2, java.sql.Types.NVARCHAR );
	  }
	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 TopicVOBuilder builder = new TopicVOBuilder();
		 builder.setTopicID( rs.getInt( 1 ) )
				 .setTopicName( rs.getString( 2 ) )
				 .setTopicContent( rs.getString( 3 ) )
				 .setTopicCreated( rs.getString( 4 ) )
				 .setTopicModified( rs.getString( 5 ) )
				 .setRevisions( rs.getInt( 6 ) );
		 vo = builder.build();
		if (loadCollections && vo.getTopicID()>0){
		   try {
			topicTagsVOList = getTagsByTopicID( vo.getTopicID() );
			if (topicTagsVOList != null){
			   vo.setTagsCollection( topicTagsVOList );
			}
		 } catch (Exception e) {
			   LOG.log(Level.WARNING,"Tag Get Error",e);
		 }
		}
		 voList.add( TopicVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}

@Override
public TopicVO getTopicByID(int _id) {
   if ( _id <= 0 ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "getTopic(int)", "Invalid param. Specified _id is not a valid TopicID" );
	  return null;
   }
   ArrayList<TopicVO> voList = getTopic( _id, null );
   if ( voList != null && !voList.isEmpty() ) {
	  return voList.get( 0 );
   } else {
	  return null;
   }

}

public UsersVO getUserByID(int _id) {
   CallableStatement cs = null;
   ResultSet rs = null;
   UsersVO vo = null;
   if ( _id <= 0 ) {
	  return null;
   }

   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USER_SELECT_BY_ID );
	  cs.setInt( 1, _id );
	  rs = cs.executeQuery();
	  if ( rs.next() ) {
		 UsersVOBuilder builder = new UsersVOBuilder()
				 .setUserID( rs.getInt( 1 ) )
				 .setUserName( rs.getString( 2 ) )
				 .setPassword( rs.getString( 3 ) )
				 .setUserRole( rs.getString( 4 ) )
				 .setEmailAddress( rs.getString( 5 ) );
		 vo = builder.build();
		 try {
			if ( !rs.isClosed() ) {
			   rs.close();
			}
			if ( !cs.isClosed() ) {
			   cs.close();
			}
		 } catch (SQLException ex) {
			LOG.log( Level.WARNING, "Problem closing Db objects", ex );
		 }
		 return vo;
	  }

   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return null;

}

@Override
public TopicVO getTopicByName(String _name) {
   ArrayList<TopicVO> voList = getTopic( 0, _name );
   if ( StringUtils.toS( _name ).isEmpty() ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "getTopic(String)", "Invalid param. Invalid _name, TopicName can not be null" );
	  return null;
   }
   if ( voList != null && !voList.isEmpty() ) {
	  return voList.get( 0 );
   } else {
	  return null;
   }
}

@Override
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicID(int _topicId) {
   CallableStatement cs = null;
   ResultSet rs = null;
   TopicHistoryVO vo = null;
   ArrayList<TopicHistoryVO> voList = new ArrayList<TopicHistoryVO>();
   if ( _topicId <= 0 ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "getTopicHistoryByTopicID(int)", "Invalid param. Specified _id is not a valid TopicID" );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPICHISTORY_GET_BY_TOPIC_ID );

	  cs.setInt( 1, _topicId );

	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 vo = new TopicHistoryVO( rs.getInt( 1 ), rs.getString( 3 ), rs.getString( 4 ), rs.getInt( 2 ) );
		 voList.add( TopicHistoryVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}

@Override
public ArrayList<TopicHistoryVO> getTopicHistoryByTopicName(String _topicName) {
   CallableStatement cs = null;
   ResultSet rs = null;
   TopicHistoryVO vo = null;
   ArrayList<TopicHistoryVO> voList = new ArrayList<TopicHistoryVO>();
   if ( StringUtils.toS( _topicName ).isEmpty() ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "getTopicHistoryByTopicName(String)", "Invalid param. TopicName can not be null." );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPICHISTORY_GET_BY_TOPIC_NAME );

	  cs.setString( 1, _topicName );

	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 vo = new TopicHistoryVO( rs.getInt( 1 ), rs.getString( 3 ), rs.getString( 4 ), rs.getInt( 2 ) );
		 voList.add( TopicHistoryVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}
@Override
public ArrayList<TopicVO> getTopics(boolean populateTopicTags){
  ArrayList<TopicVO> voList = getTopic( 0, null,populateTopicTags );
   if ( voList != null && !voList.isEmpty() ) {
	  return voList;
   } else {
	  LOG.logp( Level.INFO, this.getClass().getName(), "getTopics()", "No topics found." );
	  return null;
   }  
}
@Override
public ArrayList<TopicVO> getTopics() {
   return getTopics(true);
}

@Override
public ArrayList<TopicVO> getTopicsByTagID(int _id) {
   CallableStatement cs = null;
   ResultSet rs = null;
   TopicVO vo = null;
   ArrayList<TagsVO> topicTagsVOList;
   ArrayList<TopicVO> voList = new ArrayList<TopicVO>();
   if ( _id <= 0 ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "getTopicsByTagID(int)", "Invalid params. TagID must be positive int." );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_SELECT_BY_TAG );
	  cs.setInt( 1, _id );
	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 TopicVOBuilder builder = new TopicVOBuilder();
		 builder.setTopicID( rs.getInt( 1 ) )
				 .setTopicName( rs.getString( 2 ) )
				 .setTopicContent( rs.getString( 3 ) )
				 .setTopicCreated( rs.getString( 4 ) )
				 .setTopicModified( rs.getString( 5 ) )
				 .setRevisions( rs.getInt( 6 ) );
		 vo = builder.build();
		  try {
			topicTagsVOList = getTagsByTopicID( vo.getTopicID() );
			if (topicTagsVOList != null){
			   vo.setTagsCollection( topicTagsVOList );
			}
		 } catch (Exception e) {
			   LOG.log(Level.WARNING,"Tag Get Error",e);
		 }
		 voList.add( TopicVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}

@Override
public ArrayList<TopicVO> getTopicsByTagName(String _name) {
   CallableStatement cs = null;
   ResultSet rs = null;
   TopicVO vo = null;
   ArrayList<TagsVO> topicTagsVOList;
   ArrayList<TopicVO> voList = new ArrayList<TopicVO>();
   TagsVO tag = getTagByName( _name );
   if ( tag == null || tag.getTagID() <= 0 ) {
	  LOG.logp( Level.INFO, this.getClass().getName(), "getTopicsByTagName(String)", "Invalid tag name" );
	  return voList;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_SELECT_BY_TAG );
	  cs.setInt( 1, tag.getTagID() );
	  rs = cs.executeQuery();
	  while ( rs.next() ) {
		 TopicVOBuilder builder = new TopicVOBuilder();
		 builder.setTopicID( rs.getInt( 1 ) )
				 .setTopicName( rs.getString( 2 ) )
				 .setTopicContent( rs.getString( 3 ) )
				 .setTopicCreated( rs.getString( 4 ) )
				 .setTopicModified( rs.getString( 5 ) )
				 .setRevisions( rs.getInt( 6 ) );
		 vo = builder.build();
		 try {
			topicTagsVOList = getTagsByTopicID( vo.getTopicID() );
			if (topicTagsVOList != null){
			   vo.setTagsCollection( topicTagsVOList );
			}
		 } catch (Exception e) {
			   LOG.log(Level.WARNING,"Tag Get Error",e);
		 }
		 voList.add( TopicVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;

}

@Override
public ArrayList<UsersVO> getUsers() {
   CallableStatement cs = null;
   UsersVO vo;
   ResultSet rs = null;
   ArrayList<UsersVO> voList = new ArrayList<>();
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USERS_SELECT_BY_USERNAME );
	  cs.setNull( 1, java.sql.Types.NVARCHAR );

	  rs = cs.executeQuery();

	  //Retrieve ResultSet
	  while ( rs.next() ) {
		 vo = new UsersVOBuilder()
				 .setUserID( rs.getInt( 1 ) )
				 .setUserName( rs.getString( 2 ) )
				 .setPassword( rs.getString( 3 ) )
				 .setUserRole( rs.getString( 4 ) )
				 .setEmailAddress( rs.getString( 5 ) )
				 .build();
		 voList.add( UsersVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
	  return voList;
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return null;
}

@Override
public boolean revertTopicFromHistory(TopicHistoryVO _vo) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   if ( _vo == null || _vo.getTopicID() <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "revertTopicFromHistory(TopicHistoryVO)", "Invalid TopicHistory Object" );
	  return false;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPICHISTORY_REVERT_TOPIC_BY_HISTORY_ID );
	  cs.setInt( 1, _vo.getTopicID() );

	  //Execute update and retieve rows updated
	  rowsAffected = cs.executeUpdate();
	  if ( DEBUG ) {
		 System.out.println( "Topics Reverted = " + rowsAffected );
	  }

	  if ( !cs.isClosed() ) {
		 cs.close();
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return rowsAffected > 0;
}

@Override
public ArrayList<TopicVO> searchTopic(String _query){
   return searchTopic(_query,true);
}

@Override
public ArrayList<TopicVO> searchTopic(String _query, boolean loadCollections) {
   CallableStatement cs = null;
   ResultSet rs = null;
   TopicVO vo = null;
   ArrayList<TagsVO> topicTagsVOList = null;
   ArrayList<TopicVO> voList = new ArrayList<TopicVO>();
   if ( StringUtils.toS( _query ).isEmpty() ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "searchTopic(String)", "Invalid params. Query can not be null." );
	  return null;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_SEARCH_BY_KEYWORD );
	  cs.setString( 1, _query );
	  rs = cs.executeQuery();

	  while ( rs.next() ) {
		 TopicVOBuilder builder = new TopicVOBuilder();
		 builder.setTopicID( rs.getInt( 1 ) )
				 .setTopicName( rs.getString( 2 ) )
				 .setTopicContent( rs.getString( 3 ) )
				 .setTopicCreated( rs.getString( 4 ) )
				 .setTopicModified( rs.getString( 5 ) )
				 .setRevisions( rs.getInt( 6 ) );
		 vo = builder.build();
		 if (loadCollections && vo.getTopicID()>0){
		   try {
			topicTagsVOList = getTagsByTopicID( vo.getTopicID() );
			if (topicTagsVOList != null){
			   vo.setTagsCollection( topicTagsVOList );
			}
		 } catch (Exception e) {
			   LOG.log(Level.WARNING,"Tag Get Error",e);
		 }
		}
		 voList.add( TopicVO.newInstance( vo ) );
	  }
	  try {
		 if ( !rs.isClosed() ) {
			rs.close();
		 }
		 if ( !cs.isClosed() ) {
			cs.close();
		 }
	  } catch (SQLException ex) {
		 LOG.log( Level.WARNING, "Problem closing Db objects", ex );
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return voList;
}

@Override
public boolean unassignTopicTags(TopicVO _topicVO, ArrayList<TagsVO> _tagsVOList) {
   return changeTopicTagsBinding( _topicVO, _tagsVOList, false );
}

@Override
public boolean updateTopic(TopicVO _vo) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   if ( _vo == null || _vo.getTopicID() <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "updateTopic(TopicVO)", "Invalid param. TopicVO id null or TopicID is not valid." );
	  return false;

   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.TOPIC_UPDATE );
	  cs.setInt( 1, _vo.getTopicID() );
	  if ( _vo.getTopicName().isEmpty() ) {
		 cs.setNull( 2, java.sql.Types.NVARCHAR );
	  } else {
		 cs.setString( 2, _vo.getTopicName() );
	  }

	  cs.setString( 3, _vo.getTopicContent() );

	  //Execute update and retieve rows updated
	  rowsAffected = cs.executeUpdate();

	  if ( DEBUG ) {
		 System.out.println( "Topics updated = " + rowsAffected );
	  }

	  if ( !cs.isClosed() ) {
		 cs.close();
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return rowsAffected > 0;
}

@Override
public boolean updateUser(UsersVO _vo) {
   CallableStatement cs = null;
   int rowsAffected = 0;
   if ( _vo == null || _vo.getUserID() <= 0 ) {
	  LOG.logp( Level.WARNING, this.getClass().getName(), "updateUser(UsersVO)", "Invalid param. UserVO is null or UserID is not valid." );
	  return false;
   }
   try {
	  cs = dbc.getConn().prepareCall( DB_STRINGS.USER_UPDATE );
	  cs.setInt( 1, _vo.getUserID() );
	  if ( _vo.getUserName().isEmpty() ) {
		 cs.setNull( 2, java.sql.Types.NVARCHAR );
	  } else {
		 cs.setString( 2, _vo.getUserName() );
	  }

	  if ( _vo.getPassword().isEmpty() ) {
		 cs.setNull( 3, java.sql.Types.NVARCHAR );
	  } else {
		 cs.setString( 3, _vo.getPassword() );
	  }

	  if ( _vo.getUserRole().isEmpty() ) {
		 cs.setNull( 4, java.sql.Types.NVARCHAR );
	  } else {
		 cs.setString( 4, _vo.getUserRole() );
	  }

	  if ( _vo.getEmailAddress().isEmpty() ) {
		 cs.setNull( 5, java.sql.Types.NVARCHAR );
	  } else {
		 cs.setString( 5, _vo.getEmailAddress() );
	  }

	  //Execute update and retieve rows updated
	  rowsAffected = cs.executeUpdate();
	  if ( DEBUG ) {
		 System.out.println( "Users updated = " + rowsAffected );
	  }

	  if ( !cs.isClosed() ) {
		 cs.close();
	  }
   } catch (SQLException ex) {
	  LOG.log( Level.SEVERE, null, ex );
   } catch (Exception ex) {
	  LOG.log( Level.SEVERE, null, ex );
   }
   return rowsAffected > 0;
}

public GeneralDAO(DbConnection _dbc) {
   super();
   if ( _dbc == null ) {
	  throw new NullPointerException( "DbConnection can not be null" );
   }
   this.dbc = _dbc;
}

}
