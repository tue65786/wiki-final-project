USE [cis3238FinalProject]
GO
/****** Object:  User [CIS3238]    Script Date: 03/24/2016 08:50:49 ******/
CREATE USER [CIS3238] FOR LOGIN [CIS3238] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  FullTextCatalog [TopicFTC]    Script Date: 03/24/2016 08:50:49 ******/
CREATE FULLTEXT CATALOG [TopicFTC]WITH ACCENT_SENSITIVITY = ON
AS DEFAULT
AUTHORIZATION [dbo]
GO
/****** Object:  Table [dbo].[Tags]    Script Date: 03/24/2016 08:50:52 ******/
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
/****** Object:  UserDefinedFunction [dbo].[fnCamelCase]    Script Date: 03/24/2016 08:50:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fnCamelCase] (@NAME nvarchar (1024)) 
RETURNS nvarchar (1024) 
AS
BEGIN
	DECLARE @CAMEL_NAME  nvarchar (1024) ;
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
/****** Object:  Table [dbo].[Topic]    Script Date: 03/24/2016 08:50:53 ******/
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
	[TopicNameCamelCase]  AS ([dbo].[fnCamelCase]([TopicName])),
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
/****** Object:  Table [dbo].[TopicTags]    Script Date: 03/24/2016 08:50:53 ******/
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
/****** Object:  View [dbo].[vwTopicInTags]    Script Date: 03/24/2016 08:50:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[vwTopicInTags]
AS
SELECT     dbo.Topic.TopicID, dbo.Topic.TopicName, dbo.Tags.TagName, dbo.Tags.TagID
FROM         dbo.Tags INNER JOIN
                      dbo.TopicTags ON dbo.Tags.TagID = dbo.TopicTags.TagID RIGHT OUTER JOIN
                      dbo.Topic ON dbo.TopicTags.TopicID = dbo.Topic.TopicID
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "TopicTags"
            Begin Extent = 
               Top = 6
               Left = 452
               Bottom = 88
               Right = 604
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Topic"
            Begin Extent = 
               Top = 6
               Left = 38
               Bottom = 118
               Right = 224
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Tags"
            Begin Extent = 
               Top = 6
               Left = 262
               Bottom = 103
               Right = 414
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'vwTopicInTags'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'vwTopicInTags'
GO
/****** Object:  View [dbo].[vwTopicTagsCount]    Script Date: 03/24/2016 08:50:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[vwTopicTagsCount] AS
SELECT TopicID
      ,[TopicName]
      ,COUNT([TagID]) TagsCount
  FROM [cis3238FinalProject].[dbo].[vwTopicInTags]
  GROUP BY TopicID, TopicName
GO
/****** Object:  View [dbo].[vwTagsTopicCount]    Script Date: 03/24/2016 08:50:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[vwTagsTopicCount] AS
SELECT TagID
      ,[TagName]
      ,COUNT([TopicID]) TopicsCount
  FROM [cis3238FinalProject].[dbo].[vwTopicInTags]
  GROUP BY TagID, TagName
GO
/****** Object:  StoredProcedure [dbo].[spTopicChangeTagBinding]    Script Date: 03/24/2016 08:51:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[spTopicChangeTagBinding]
@TopicID int,
@TagID int,
@mustAssign bit

AS
SET NOCOUNT OFF
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
RETURN @@ROWCOUNT
GO
/****** Object:  Table [dbo].[TopicHistory]    Script Date: 03/24/2016 08:51:02 ******/
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
/****** Object:  StoredProcedure [dbo].[spTopicHistorySelectByTopic]    Script Date: 03/24/2016 08:51:02 ******/
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
/****** Object:  Trigger [TopicHistoryLogger]    Script Date: 03/24/2016 08:51:04 ******/
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
/****** Object:  StoredProcedure [dbo].[spTopicUpdate]    Script Date: 03/24/2016 08:51:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicUpdate] 
    @TopicID int,
    @TopicName nvarchar(1024) = NULL,
    @TopicContent nvarchar(MAX) = NULL
    
AS 
BEGIN
	SET NOCOUNT OFF
	
	UPDATE [dbo].[Topic]
	SET    [TopicName] = ISNULL(@TopicName,TopicName)
			, [TopicContent] = ISNULL(@TopicContent,TopicContent)
			, [TopicModified] = GETDATE()
			, [Revisions] = Revisions + 1
	WHERE  [TopicID] = @TopicID
	
	RETURN @@ROWCOUNT
END
GO
/****** Object:  StoredProcedure [dbo].[spTopicSelectByTag]    Script Date: 03/24/2016 08:51:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicSelectByTag]
@TagID int
AS
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN
		SET NOCOUNT OFF;
		SELECT t.[TopicID], t.[TopicName], t.[TopicContent], t.[TopicCreated], t.[TopicModified], t.[Revisions] 
		FROM dbo.Topic t INNER JOIN dbo.TopicTags tt ON t.TopicID = tt.TopicID 
		WHERE tt.TagID = @TagID
	
	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTopicSelect]    Script Date: 03/24/2016 08:51:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicSelect] 
    @TopicID int = NULL,
    @TopicName nvarchar(max)
AS 
	SET NOCOUNT ON 
	SET XACT_ABORT ON  

	BEGIN TRAN

	SELECT [TopicID], [TopicName], [TopicContent], [TopicCreated], [TopicModified], [Revisions] 
	FROM   [dbo].[Topic] 
	WHERE  ([TopicID] = @TopicID OR @TopicID IS NULL) AND (TopicName = @TopicName OR @TopicName IS NULL)

	COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTopicSearchByKeyword]    Script Date: 03/24/2016 08:51:02 ******/
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
DECLARE @tbL TABLE (topicPK int)

 INSERT INTO @tbL
 SELECT TopicID 
 FROM dbo.vwTopicInTags
 WHERE LOWER(TagName) LIKE '%' + LOWER(@keyword) + '%'
 
