USE [cis3238FinalProject]
GO
/****** Object:  User [CIS3238]    Script Date: 03/18/2016 17:20:49 ******/
CREATE USER [CIS3238] FOR LOGIN [CIS3238] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  FullTextCatalog [TopicFTC]    Script Date: 03/18/2016 17:20:49 ******/
CREATE FULLTEXT CATALOG [TopicFTC]WITH ACCENT_SENSITIVITY = ON
AS DEFAULT
AUTHORIZATION [dbo]
GO
/****** Object:  Table [dbo].[TopicTags]    Script Date: 03/18/2016 17:20:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TopicTags](
	[TopicID] [int] NOT NULL,
	[TagID] [int] NOT NULL,
 CONSTRAINT [PK_TopicTags] PRIMARY KEY CLUSTERED 
(
	[TopicID] ASC,
	[TagID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[TopicTags] ([TopicID], [TagID]) VALUES (1, 1)
/****** Object:  StoredProcedure [dbo].[spTopicChangeTagBinding]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spTopicChangeTagBinding]
@TopicID int,
@TagID int,
@mustAssign bit

AS

IF (@mustAssign = 1)
BEGIN
  IF NOT EXISTS(SELECT 1 FROM [cis3238FinalProject].dbo.TopicTags WHERE TopicID = @TopicID AND TagID = @TagID)
  BEGIN
    INSERT INTO [cis3238FinalProject].dbo.TopicTags(TopicID, TagID) VALUES(@TopicID, @TagID)
  END
END
ELSE
BEGIN
  DELETE FROM [cis3238FinalProject].dbo.TopicTags WHERE TopicID = @TopicID AND @TagID = @TagID
END
GO
/****** Object:  Table [dbo].[TopicHistory]    Script Date: 03/18/2016 17:20:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TopicHistory](
	[TopicHistoryID] [int] IDENTITY(1,1) NOT NULL,
	[TopicID] [int] NOT NULL,
	[TopicConent] [nvarchar](max) NULL,
	[TopicHistoryCreated] [datetime] NULL,
 CONSTRAINT [PK_TopicHistory] PRIMARY KEY CLUSTERED 
(
	[TopicHistoryID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
CREATE FULLTEXT INDEX ON [dbo].[TopicHistory](
[TopicConent] LANGUAGE [English])
KEY INDEX [PK_TopicHistory]ON ([TopicFTC], FILEGROUP [PRIMARY])
WITH (CHANGE_TRACKING = AUTO, STOPLIST = SYSTEM)
GO
SET IDENTITY_INSERT [dbo].[TopicHistory] ON
INSERT [dbo].[TopicHistory] ([TopicHistoryID], [TopicID], [TopicConent], [TopicHistoryCreated]) VALUES (1, 1, N'Hello world! Welcome to WikWikiWeb', CAST(0x0000A5CD011CCF6F AS DateTime))
SET IDENTITY_INSERT [dbo].[TopicHistory] OFF
/****** Object:  StoredProcedure [dbo].[spTopicHistorySelectByTopic]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicHistorySelectByTopic] 
    @TopicID int
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [TopicHistoryID], [TopicID], [TopicConent], [TopicHistoryCreated] 
	FROM   [dbo].[TopicHistory] 
	WHERE  ([TopicID] = @TopicID )

	COMMIT
GO
/****** Object:  Table [dbo].[Topic]    Script Date: 03/18/2016 17:20:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Topic](
	[TopicID] [int] IDENTITY(1,1) NOT NULL,
	[TopicName] [nvarchar](1024) NOT NULL,
	[TopicContent] [nvarchar](max) NULL,
	[TopicCreated] [datetime] NULL,
	[TopicModified] [datetime] NULL,
	[Revisions] [int] NULL,
 CONSTRAINT [PK_Topic] PRIMARY KEY CLUSTERED 
(
	[TopicID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
CREATE FULLTEXT INDEX ON [dbo].[Topic](
[TopicContent] LANGUAGE [English])
KEY INDEX [PK_Topic]ON ([TopicFTC], FILEGROUP [PRIMARY])
WITH (CHANGE_TRACKING = AUTO, STOPLIST = SYSTEM)
GO
SET IDENTITY_INSERT [dbo].[Topic] ON
INSERT [dbo].[Topic] ([TopicID], [TopicName], [TopicContent], [TopicCreated], [TopicModified], [Revisions]) VALUES (1, N'HelloWorld', N'Hello world! Welcome to WikWikiWeb', CAST(0x0000A5CD00000000 AS DateTime), CAST(0x0000A5CD00000000 AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Topic] OFF
/****** Object:  Trigger [TopicHistoryLogger]    Script Date: 03/18/2016 17:20:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[TopicHistoryLogger] 
   ON  [dbo].[Topic] 
   AFTER UPDATE
AS 
BEGIN
	SET NOCOUNT ON;
	
IF EXISTS (SELECT * FROM INSERTED) 
	BEGIN
		IF EXISTS (SELECT * FROM DELETED) 
			BEGIN
				INSERT INTO TopicHistory 
					([TopicID],[TopicConent],[TopicHistoryCreated])
				SELECT TopicID, TopicContent,GETDATE()
					FROM DELETED
			END	
	END	

END
GO
/****** Object:  StoredProcedure [dbo].[spTopicUpdate]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicUpdate] 
    @TopicID int,
    @TopicName nvarchar(1024),
    @TopicContent nvarchar(MAX) = NULL
    
AS 
BEGIN
	SET NOCOUNT ON
	
	--ADD TO AUDIT
	INSERT INTO TopicHistory 
	SELECT @TopicID, (SELECT TopicContent from Topic Where TopicID = @TopicID),GETDATE()
	
	SET NOCOUNT OFF
	
	UPDATE [dbo].[Topic]
	SET    [TopicName] = @TopicName, [TopicContent] = @TopicContent
	,[TopicModified] = GETDATE(), [Revisions] = Revisions + 1
	WHERE  [TopicID] = @TopicID
	
	
END
GO
/****** Object:  StoredProcedure [dbo].[spTopicSelect]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicSelect] 
    @TopicID int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [TopicID], [TopicName], [TopicContent], [TopicCreated], [TopicModified], [Revisions] 
	FROM   [dbo].[Topic] 
	WHERE  ([TopicID] = @TopicID OR @TopicID IS NULL) 

	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTopicSearchByKeyword]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicSearchByKeyword]
@keyword nvarchar (1024) 

AS
SET NOCOUNT ON
SET XACT_ABORT ON

BEGIN TRAN

SELECT
	   [TopicID]
,	   [TopicName]
,	   [TopicContent]
,	   [TopicCreated]
,	   [TopicModified]
,	   [Revisions]
	   FROM [dbo].[Topic]
	   WHERE (LOWER ([TopicName]) LIKE '%' + LOWER (@keyword) + '%'
		   OR LOWER (TopicContent) LIKE '%' + LOWER (@keyword) + '%') 
		  OR (TopicID IN (  
							SELECT  -- FT SEarch
								  TOP 10 TopicID
								   FROM Topic
											JOIN CONTAINSTABLE (Topic , * , @keyword) FTS
												ON Topic.TopicID = FTS.[KEY]
								   ORDER BY
											FTS.Rank DESC) 
			 ) 

COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTopicInsert]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicInsert] 
    @TopicName nvarchar(1024),
    @TopicContent nvarchar(MAX) = NULL,
	@inserted INT OUTPUT
AS 
BEGIN
	SET NOCOUNT ON 
	INSERT INTO [dbo].[Topic] ([TopicName], [TopicContent], [TopicCreated], [TopicModified], [Revisions])
	SELECT @TopicName, @TopicContent, GETDATE(), GETDATE(), 0      
SET @inserted = Ident_current ('[dbo].[Topic]')
END
GO
/****** Object:  Table [dbo].[Tags]    Script Date: 03/18/2016 17:20:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tags](
	[TagID] [int] IDENTITY(1,1) NOT NULL,
	[TagName] [nvarchar](1024) NOT NULL,
	[ParentTagId] [int] NULL,
 CONSTRAINT [PK_Tags] PRIMARY KEY CLUSTERED 
(
	[TagID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Tags] ON
INSERT [dbo].[Tags] ([TagID], [TagName], [ParentTagId]) VALUES (1, N'Wiki', NULL)
SET IDENTITY_INSERT [dbo].[Tags] OFF
/****** Object:  StoredProcedure [dbo].[spTagsSelectByTopic]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTagsSelectByTopic]
@TopicID int
AS
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN
		SET NOCOUNT OFF;
		SELECT tg.TagID,TagName,ParentTagID 
		FROM Tags tg INNER JOIN TopicTags tt ON tg.TagID = tt.TagID 
		WHERE tt.TopicID = @TopicID
	
	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTagsDelete]    Script Date: 03/18/2016 17:20:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTagsDelete] 
    @TagID int
AS 

	SET NOCOUNT OFF 
	SET XACT_ABORT ON  
	BEGIN TRAN
	DELETE 
	FROM TopicTags WHERE TagID = @TagID
	DELETE
	FROM   [dbo].[Tags]
	WHERE  [TagID] = @TagID
	COMMIT
GO
/****** Object:  UserDefinedFunction [dbo].[fnTitleCase]    Script Date: 03/18/2016 17:20:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fnTitleCase] (
@StrIn nvarchar (1024)) 
RETURNS nvarchar (1024) 
AS
BEGIN
	DECLARE
	@StrOut nvarchar (1024) 
  ,
	@CurrentPosition int
  ,
	@NextSpace int
  ,
	@CurrentWord nvarchar (1024) 
  ,
	@StrLen int
  ,
	@LastWord bit

	SET @NextSpace = 1
	SET @CurrentPosition = 1
	SET @StrOut = ''
	SET @StrLen = LEN (@StrIn) 
	SET @LastWord = 0

	WHILE @LastWord = 0
		BEGIN
			SET @NextSpace = CHARINDEX (' ' , @StrIn , @CurrentPosition + 1) 
			IF  @NextSpace = 0 -- no more spaces found
				BEGIN
					SET @NextSpace = @StrLen
					SET @LastWord = 1
				END
			SET @CurrentWord = UPPER (SUBSTRING (@StrIn , @CurrentPosition , 1)) 
			SET @CurrentWord = @CurrentWord + LOWER (SUBSTRING (@StrIn , @CurrentPosition + 1 ,
@NextSpace - @CurrentPosition)) 
			SET @StrOut = @StrOut + @CurrentWord
			SET @CurrentPosition = @NextSpace + 1
		END
	RETURN @StrOut
END
GO
/****** Object:  UserDefinedFunction [dbo].[fnSplit]    Script Date: 03/18/2016 17:20:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fnSplit] (@sText varchar (8000) 
							   , @sDelim varchar (20) = ' ') 
RETURNS @retArray TABLE (
						idx smallint PRIMARY KEY
,						value varchar (8000)) 
AS
BEGIN
	DECLARE @idx smallint
		  ,
			@value varchar (8000) 
		  ,
			@bcontinue bit
		  ,
			@iStrike smallint
		  ,
			@iDelimlength tinyint

	IF @sDelim = 'Space'
		BEGIN
			SET @sDelim = ' '
		END

	SET @idx = 0
	SET @sText = LTRIM (RTRIM (@sText)) 
	SET @iDelimlength = DATALENGTH (@sDelim) 
	SET @bcontinue = 1

	IF NOT ((@iDelimlength = 0) 
		 OR (@sDelim = 'Empty')) 
		BEGIN
			WHILE @bcontinue = 1
				BEGIN

					--If you can find the delimiter in the text, retrieve the first element and
					--insert it with its index into the return table.

					IF CHARINDEX (@sDelim , @sText) > 0
						BEGIN
							SET @value = SUBSTRING (@sText , 1 , CHARINDEX (@sDelim , @sText) - 1) 
							BEGIN
								INSERT @retArray (
									   idx
,									   value) 
								VALUES (
									   @idx
									 , @value) 
							END

							--Trim the element and its delimiter from the front of the string.
							--Increment the index and loop.
							SET @iStrike = DATALENGTH (@value) + @iDelimlength
							SET @idx = @idx + 1
							SET @sText = LTRIM (RIGHT (@sText , DATALENGTH (@sText) - @iStrike)) 

						END
					ELSE
						BEGIN
							--If you can’t find the delimiter in the text, @sText is the last value in
							--@retArray.
							SET @value = @sText
							BEGIN
								INSERT @retArray (
									   idx
,									   value) 
								VALUES (
									   @idx
									 , @value) 
							END
							--Exit the WHILE loop.
							SET @bcontinue = 0
						END
				END
		END
	ELSE
		BEGIN
			WHILE @bcontinue = 1
				BEGIN
					--If the delimiter is an empty string, check for remaining text
					--instead of a delimiter. Insert the first character into the
					--retArray table. Trim the character from the front of the string.
					--Increment the index and loop.
					IF DATALENGTH (@sText) > 1
						BEGIN
							SET @value = SUBSTRING (@sText , 1 , 1) 
							BEGIN
								INSERT @retArray (
									   idx
,									   value) 
								VALUES (
									   @idx
									 , @value) 
							END
							SET @idx = @idx + 1
							SET @sText = SUBSTRING (@sText , 2 , DATALENGTH (@sText) - 1) 

						END
					ELSE
						BEGIN
							--One character remains.
							--Insert the character, and exit the WHILE loop.
							INSERT @retArray (
								   idx
,								   value) 
							VALUES (
								   @idx
								 , @sText) 
							SET @bcontinue = 0
						END
				END

		END

	RETURN
END
GO
/****** Object:  UserDefinedFunction [dbo].[fnCSVToInt]    Script Date: 03/18/2016 17:20:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fnCSVToInt] (@array varchar(max))
RETURNS @retTable TABLE (
							[row]	smallint IDENTITY(1,1)
						,	[id] int
						)
AS
  BEGIN
      DECLARE @separator char(1)
      SET @separator = ','
      DECLARE @separator_position int
      DECLARE @array_value varchar(max)
      SET @array = @array + ','
      WHILE PATINDEX('%,%', @array) <> 0
        BEGIN
            SELECT @separator_position = PATINDEX('%,%', @array)
            SELECT @array_value = LEFT(@array, @separator_position - 1)
            INSERT @retTable
				VALUES (CAST(@array_value AS int))
            SELECT @array = STUFF(@array, 1, @separator_position, '')
        END
      RETURN
  END
GO
/****** Object:  UserDefinedFunction [dbo].[fnCamelCase]    Script Date: 03/18/2016 17:20:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fnCamelCase] (@NAME nvarchar (512)) 
RETURNS nvarchar (512) 
AS
BEGIN
	DECLARE @CAMEL_NAME  nvarchar (255) ;
	DECLARE @CurrentPosR  int;
	DECLARE @NextPosR     int;
	SET @CAMEL_NAME = LOWER (@NAME) ;
	SET @CAMEL_NAME = UPPER (LEFT (@CAMEL_NAME , 1)) + SUBSTRING (@CAMEL_NAME , 2 , LEN (@NAME)) ;

	SET @CurrentPosR = 1;
	WHILE CHARINDEX (' ' , @CAMEL_NAME , @CurrentPosR) > 0
		BEGIN
			SET @NextPosR = CHARINDEX (' ' , @CAMEL_NAME , @CurrentPosR) ;
			SET @CAMEL_NAME = LEFT (@CAMEL_NAME , @NextPosR - 1) + ' ' + UPPER (SUBSTRING (
@CAMEL_NAME , @NextPosR + 1 , 1)) + SUBSTRING (@CAMEL_NAME , @NextPosR + 2 , LEN (@NAME)) ;
			SET @CurrentPosR = @NextPosR + 1;
		END -- while;
	RETURN @CAMEL_NAME;
END
GO
/****** Object:  Table [dbo].[Users]    Script Date: 03/18/2016 17:20:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](128) NOT NULL,
	[Password] [nvarchar](512) NOT NULL,
	[UserRole] [nvarchar](64) NULL,
	[EmailAddress] [nvarchar](1028) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Users] ON
INSERT [dbo].[Users] ([UserID], [UserName], [Password], [UserRole], [EmailAddress]) VALUES (1, N'admin', N'ilovewiki', N'admin', N'wiki@wiki.com')
SET IDENTITY_INSERT [dbo].[Users] OFF
/****** Object:  StoredProcedure [dbo].[spTagsSelect]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTagsSelect] 
    @TagID int = NULL,
    @TagName nvarchar(max) = NULL
    
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN
	SET NOCOUNT OFF
	SELECT [TagID], [TagName], [ParentTagId] 
	FROM   [dbo].[Tags] 
	WHERE  (([TagID] = @TagID OR @TagID IS NULL) AND ([TagName] = @TagName OR @TagName IS NULL))

	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTagsInsert]    Script Date: 03/18/2016 17:20:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTagsInsert] 
    @TagName nvarchar(1024),
    @ParentTagId int = NULL
, @inserted INT OUTPUT
AS 
BEGIN
	SET NOCOUNT ON 
	INSERT INTO [dbo].[Tags] ([TagName], [ParentTagId])
	SELECT @TagName, @ParentTagId      
SET @inserted = Ident_current ('[dbo].[Tags]')
END
GO
/****** Object:  StoredProcedure [dbo].[spTagsUpdate]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTagsUpdate] 
    @TagID int,
    @TagName nvarchar(1024),
    @ParentTagId int = NULL
AS 
BEGIN
	SET NOCOUNT OFF
	UPDATE [dbo].[Tags]
	SET    [TagName] = @TagName, [ParentTagId] = @ParentTagId
	WHERE  [TagID] = @TagID
	
END
GO
/****** Object:  StoredProcedure [dbo].[spUsersUpdate]    Script Date: 03/18/2016 17:20:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersUpdate] 
    @UserID int,
    @UserName nvarchar(128),
    @Password nvarchar(512),
    @UserRole nvarchar(64) = NULL,
    @EmailAddress nvarchar(1028) = NULL
AS 
BEGIN
	SET NOCOUNT OFF
	UPDATE [dbo].[Users]
	SET    [UserName] = @UserName, [Password] = @Password, [UserRole] = isnull(@UserRole,UserRole), [EmailAddress] = @EmailAddress
	WHERE  [UserID] = @UserID
	
END
GO
/****** Object:  StoredProcedure [dbo].[spUsersSelectByUserNameAndPassword]    Script Date: 03/18/2016 17:20:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersSelectByUserNameAndPassword] 
    @UserName nvarchar(max),
    @Password nvarchar(max)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [UserID], [UserName], [Password], [UserRole], [EmailAddress] 
	FROM   [dbo].[Users] 
	WHERE  (LOWER([UserName]) = LOWER(@UserName) AND @Password = [Password]) 

	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spUsersSelectByID]    Script Date: 03/18/2016 17:20:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersSelectByID] 
    @UserID int = NULL
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  
	BEGIN TRAN

	SELECT [UserID], [UserName], [Password], [UserRole], [EmailAddress] 
	FROM   [dbo].[Users] 
	WHERE  ([UserID] = @UserID OR @UserID IS NULL) 

	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spUsersInsert]    Script Date: 03/18/2016 17:20:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersInsert] 
    @UserName nvarchar(128),
    @Password nvarchar(512),
    @UserRole nvarchar(64) = 'viewer',
    @EmailAddress nvarchar(1028) = NULL
, @inserted INT OUTPUT
AS 
BEGIN
	SET NOCOUNT ON 
	INSERT INTO [dbo].[Users] ([UserName], [Password], [UserRole], [EmailAddress])
	SELECT @UserName, @Password, @UserRole, @EmailAddress      
SET @inserted = Ident_current ('[dbo].[Users]')
END
GO
/****** Object:  StoredProcedure [dbo].[spUsersDelete]    Script Date: 03/18/2016 17:20:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersDelete] 
    @UserID int
AS 
	SET NOCOUNT OFF 
	SET XACT_ABORT ON  
	BEGIN TRAN
	DELETE
	FROM   [dbo].[Users]
	WHERE  [UserID] = @UserID
	COMMIT
GO
/****** Object:  Default [DF_Topic_Revisions]    Script Date: 03/18/2016 17:20:46 ******/
ALTER TABLE [dbo].[Topic] ADD  CONSTRAINT [DF_Topic_Revisions]  DEFAULT ((0)) FOR [Revisions]
GO
/****** Object:  Default [DF_TopicHistory_TopicHistoryCreated]    Script Date: 03/18/2016 17:20:47 ******/
ALTER TABLE [dbo].[TopicHistory] ADD  CONSTRAINT [DF_TopicHistory_TopicHistoryCreated]  DEFAULT (getdate()) FOR [TopicHistoryCreated]
GO
/****** Object:  Default [DF_Users_UserRole]    Script Date: 03/18/2016 17:20:47 ******/
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_UserRole]  DEFAULT (N'viewer') FOR [UserRole]
GO
/****** Object:  ForeignKey [FK_TopicHistory_Topic]    Script Date: 03/18/2016 17:20:47 ******/
ALTER TABLE [dbo].[TopicHistory]  WITH CHECK ADD  CONSTRAINT [FK_TopicHistory_Topic] FOREIGN KEY([TopicID])
REFERENCES [dbo].[Topic] ([TopicID])
GO
ALTER TABLE [dbo].[TopicHistory] CHECK CONSTRAINT [FK_TopicHistory_Topic]
GO
/****** Object:  ForeignKey [FK_TopicTags_Tags]    Script Date: 03/18/2016 17:20:47 ******/
ALTER TABLE [dbo].[TopicTags]  WITH CHECK ADD  CONSTRAINT [FK_TopicTags_Tags] FOREIGN KEY([TagID])
REFERENCES [dbo].[Tags] ([TagID])
GO
ALTER TABLE [dbo].[TopicTags] CHECK CONSTRAINT [FK_TopicTags_Tags]
GO
/****** Object:  ForeignKey [FK_TopicTags_Topic]    Script Date: 03/18/2016 17:20:47 ******/
ALTER TABLE [dbo].[TopicTags]  WITH CHECK ADD  CONSTRAINT [FK_TopicTags_Topic] FOREIGN KEY([TopicID])
REFERENCES [dbo].[Topic] ([TopicID])
GO
ALTER TABLE [dbo].[TopicTags] CHECK CONSTRAINT [FK_TopicTags_Topic]
GO
