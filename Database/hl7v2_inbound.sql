DROP SCHEMA hl7v2_inbound;

CREATE SCHEMA hl7v2_inbound;

USE hl7v2_inbound;

CREATE TABLE `imperial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_received` datetime DEFAULT NULL,
  `message_wrapper` text,
  `hl7_message` text,
  `payload_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `payload_id_UNIQUE` (`payload_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