SELECT
	   [TopicID]
,	   [TopicName]
,	   [TopicContent]
,	   [TopicCreated]
,	   [TopicModified]
,	   [Revisions]
	   FROM [dbo].[Topic]
	   WHERE 
			LOWER ([TopicName]) LIKE '%' + LOWER (@keyword) + '%'
			OR LOWER (TopicContent) LIKE '%' + LOWER (@keyword) + '%'
			OR (
				TopicID IN (  
							SELECT  -- FT SEarch
								  TOP 10 TopicID
								   FROM Topic
											JOIN CONTAINSTABLE (Topic , * , @keyword) FTS
												ON Topic.TopicID = FTS.[KEY]
								   ORDER BY
											FTS.Rank DESC) 
			 )
			OR ( -- Tags Search
				TopicID IN (SELECT topicPK FROM @tbL)
			)

COMMIT
GO
/****** Object:  StoredProcedure [dbo].[spTopicInsert]    Script Date: 03/24/2016 08:51:02 ******/
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
	SET NOCOUNT ON;
IF NOT EXISTS(SELECT 1 FROM [cis3238FinalProject].dbo.Topic WHERE TopicName = @TopicName)
	BEGIN
		INSERT INTO [dbo].[Topic] ([TopicName], [TopicContent], [TopicCreated], [TopicModified], [Revisions])
		SELECT @TopicName, @TopicContent, GETDATE(), GETDATE(), 0      
		SET @inserted = Ident_current ('[dbo].[Topic]')
	END 
ELSE
	BEGIN
		SET @inserted = (SELECT TOP 1 TopicID FROM Topic WHERE TopicName = @TopicName)
		
		if (@inserted IS NOT NULL)
			BEGIN
				UPDATE dbo.Topic 
				SET TopicContent = ISNULL(@TopicContent,TopicContent)
				WHERE TopicID = @inserted
			END 	
	END 	
