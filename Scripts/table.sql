CREATE DATABASE IF NOT EXISTS `xml_uploader`;

USE `xml_uploader`;

DROP TABLE IF EXISTS `epaper_request`;

CREATE TABLE `epaper_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `newspaper_name` varchar(100) NOT NULL,
  `width` smallint,
  `height` smallint,
  `dpi` smallint,
  `upload_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `file_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 