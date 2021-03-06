USE [master]
GO
/****** Object:  Database [SINHVIEN]    Script Date: 10/11/2021 10:28:23 PM ******/
CREATE DATABASE [SINHVIEN]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SINHVIEN', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SINHVIEN.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SINHVIEN_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SINHVIEN_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SINHVIEN] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SINHVIEN].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SINHVIEN] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SINHVIEN] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SINHVIEN] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SINHVIEN] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SINHVIEN] SET ARITHABORT OFF 
GO
ALTER DATABASE [SINHVIEN] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SINHVIEN] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SINHVIEN] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SINHVIEN] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SINHVIEN] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SINHVIEN] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SINHVIEN] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SINHVIEN] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SINHVIEN] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SINHVIEN] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SINHVIEN] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SINHVIEN] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SINHVIEN] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SINHVIEN] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SINHVIEN] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SINHVIEN] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SINHVIEN] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SINHVIEN] SET RECOVERY FULL 
GO
ALTER DATABASE [SINHVIEN] SET  MULTI_USER 
GO
ALTER DATABASE [SINHVIEN] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SINHVIEN] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SINHVIEN] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SINHVIEN] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SINHVIEN] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SINHVIEN', N'ON'
GO
USE [SINHVIEN]
GO
/****** Object:  Table [dbo].[SV]    Script Date: 10/11/2021 10:28:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SV](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](max) NULL,
	[Class] [nvarchar](max) NULL,
	[Address] [nvarchar](max) NULL,
	[Gender] [nvarchar](3) NULL,
	[Phone] [nvarchar](max) NULL,
 CONSTRAINT [PK_SV] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[SV] ([Id], [Name], [Class], [Address], [Gender], [Phone]) VALUES (1, N'Hà', N'6A12      ', N'57 HTLQ', N'Nữ', N'0342542386  ')
INSERT [dbo].[SV] ([Id], [Name], [Class], [Address], [Gender], [Phone]) VALUES (2, N'Nhật', N'7A12      ', N'5 PM', N'Nam', N'0123456789  ')
INSERT [dbo].[SV] ([Id], [Name], [Class], [Address], [Gender], [Phone]) VALUES (3, N'Tâm', N'8A12      ', N'97 MT', N'Nữ', N'0987654321  ')
USE [master]
GO
ALTER DATABASE [SINHVIEN] SET  READ_WRITE 
GO