END
GO
/****** Object:  StoredProcedure [dbo].[spTopicHistorySelectByTopicName]    Script Date: 03/24/2016 08:51:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicHistorySelectByTopicName] 
    @TopicName nvarchar(max)
AS 
	--SET NOCOUNT ON 
	DECLARE @TopicID int = (
					SELECT TOP 1 TopicID 
						FROM Topic 
						WHERE LOWER(TopicName) = LOWER(@TopicName)
					)
	IF @TopicID IS NOT NULL
		BEGIN
			SELECT [TopicHistoryID], [TopicID], [TopicConent], [TopicHistoryCreated] 
				FROM   [dbo].[TopicHistory] 
				WHERE  ([TopicID] = @TopicID )
		END
GO
/****** Object:  StoredProcedure [dbo].[spTopicHistoryRevertTopicByTopicHistoryID]    Script Date: 03/24/2016 08:51:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicHistoryRevertTopicByTopicHistoryID]
    @TopicHistoryID int
AS 
	SET NOCOUNT ON;
	DECLARE @TopicID int = 0
	DECLARE @TopicConent nvarchar(max)
	DECLARE @TopicModified datetime	
	
	IF EXISTS(SELECT 1 FROM TopicHistory WHERE TopicHistoryID = @TopicHistoryID)
		BEGIN
			SELECT	@TopicID=TopicID
					,@TopicConent=TopicConent
					,@TopicModified=TopicHistoryCreated 
				FROM TopicHistory

			IF (@TopicConent IS NOT NULL AND @TopicID IS NOT NULL)
					BEGIN
					SET NOCOUNT OFF;
						UPDATE Topic SET TopicContent = @TopicConent
										,TopicModified = @TopicModified
							WHERE TopicID = @TopicID
					END 	
		END 		
	RETURN @@ROWCOUNT
GO
/****** Object:  StoredProcedure [dbo].[spTopicDelete]    Script Date: 03/24/2016 08:51:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTopicDelete] 
    @TopicID int
AS 

	SET NOCOUNT ON	
	DELETE 
	FROM TopicTags 
	WHERE TopicID = @TopicID

	DELETE 
	FROM TopicHistory
	WHERE TopicID = @TopicID
	
	SET NOCOUNT OFF
	DELETE
	FROM   [dbo].[Topic]
	WHERE  [TopicID] = @TopicID
	RETURN @@ROWCOUNT
GO
/****** Object:  StoredProcedure [dbo].[spTagsSelectByTopic]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  StoredProcedure [dbo].[spTagsDelete]    Script Date: 03/24/2016 08:51:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spTagsDelete] 
    @TagID int
AS 

	SET NOCOUNT ON; 
	
	DELETE 
	FROM TopicTags WHERE TagID = @TagID

SET NOCOUNT OFF;	
	DELETE
	FROM   [dbo].[Tags]
	WHERE  [TagID] = @TagID
	
RETURN @@ROWCOUNT
GO
/****** Object:  Table [dbo].[Users]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  UserDefinedFunction [dbo].[fnTitleCase]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  UserDefinedFunction [dbo].[fnSplit]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  UserDefinedFunction [dbo].[fnCSVToInt]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  StoredProcedure [dbo].[spTagsSelect]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  StoredProcedure [dbo].[spTagsInsert]    Script Date: 03/24/2016 08:51:03 ******/
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
	IF EXISTS (SELECT 1 FROM Tags WHERE TagName = @TagName)
		BEGIN
			SET @inserted = (SELECT TOP 1 TagID FROM Tags WHERE TagName = @TagName)
		END
	ELSE
		BEGIN 		
			INSERT INTO [dbo].[Tags] ([TagName], [ParentTagId])
			SELECT @TagName, @ParentTagId      
		SET @inserted = Ident_current ('[dbo].[Tags]')
		END 
