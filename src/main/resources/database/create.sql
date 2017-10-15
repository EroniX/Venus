CREATE DATABASE `venus` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `courses` (
  `Id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `TeacherId` int(32) unsigned NOT NULL,
  `SemesterId` int(32) unsigned NOT NULL,
  `SubjectId` int(32) unsigned NOT NULL,
  `Limit` int(32) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Courses.TeacherId_Users.Id` (`TeacherId`),
  KEY `Courses.SemesterId_Semesters.Id` (`SemesterId`),
  KEY `Courses.SubjectId_Subjects.Id` (`SubjectId`),
  CONSTRAINT `Courses.SemesterId_Semesters.Id` FOREIGN KEY (`SemesterId`) REFERENCES `semesters` (`Id`),
  CONSTRAINT `Courses.SubjectId_Subjects.Id` FOREIGN KEY (`SubjectId`) REFERENCES `subjects` (`Id`),
  CONSTRAINT `Courses.TeacherId_Users.Id` FOREIGN KEY (`TeacherId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `semesters` (
  `Id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `From` date NOT NULL,
  `To` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `subjects` (
  `Id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Code` varchar(255) NOT NULL,
  `TrainingId` int(32) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Code_UNIQUE` (`Code`),
  KEY `Subjects.TrainingId_Trainings.Id` (`TrainingId`),
  CONSTRAINT `Subjects.TrainingId_Trainings.Id` FOREIGN KEY (`TrainingId`) REFERENCES `trainings` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `trainings` (
  `Id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `Id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Role` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`Email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `users_courses` (
  `Id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(32) unsigned NOT NULL,
  `CourseId` int(32) unsigned NOT NULL,
  `Mark` int(32) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Users_Courses.UserId_Users.Id` (`UserId`),
  KEY `Users_Courses.CourseId_Courses.Id` (`CourseId`),
  CONSTRAINT `Users_Courses.CourseId_Courses.Id` FOREIGN KEY (`CourseId`) REFERENCES `courses` (`Id`),
  CONSTRAINT `Users_Courses.UserId_Users.Id` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `users_trainings` (
  `UserId` int(32) unsigned NOT NULL,
  `TrainingId` int(32) unsigned NOT NULL,
  PRIMARY KEY (`UserId`,`TrainingId`),
  KEY `Users_Trainings.UserId_Users.Id` (`TrainingId`),
  CONSTRAINT `Users_Trainings.TrainingId_Trainings.Id` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`),
  CONSTRAINT `Users_Trainings.UserId_Users.Id` FOREIGN KEY (`TrainingId`) REFERENCES `trainings` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SELECT * FROM venus.semesters;