END
GO
/****** Object:  StoredProcedure [dbo].[spTagsUpdate]    Script Date: 03/24/2016 08:51:03 ******/
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
/****** Object:  StoredProcedure [dbo].[spUsersUpdate]    Script Date: 03/24/2016 08:51:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersUpdate] 
    @UserID int,
    @UserName nvarchar(128) = NULL,
    @Password nvarchar(512) = NULL,
    @UserRole nvarchar(64) = NULL,
    @EmailAddress nvarchar(1028) = NULL
AS 
BEGIN
	SET NOCOUNT OFF
	UPDATE [dbo].[Users]
	SET		[UserName] = ISNULL(@UserName,UserName)
			, [Password] = ISNULL(@Password,[Password])
			, [UserRole] = ISNULL(@UserRole,UserRole)
			, [EmailAddress] = ISNULL(@EmailAddress,EmailAddress)
	WHERE  [UserID] = @UserID
	
	RETURN @@ROWCOUNT
	
END
GO
/****** Object:  StoredProcedure [dbo].[spUsersSelectByUserNameAndPassword]    Script Date: 03/24/2016 08:51:04 ******/
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
/****** Object:  StoredProcedure [dbo].[spUsersSelectByUserName]    Script Date: 03/24/2016 08:51:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersSelectByUserName] 
    @UserName nvarchar(max)
AS 
	SET NOCOUNT ON;
	
	SELECT TOP 1 [UserID], [UserName], [Password], [UserRole], [EmailAddress] 
	FROM   [dbo].[Users] 
	WHERE  (LOWER([UserName]) = LOWER(@UserName))
GO
/****** Object:  StoredProcedure [dbo].[spUsersSelectByID]    Script Date: 03/24/2016 08:51:04 ******/
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
/****** Object:  StoredProcedure [dbo].[spUsersSelect]    Script Date: 03/24/2016 08:51:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[spUsersSelect] 
    @UserName nvarchar(max) = NULL
AS 
	SET NOCOUNT ON;
	
	SELECT  [UserID], [UserName], [Password], [UserRole], [EmailAddress] 
	FROM   [dbo].[Users] 
	WHERE  (LOWER([UserName]) = LOWER(@UserName) OR @UserName IS NULL)
GO
/****** Object:  StoredProcedure [dbo].[spUsersInsert]    Script Date: 03/24/2016 08:51:04 ******/
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
/****** Object:  StoredProcedure [dbo].[spUsersDelete]    Script Date: 03/24/2016 08:51:04 ******/
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
/****** Object:  Default [DF_Topic_Revisions]    Script Date: 03/24/2016 08:50:53 ******/
ALTER TABLE [dbo].[Topic] ADD  CONSTRAINT [DF_Topic_Revisions]  DEFAULT ((0)) FOR [Revisions]
GO
/****** Object:  Default [DF_TopicHistory_TopicHistoryCreated]    Script Date: 03/24/2016 08:51:02 ******/
ALTER TABLE [dbo].[TopicHistory] ADD  CONSTRAINT [DF_TopicHistory_TopicHistoryCreated]  DEFAULT (getdate()) FOR [TopicHistoryCreated]
GO
/****** Object:  Default [DF_Users_UserRole]    Script Date: 03/24/2016 08:51:03 ******/
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_UserRole]  DEFAULT (N'viewer') FOR [UserRole]
GO
/****** Object:  ForeignKey [FK_TopicTags_Tags]    Script Date: 03/24/2016 08:50:53 ******/
ALTER TABLE [dbo].[TopicTags]  WITH CHECK ADD  CONSTRAINT [FK_TopicTags_Tags] FOREIGN KEY([TagID])
REFERENCES [dbo].[Tags] ([TagID])
GO
ALTER TABLE [dbo].[TopicTags] CHECK CONSTRAINT [FK_TopicTags_Tags]
GO
/****** Object:  ForeignKey [FK_TopicTags_Topic]    Script Date: 03/24/2016 08:50:53 ******/
ALTER TABLE [dbo].[TopicTags]  WITH CHECK ADD  CONSTRAINT [FK_TopicTags_Topic] FOREIGN KEY([TopicID])
REFERENCES [dbo].[Topic] ([TopicID])
GO
ALTER TABLE [dbo].[TopicTags] CHECK CONSTRAINT [FK_TopicTags_Topic]
GO
/****** Object:  ForeignKey [FK_TopicHistory_Topic]    Script Date: 03/24/2016 08:51:02 ******/
ALTER TABLE [dbo].[TopicHistory]  WITH CHECK ADD  CONSTRAINT [FK_TopicHistory_Topic] FOREIGN KEY([TopicID])
REFERENCES [dbo].[Topic] ([TopicID])
GO
ALTER TABLE [dbo].[TopicHistory] CHECK CONSTRAINT [FK_TopicHistory_Topic]
GO
