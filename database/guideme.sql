--
-- Скрипт сгенерирован Devart dbForge Studio for MySQL, Версия 6.3.358.0
-- Домашняя страница продукта: http://www.devart.com/ru/dbforge/mysql/studio
-- Дата скрипта: 07.11.2015 17:56:53
-- Версия сервера: 5.6.25-log
-- Версия клиента: 4.1
--


-- 
-- Отключение внешних ключей
-- 
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

-- 
-- Установить режим SQL (SQL mode)
-- 
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

-- 
-- Установка базы данных по умолчанию
--
USE guideme;

--
-- Описание для таблицы language
--
DROP TABLE IF EXISTS language;
CREATE TABLE language (
  id INT(11) NOT NULL AUTO_INCREMENT,
  short_name VARCHAR(20) NOT NULL,
  name VARCHAR(50) NOT NULL,
  localized TINYINT(1) DEFAULT NULL,
  deleted TINYINT(1) DEFAULT 0,
  locale VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX `key` (short_name)
)
ENGINE = INNODB
AUTO_INCREMENT = 10
AVG_ROW_LENGTH = 2340
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы photo
--
DROP TABLE IF EXISTS photo;
CREATE TABLE photo (
  id INT(11) NOT NULL AUTO_INCREMENT,
  path VARCHAR(255) NOT NULL,
  owner_type ENUM('user','event') DEFAULT NULL,
  owner_id INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX FK_photo_event_id (owner_id)
)
ENGINE = INNODB
AUTO_INCREMENT = 154
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы tag
--
DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  deleted TINYINT(1) DEFAULT 0,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 19
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user_forgot_password
--
DROP TABLE IF EXISTS user_forgot_password;
CREATE TABLE user_forgot_password (
  id INT(11) NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) DEFAULT NULL,
  code VARCHAR(255) DEFAULT NULL,
  date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  is_available INT(11) DEFAULT 1,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 42
AVG_ROW_LENGTH = 1170
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user_type
--
DROP TABLE IF EXISTS user_type;
CREATE TABLE user_type (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name (name)
)
ENGINE = INNODB
AUTO_INCREMENT = 9
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы country
--
DROP TABLE IF EXISTS country;
CREATE TABLE country (
  id INT(11) NOT NULL AUTO_INCREMENT,
  pure_id INT(11) NOT NULL,
  local_id INT(11) NOT NULL,
  name VARCHAR(100) NOT NULL,
  deleted TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT FK_country_language_id FOREIGN KEY (local_id)
    REFERENCES language(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 19
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы city
--
DROP TABLE IF EXISTS city;
CREATE TABLE city (
  id INT(11) NOT NULL AUTO_INCREMENT,
  pure_id INT(11) NOT NULL,
  local_id INT(11) NOT NULL,
  name VARCHAR(100) NOT NULL,
  country_id INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted TINYINT(1) DEFAULT 0,
  lattitude DOUBLE(12, 5) DEFAULT NULL,
  longitude DOUBLE(12, 5) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_city_country_id FOREIGN KEY (country_id)
    REFERENCES country(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_city_language_id FOREIGN KEY (local_id)
    REFERENCES language(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 45
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы address
--
DROP TABLE IF EXISTS address;
CREATE TABLE address (
  id INT(11) NOT NULL AUTO_INCREMENT,
  pure_id INT(11) NOT NULL,
  local_id INT(11) NOT NULL DEFAULT 2,
  address VARCHAR(255) DEFAULT NULL,
  city_id INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  lattitude DOUBLE(12, 6) DEFAULT NULL,
  longitude DOUBLE(12, 6) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_address_language_id FOREIGN KEY (local_id)
    REFERENCES language(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_adress_city_id FOREIGN KEY (city_id)
    REFERENCES city(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 271
AVG_ROW_LENGTH = 1820
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user
--
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(100) DEFAULT NULL,
  user_type_id INT(11) NOT NULL,
  sex ENUM('female','male') DEFAULT NULL,
  lang_id INT(11) NOT NULL,
  cell_number VARCHAR(255) DEFAULT NULL,
  facebook_id VARCHAR(255) DEFAULT NULL,
  vk_id VARCHAR(255) DEFAULT NULL,
  is_active TINYINT(1) NOT NULL DEFAULT 1,
  address_id INT(11) DEFAULT NULL,
  password VARCHAR(255) NOT NULL,
  avatar_id INT(11) NOT NULL DEFAULT 10,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  birth_date DATE DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX FK_user_photo_id (avatar_id),
  CONSTRAINT FK_user_adress_id FOREIGN KEY (address_id)
    REFERENCES address(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_user_language_id FOREIGN KEY (lang_id)
    REFERENCES language(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_user_user_type_id FOREIGN KEY (user_type_id)
    REFERENCES user_type(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 49
AVG_ROW_LENGTH = 2730
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы comment_user
--
DROP TABLE IF EXISTS comment_user;
CREATE TABLE comment_user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  commentator_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  comment VARCHAR(512) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_comment_commentator_user_id FOREIGN KEY (commentator_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_comment_user_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 32
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы event
--
DROP TABLE IF EXISTS event;
CREATE TABLE event (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(1024) DEFAULT NULL,
  date_from DATETIME DEFAULT NULL,
  date_to DATETIME DEFAULT NULL,
  address_id INT(11) NOT NULL,
  moderator_id INT(11) NOT NULL,
  status ENUM('active','filled','cancelled','done') DEFAULT NULL,
  avatar_id INT(11) NOT NULL DEFAULT 11,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  participants_limit INT(11) DEFAULT NULL,
  deleted TINYINT(1) DEFAULT 0,
  video_link VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX FK_event_adress_id (address_id),
  CONSTRAINT FK_event_address_id FOREIGN KEY (address_id)
    REFERENCES address(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_event_photo_id FOREIGN KEY (avatar_id)
    REFERENCES photo(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_event_user_id FOREIGN KEY (moderator_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 54
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы friend_user
--
DROP TABLE IF EXISTS friend_user;
CREATE TABLE friend_user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) DEFAULT NULL,
  friend_id INT(11) DEFAULT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_friend_friend_user_id FOREIGN KEY (friend_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_friend_user_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 170
AVG_ROW_LENGTH = 2730
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы getmoney
--
DROP TABLE IF EXISTS getmoney;
CREATE TABLE getmoney (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  sum INT(11) DEFAULT NULL,
  Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_getmoney_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы message_user
--
DROP TABLE IF EXISTS message_user;
CREATE TABLE message_user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  sender_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  message VARCHAR(512) NOT NULL,
  is_read TINYINT(1) DEFAULT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_message_sender_user_id FOREIGN KEY (sender_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_message_user_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 28
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы raiting_user
--
DROP TABLE IF EXISTS raiting_user;
CREATE TABLE raiting_user (
  id INT(11) NOT NULL AUTO_INCREMENT,
  estimator_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  mark INT(11) DEFAULT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_raiting_estimator_user_id FOREIGN KEY (estimator_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_raiting_user_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 108
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы service
--
DROP TABLE IF EXISTS service;
CREATE TABLE service (
  id INT(11) NOT NULL AUTO_INCREMENT,
  guide_id INT(11) NOT NULL,
  name VARCHAR(255) NOT NULL,
  price DOUBLE DEFAULT 0,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  description VARCHAR(1024) DEFAULT NULL,
  is_temporary INT(11) NOT NULL DEFAULT 0,
  deleted TINYINT(4) DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT FK_service_user_id FOREIGN KEY (guide_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 33
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user_language
--
DROP TABLE IF EXISTS user_language;
CREATE TABLE user_language (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  lang_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_user_language_language_id FOREIGN KEY (lang_id)
    REFERENCES language(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_user_language_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 16
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user_logining
--
DROP TABLE IF EXISTS user_logining;
CREATE TABLE user_logining (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_user_logining_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 348
AVG_ROW_LENGTH = 277
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user_tag
--
DROP TABLE IF EXISTS user_tag;
CREATE TABLE user_tag (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  tag_id INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_user_tag_tag_id FOREIGN KEY (tag_id)
    REFERENCES tag(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_user_tag_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 51
AVG_ROW_LENGTH = 3276
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы wallet
--
DROP TABLE IF EXISTS wallet;
CREATE TABLE wallet (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  balance INT(11) DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT FK_wallet_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 14
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы withdrawmoney
--
DROP TABLE IF EXISTS withdrawmoney;
CREATE TABLE withdrawmoney (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  money_amount DOUBLE DEFAULT NULL,
  withdraw_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_withdrawmoney_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 9
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы comment_event
--
DROP TABLE IF EXISTS comment_event;
CREATE TABLE comment_event (
  id INT(11) NOT NULL AUTO_INCREMENT,
  date DATETIME DEFAULT NULL,
  commentator_id INT(11) NOT NULL,
  event_id INT(11) NOT NULL,
  comment VARCHAR(512) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_comment_event_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_comment_event_user_id FOREIGN KEY (commentator_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 86
AVG_ROW_LENGTH = 4096
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы event_tag
--
DROP TABLE IF EXISTS event_tag;
CREATE TABLE event_tag (
  id INT(11) NOT NULL AUTO_INCREMENT,
  event_id INT(11) NOT NULL,
  tag_id INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_event_tag_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_event_tag_tag_id FOREIGN KEY (tag_id)
    REFERENCES tag(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 73
AVG_ROW_LENGTH = 409
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы eventpay
--
DROP TABLE IF EXISTS eventpay;
CREATE TABLE eventpay (
  id INT(11) NOT NULL AUTO_INCREMENT,
  from_user_id INT(11) DEFAULT NULL,
  to_user_id INT(11) DEFAULT NULL,
  sum INT(11) DEFAULT NULL,
  Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  event_id INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_eventpay_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_eventpay_user_id_from FOREIGN KEY (from_user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_eventpay_user_id_to FOREIGN KEY (to_user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы message_event
--
DROP TABLE IF EXISTS message_event;
CREATE TABLE message_event (
  id INT(11) NOT NULL AUTO_INCREMENT,
  sender_id INT(11) NOT NULL,
  event_id INT(11) NOT NULL,
  message VARCHAR(512) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_message_event_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_message_event_user_id FOREIGN KEY (sender_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 49
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы rating_event
--
DROP TABLE IF EXISTS rating_event;
CREATE TABLE rating_event (
  id INT(11) NOT NULL AUTO_INCREMENT,
  estimator_id INT(11) NOT NULL,
  event_id INT(11) NOT NULL,
  mark INT(11) NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_rating_event_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_rating_event_user_id FOREIGN KEY (estimator_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 12
AVG_ROW_LENGTH = 5461
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы service_in_event
--
DROP TABLE IF EXISTS service_in_event;
CREATE TABLE service_in_event (
  id INT(11) NOT NULL AUTO_INCREMENT,
  date_from DATETIME DEFAULT NULL,
  date_to DATETIME DEFAULT NULL,
  service_id INT(11) NOT NULL,
  event_id INT(11) NOT NULL,
  available_amount_of_positions INT(11) DEFAULT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted INT(11) NOT NULL DEFAULT 0,
  is_necessary_to_pay INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT FK_service_in_event_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_service_in_event_service_id FOREIGN KEY (service_id)
    REFERENCES service(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 21
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы user_in_event
--
DROP TABLE IF EXISTS user_in_event;
CREATE TABLE user_in_event (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  event_id INT(11) NOT NULL,
  status ENUM('resident','guest') DEFAULT NULL,
  bed_count INT(11) DEFAULT 0,
  food_count INT(11) DEFAULT 0,
  carplace_count INT(11) DEFAULT 0,
  is_member TINYINT(1) DEFAULT 0,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_user_in_event_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_user_in_event_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 137
AVG_ROW_LENGTH = 5461
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы walletupdate
--
DROP TABLE IF EXISTS walletupdate;
CREATE TABLE walletupdate (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) DEFAULT NULL,
  sum INT(11) DEFAULT NULL,
  transaction INT(11) DEFAULT NULL,
  date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  wallet_id INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_walletupdate_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_walletupdate_wallet_id FOREIGN KEY (wallet_id)
    REFERENCES wallet(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

--
-- Описание для таблицы paid_service
--
DROP TABLE IF EXISTS paid_service;
CREATE TABLE paid_service (
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_id INT(11) NOT NULL,
  event_id INT(11) NOT NULL,
  service_in_event_id INT(11) NOT NULL,
  paid_date TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_paid_service_event_id FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_paid_service_service_id FOREIGN KEY (service_in_event_id)
    REFERENCES service_in_event(id) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT FK_paid_service_user_id FOREIGN KEY (user_id)
    REFERENCES user(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
AUTO_INCREMENT = 52
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;

DELIMITER $$

--
-- Описание для процедуры confirmUnconfirmUser
--
DROP PROCEDURE IF EXISTS confirmUnconfirmUser$$
CREATE DEFINER = 'taras'@'localhost'
PROCEDURE confirmUnconfirmUser( userId int )
begin
DECLARE userTypeId int;
DECLARE newUserTypeId int;

 SELECT u.user_type_id FROM user u WHERE u.id = userId INTO userTypeId;
  IF (userTypeId = 7) THEN
     SET newUserTypeId = 3;
  ELSEIF (userTypeId = 3) THEN
     SET newUserTypeId = 7;
   ELSEIF (userTypeId = 2) THEN
     SET newUserTypeId = 8;
   ELSEIF (userTypeId = 8) THEN
     SET newUserTypeId = 2;
  END IF;

IF newUserTypeId <> 0 THEN
  UPDATE user u
    SET u.user_type_id = newUserTypeId
    WHERE u.id = userId;
  END IF;
end
$$

--
-- Описание для процедуры searchUser
--
DROP PROCEDURE IF EXISTS searchUser$$
CREATE DEFINER = 'root'@'localhost'
PROCEDURE searchUser(
  IN 
  user_id int, 
  fist_name varchar(255) CHARACTER SET utf8, 
  last_name varchar(255) CHARACTER SET utf8,
  country_name varchar(255) CHARACTER SET utf8, 
  city_name varchar(255) CHARACTER SET utf8,  
  tags varchar(255) CHARACTER SET utf8,
  userType varchar(255) CHARACTER SET utf8)
BEGIN


  SET @sqlGetUserByFName = CONCAT("SELECT u1.id FROM user u1 where  u1.first_name LIKE '%",fist_name,"%' OR u1.last_name LIKE '%",fist_name,"%'");

  SET @sqlGetUserByLName = CONCAT("SELECT u2.id FROM user u2 WHERE u2.last_name LIKE '%",last_name,"%' or u2.first_name LIKE '%",last_name,"%'");

    -- search sql by city
  SET @sqlGetByCityName = CONCAT("SELECT u3.id FROM user u3 
                                  JOIN address a ON u3.address_id = a.id  
                                  JOIN city c ON a.city_id = c.id WHERE c.id IN (    
                                  SELECT c1.id FROM city c1 WHERE c1.pure_id in ( 
                                  SELECT c2.pure_id FROM city c2 WHERE c2.name LIKE '%",city_name,"%' 
                                  GROUP BY a.pure_id))");
  -- search sql by country
  SET @sqlByCountryName = CONCAT("SELECT u8.id FROM user u8 
                                  JOIN address a ON u8.address_id = a.id  
                                  JOIN city ct ON a.city_id = ct.id 
                                  JOIN country c ON ct.country_id = c.id
                                  WHERE c.pure_id IN (SELECT c1.pure_id FROM country c1 
                                  where c1.name LIKE '%",country_name,"%') AND not c.deleted");

-- specify get non frieds, sent requests, received requests
  SET @sqlGetUserNonFriends = CONCAT("SELECT u4.id FROM user u4 WHERE (not u4.id=",user_id,") 
                                		 AND (u4.is_active=true)  AND (u4.id not in (
                                		 select fu.friend_id from friend_user fu WHERE fu.user_id =",user_id," AND NOT EXISTS (
                                		 SELECT fu1.friend_id FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)
                                		 union  SELECT fu.user_id from friend_user fu WHERE fu.friend_id = ",user_id," AND NOT EXISTS (
                                		 SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)
                                		 union  SELECT fu.friend_id from friend_user  fu WHERE fu.user_id = ",user_id," AND EXISTS (
                                		 SELECT * FROM friend_user fu1 WHERE fu.friend_id = fu1.user_id AND fu.user_id=fu1.friend_id)))");


-- specify query by tags
 IF tags='' THEN
  SET @sqlByTags = CONCAT("SELECT currentUser.id FROM user currentUser");
  ELSE 
  SET @sqlByTags = concat("select  currentUser.id FROM user  currentUser WHERE EXISTS( 
                                  SELECT ut.user_id , COUNT(*) AS tag_count 
                            		  FROM user_tag ut
                            		  JOIN user u ON ut.user_id = u.id JOIN tag t ON ut.tag_id = t.id
                            			  WHERE currentUser.id = ut.user_id and t.name  IN (",tags,")
                            		      GROUP BY currentUser.id
                            			    HAVING tag_count >= 1)");
  END IF;

-- specify query by usertyper
  CASE 
     WHEN userType = 'all' THEN
        SET @sqlByGdType = CONCAT("SELECT u5.id FROM user u5");
     WHEN userType = 'guide' THEN 
        SET @sqlByGdType = CONCAT("SELECT u5.id FROM user u5  JOIN user_type ut ON u5.user_type_id = ut.id 
			                                      WHERE ut.name = 'guide'");
     WHEN userType = 'user' THEN 
        SET @sqlByGdType = CONCAT("SELECT u5.id FROM user u5  JOIN user_type ut ON u5.user_type_id = ut.id 
			                                      WHERE ut.name = 'user'"); 
  END CASE;

-- building general sql  
  SET @sqlTotal = CONCAT("SELECT * FROM user u WHERE u.is_active=true 
                                AND (u.user_type_id = 2 OR u.user_type_id = 3) 
                                and u.id IN (",@sqlGetUserByFName,") 
                                AND u.id IN (",@sqlGetUserByLName,")
                                AND u.id IN (",@sqlByCountryName,")
                                AND u.id IN (",@sqlGetByCityName,")
                                AND u.id IN (",@sqlByTags,")
                                AND u.id IN (",@sqlByGdType,")
                                AND u.id IN (",@sqlGetUserNonFriends,")
                                ");
  PREPARE stmt FROM @sqlTotal;
  EXECUTE stmt;
  DEALLOCATE PREPARE stmt;

END
$$

--
-- Описание для процедуры userActivity
--
DROP PROCEDURE IF EXISTS userActivity$$
CREATE DEFINER = 'root'@'localhost'
PROCEDURE userActivity( AuserId int )
begin
    SELECT x.activity AS activity, 
           x.name AS NAME,
           X.act AS act,
           X.idAct AS idAct
      FROM(
  SELECT 'create event 'AS activity, 
          e.name AS NAME,
          'event' AS act,
          e.id AS idAct
    FROM event e
    WHERE e.moderator_id = AuserID
  UNION
  SELECT 'comment event'AS activity, 
         ce.comment AS NAME,
         'event' AS act,
          ce.event_id AS idAct
    FROM comment_event ce
  WHERE ce.commentator_id = AuserID
  UNION 
    SELECT 'comment user'AS activity, 
            cu.comment AS NAME,
            'user' AS act,
            cu.user_id AS idAct
      FROM comment_user cu
      WHERE cu.commentator_id = AuserId
      )AS X;
  SELECT 
    u.last_name AS lastName,
    u.first_name AS firstName,
    u.id AS id 
  FROM friend_user AS fu
    JOIN user u ON fu.friend_id = u.id
  WHERE fu.user_id  = AuserId;
end
$$

--
-- Описание для функции fc
--
DROP FUNCTION IF EXISTS fc$$
CREATE DEFINER = 'oleg_pc'@'localhost'
FUNCTION fc(valueSearch varchar(20))
  RETURNS varchar(20) CHARSET utf8
BEGIN

 set @t1 = ('cinema', 'music', 'camping', 'hiking');
  RETURN @t1;
END
$$

--
-- Описание для функции hello_world
--
DROP FUNCTION IF EXISTS hello_world$$
CREATE DEFINER = 'oleg_pc'@'localhost'
FUNCTION hello_world()
  RETURNS text CHARSET utf8
BEGIN
  RETURN 'Hello World';
END
$$

--
-- Описание для функции HTMLBody
--
DROP FUNCTION IF EXISTS HTMLBody$$
CREATE DEFINER = 'taras'@'localhost'
FUNCTION HTMLBody(Msg varchar(8192))
  RETURNS varchar(17408) CHARSET latin1
  DETERMINISTIC
BEGIN
  declare tmpMsg varchar(17408);
  set tmpMsg = cast(concat(
      'Date: ',date_format(NOW(),'%e %b %Y %H:%i:%S -0600'),'\r\n',
      'MIME-Version: 1.0','\r\n',
      'Content-Type: multipart/alternative;','\r\n',
      ' boundary=\"----=_NextPart_000_0000_01CA4B3F.8C263EE0\"','\r\n',
      'Content-Class: urn:content-classes:message','\r\n',
      'Importance: normal','\r\n',
      'Priority: normal','\r\n','','\r\n','','\r\n',
      'This is a multi-part message in MIME format.','\r\n','','\r\n',
      '------=_NextPart_000_0000_01CA4B3F.8C263EE0','\r\n',
      'Content-Type: text/plain;','\r\n',
      '  charset=\"iso-8859-1\"','\r\n',
      'Content-Transfer-Encoding: 7bit','\r\n','','\r\n','','\r\n',
      Msg,
      '\r\n','','\r\n','','\r\n',
      '------=_NextPart_000_0000_01CA4B3F.8C263EE0','\r\n',
      'Content-Type: text/html','\r\n',
      'Content-Transfer-Encoding: 7bit','\r\n','','\r\n',
      Msg,
      '\r\n','------=_NextPart_000_0000_01CA4B3F.8C263EE0--'
      ) as char);
  RETURN tmpMsg;
END
$$

--
-- Описание для функции IncomeLevel
--
DROP FUNCTION IF EXISTS IncomeLevel$$
CREATE DEFINER = 'oleg_pc'@'localhost'
FUNCTION IncomeLevel(valueSearch varchar(20))
  RETURNS varchar(20) CHARSET utf8
BEGIN
 set @t1 = ('cinema', 'music', 'camping', 'hiking');
  RETURN @t1;
END
$$

DELIMITER ;

-- 
-- Вывод данных для таблицы language
--
INSERT INTO language VALUES
(2, 'UKR', 'Українська', 1, 0, 'ua_UA'),
(3, 'GBR', 'English', 1, 0, 'en_US'),
(4, 'RUS', 'Русский', 0, 0, 'ru_RU'),
(5, 'FRA', 'Français', 0, 0, 'fr_FR'),
(6, 'DEU', 'Deutsch', 0, 0, 'de_DE'),
(7, 'ESP', 'Español', 0, 0, 'es_ES'),
(8, 'POL', 'Polski', 0, 0, 'pl_PL'),
(9, 'ch', 'chine', 0, 0, NULL);

-- 
-- Вывод данных для таблицы photo
--
INSERT INTO photo VALUES
(2, 'img/userphotos/elonmask.jpg', 'user', 8, '0000-00-00 00:00:00'),
(3, 'img/eventphotos/kryovka.jpg', 'event', 1, '0000-00-00 00:00:00'),
(4, 'img/eventphotos/opera-house.jpg', 'event', 2, '0000-00-00 00:00:00'),
(5, 'img/eventphotos/royal-casle.jpg', 'event', 3, '0000-00-00 00:00:00'),
(6, 'img/eventphotos/fashion-club.jpg', 'event', 4, '0000-00-00 00:00:00'),
(7, 'img/eventphotos/zahid-fest.jpg', 'event', 5, '0000-00-00 00:00:00'),
(8, 'img/userphotos/johnbonjovi.jpg', 'user', 11, '2015-06-19 05:51:36'),
(9, 'img/userphotos/billgates.jpg', 'user', 12, '2015-06-19 05:52:33'),
(10, 'img/userphotos/unknownuserphoto.png', 'user', -1, '2015-06-19 10:01:59'),
(11, 'img/eventphotos/unknowneventphoto.jpg', 'event', -1, '2015-06-19 10:03:43'),
(12, 'img/eventphotos/150thWeeklyMonday.jpg', 'event', 6, '2015-06-23 02:17:26'),
(14, 'img/eventphotos/Banana_Boat_Party.jpg', 'event', 6, '2015-06-23 08:20:35'),
(15, 'img/eventphotos/Barbecue_by_Brisbane.png', 'event', 9, '2015-06-23 08:37:45'),
(16, 'img/eventphotos/BedBreakslowClub.jpg', 'event', 10, '2015-06-23 08:49:50'),
(17, 'img/eventphotos/f047336e42e8700f470726c0214e5e55.jpg', 'event', 11, '2015-06-23 09:17:17'),
(18, 'img/eventphotos/f047336e42e870.jpg', 'event', 12, '2015-06-23 09:25:45'),
(19, 'img/eventphotos/65464646465.jpg', 'event', 13, '2015-06-23 09:39:53'),
(20, 'img/eventphotos/bb55.jpg', 'event', 14, '2015-06-23 09:54:31'),
(22, 'img/eventphotos/1a3d319da6e0.jpg', 'event', 15, '2015-06-23 10:07:35'),
(23, 'img/eventphotos/3b566b823.jpg', 'event', 16, '2015-06-23 10:16:12'),
(24, 'img/eventphotos/8c91291.jpg', 'event', 17, '2015-06-23 10:25:43'),
(25, 'img/eventphotos/323a6bfd7.jpg', 'event', 18, '2015-06-23 10:34:15'),
(26, 'img/eventphotos/963f3197.jpg', 'event', 19, '2015-06-23 10:42:39'),
(27, 'img/eventphotos/49a1d721.jpg', 'event', 20, '2015-06-23 10:48:37'),
(28, 'img/eventphotos/bb67abbe.jpg', 'event', 21, '2015-06-23 10:53:53'),
(29, 'img/eventphotos/c313a07.jpg', 'event', 22, '2015-06-23 11:11:27'),
(30, 'img/eventphotos/605450bb1.jpg', 'event', 23, '2015-06-23 11:20:27'),
(31, 'img/eventphotos/611f35cd031.jpg', 'event', 24, '2015-06-23 11:28:13'),
(32, 'img/eventphotos/b36aeba6c.jpg', 'event', 25, '2015-06-23 11:34:36'),
(33, 'img/eventphotos/867b503.jpg', 'event', 26, '2015-06-23 12:02:16'),
(34, 'img/eventphotos/6291e496.jpg', 'event', 27, '2015-06-23 12:09:51'),
(35, 'img/eventphotos/ec38331cb.jpg', 'event', 28, '2015-06-23 12:17:10'),
(36, 'img/eventphotos/737522ec66c.jpg', 'event', 29, '2015-06-23 13:02:28'),
(37, 'img/eventphotos/93b346d4.jpg', 'event', 30, '2015-06-23 13:06:33'),
(38, 'img/eventphotos/36572da0.jpg', 'event', 31, '2015-06-23 13:30:19'),
(39, 'img/eventphotos/0c6070bc6e.jpg', 'event', 32, '2015-06-23 13:38:39'),
(40, 'img/eventphotos/ef29f0f4e1.jpg', 'event', 33, '2015-06-23 13:48:47'),
(41, 'img/eventphotos/25a1b0.jpg', 'event', 34, '2015-06-23 13:58:18'),
(42, 'img/eventphotos/0f47e7b12.jpg', 'event', 35, '2015-06-23 14:11:55'),
(44, 'img/eventphotos/46796e88.jpg', 'event', 36, '2015-06-23 15:00:19'),
(45, 'img/eventphotos/81857.jpg', 'event', 37, '2015-06-23 15:08:31'),
(46, 'img/eventphotos/19f30d98-6.jpg', 'event', 38, '2015-06-23 15:12:11'),
(47, 'img/eventphotos/5f6b7808.jpg', 'event', 39, '2015-06-23 15:16:10'),
(48, 'img/eventphotos/90e553.jpg', 'event', 40, '2015-06-23 15:19:21'),
(49, 'img/eventphotos/19f9a70.jpg', 'event', 41, '2015-06-23 15:22:16'),
(51, 'img/eventphotos/c932bc1.jpg', 'event', 43, '2015-06-23 15:44:53'),
(52, 'img/eventphotos/3b2002e.jpg', 'event', 44, '2015-06-23 15:55:07'),
(53, 'img/eventphotos/449c3d.jpg', 'event', 45, '2015-06-23 16:01:20'),
(54, 'img/eventphotos/8e58f638.jpg', 'event', 46, '2015-06-23 16:13:01'),
(55, 'img/eventphotos/1c4c9e0.jpg', 'event', 47, '2015-06-23 16:22:26'),
(56, 'img/eventphotos/09c6641.jpg', 'event', 48, '2015-06-23 16:29:48'),
(57, 'img/eventphotos/fd0c18d8.jpg', 'event', 49, '2015-06-23 16:40:43'),
(58, 'img/eventphotos/long_ArticlePortletImage_19472.JPG', 'event', 50, '2015-06-23 16:59:40'),
(60, 'img/userphotos/610-e-musk-1.jpg', 'user', 8, '2015-06-28 17:03:55'),
(61, 'img/userphotos/elon-musk-65456.jpg', 'user', 8, '2015-06-28 17:04:13'),
(62, 'img/userphotos/elonmusk744x1128.jpg', 'user', 8, '2015-06-28 17:04:31'),
(63, 'img/userphotos/elon-musk-new.jpg', 'user', 8, '2015-06-28 17:05:06'),
(65, 'img/userphotos/maxresdefault.jpg', 'user', 8, '2015-06-28 17:05:50'),
(68, 'img/eventphotos/zahid-fest-01.jpg', 'event', 5, '2015-06-28 22:26:49'),
(69, 'img/eventphotos/zahid-fest-02.jpg', 'event', 5, '2015-06-28 22:26:49'),
(70, 'img/eventphotos/zahid-fest-03.jpg', 'event', 5, '2015-06-28 22:26:49'),
(71, 'img/eventphotos/zahid-fest-04.jpg', 'event', 5, '2015-06-28 22:26:49'),
(72, 'img/eventphotos/zahid-fest-05.jpg', 'event', 5, '2015-06-28 22:26:49'),
(73, 'img/eventphotos/zahid-fest-06.jpg', 'event', 5, '2015-06-28 22:26:49'),
(74, 'img/eventphotos/zahid-fest-07.jpg', 'event', 5, '2015-06-28 22:26:49'),
(75, 'img/eventphotos/zahid-fest-08.jpg', 'event', 5, '2015-06-28 22:26:49'),
(76, 'img/eventphotos/zahid-fest-09.jpg', 'event', 5, '2015-06-28 22:26:49'),
(77, 'img/eventphotos/zahid-fest-10.jpg', 'event', 5, '2015-06-28 22:26:49'),
(78, 'img/eventphotos/zahid-fest-11.jpg', 'event', 5, '2015-06-28 22:26:49'),
(79, 'img/eventphotos/zahid-fest-12.jpg', 'event', 5, '2015-06-28 22:26:49'),
(80, 'img/eventphotos/zahid-fest-13.jpg', 'event', 5, '2015-06-28 22:26:49'),
(81, 'img/eventphotos/zahid-fest-14.jpg', 'event', 5, '2015-06-28 22:26:49'),
(82, 'img/eventphotos/zahid-fest-15.jpg', 'event', 5, '2015-06-28 22:26:49'),
(83, 'img/eventphotos/zahid-fest-16.jpg', 'event', 5, '2015-06-28 22:26:49'),
(84, 'img/eventphotos/zahid-fest-17.jpg', 'event', 5, '2015-06-28 22:26:49'),
(85, 'img/eventphotos/zahid-fest-18.jpg', 'event', 5, '2015-06-28 22:26:49'),
(86, 'img/eventphotos/zahid-fest-19.jpg', 'event', 5, '2015-06-28 22:26:49'),
(87, 'img/eventphotos/zahid-fest-20.jpg', 'event', 5, '2015-06-28 22:26:49'),
(95, 'img/eventphotos/images.jpg', 'event', 50, '2015-07-15 12:59:58'),
(96, 'img/userphotos/klava.jpg', 'user', 28, '2015-07-15 13:02:32'),
(97, 'img/userphotos/reks.jpg', 'user', 2, '2015-07-16 01:48:13'),
(98, 'img/userphotos/reks1.jpg', 'user', 2, '2015-07-16 01:51:31'),
(101, 'img/userphotos/ahmud.jpg', 'user', 20, '2015-07-16 01:52:50'),
(102, 'img/userphotos/chris.jpg', 'user', 24, '2015-07-16 01:53:27'),
(103, 'img/userphotos/black.jpg', 'user', 17, '2015-07-16 01:54:33'),
(104, 'img/userphotos/beks.jpg', 'user', 18, '2015-07-16 01:55:03'),
(105, 'img/userphotos/smith.jpg', 'user', 27, '2015-07-16 01:57:36'),
(106, 'img/userphotos/michael.jpg', 'user', 4, '2015-07-16 01:58:45'),
(107, 'img/userphotos/billi.jpg', 'user', 5, '2015-07-16 01:59:36'),
(108, 'img/userphotos/adriana.jpg', 'user', 21, '2015-07-16 02:08:00'),
(109, 'img/userphotos/boops.jpg', 'user', 6, '2015-07-16 02:08:34'),
(110, 'img/userphotos/diana.jpg', 'user', 22, '2015-07-16 02:09:04'),
(111, 'img/userphotos/pamela.jpg', 'user', 32, '2015-07-16 02:09:35'),
(112, 'img/userphotos/sarah.jpg', 'user', 25, '2015-07-16 02:10:05'),
(114, 'img/userphotos/smile.jpg', 'user', 1, '2015-07-16 03:00:55'),
(115, 'img/userphotos/veryCoo.jpg', 'user', 13, '2015-07-16 03:02:03'),
(116, 'img/userphotos/man4.jpg', 'user', 3, '2015-07-16 03:02:43'),
(117, 'img/userphotos/man1.jpg', 'user', 14, '2015-07-16 03:03:19'),
(118, 'img/userphotos/fun_man.png', 'user', 19, '2015-07-16 03:04:48'),
(119, 'img/userphotos/divka1.jpg', 'user', 40, '2015-07-16 03:06:07'),
(120, 'img/userphotos/man3.jpg', 'user', 42, '2015-07-16 03:06:43'),
(121, 'img/userphotos/mrbin.jpg', 'user', 43, '2015-07-16 03:07:42'),
(122, 'img/userphotos/man2.jpg', 'user', 41, '2015-07-16 03:08:17'),
(123, 'img/userphotos/divka2.jpg', 'user', 26, '2015-07-16 03:09:37'),
(124, 'img/userphotos/happylife.jpg', 'user', 35, '2015-07-16 03:12:22'),
(141, 'img/eventphotos/jhj.jpg', 'event', 5, '2015-07-27 21:22:48'),
(142, 'img/userphotos/tumblr_m5kw3eQRhE1rot7zyo1_1280.jpg', 'user', 26, '2015-08-02 15:09:25'),
(143, 'img/userphotos/laura-palmer.jpg', 'user', 26, '2015-08-02 15:09:25'),
(144, 'img/userphotos/laura-palmer-point.jpg', 'user', 26, '2015-08-02 15:09:25'),
(145, 'img/userphotos/181.jpg', 'user', 24, '2015-08-02 15:19:09'),
(146, 'img/userphotos/s16516.jpg', 'user', 24, '2015-08-02 15:19:09'),
(147, 'img/userphotos/18188.jpg', 'user', 24, '2015-08-02 15:19:09'),
(148, 'img/userphotos/064642.jpg', 'user', 24, '2015-08-02 15:19:09'),
(149, 'img/eventphotos/rfeggg.jpg', 'event', 42, '2015-08-02 15:58:57'),
(150, 'img/eventphotos/eded.jpg', 'event', 42, '2015-08-02 15:58:57'),
(151, 'img/eventphotos/vvrvvrv.jpg', 'event', 42, '2015-08-02 15:58:57'),
(152, 'img/eventphotos/xexrf.jpg', 'event', 42, '2015-08-02 15:58:57'),
(153, 'img/eventphotos/vrvrv.jpg', 'event', 42, '2015-08-02 15:58:57');

-- 
-- Вывод данных для таблицы tag
--
INSERT INTO tag VALUES
(1, 'Music', 0, '0000-00-00 00:00:00'),
(2, 'Cinema', 0, '0000-00-00 00:00:00'),
(3, 'Tourism', 0, '0000-00-00 00:00:00'),
(4, 'Festival', 0, '0000-00-00 00:00:00'),
(5, 'Nature', 0, '0000-00-00 00:00:00'),
(6, 'Sport', 0, '0000-00-00 00:00:00'),
(7, 'Cycling', 0, '0000-00-00 00:00:00'),
(8, 'Hiking', 0, '0000-00-00 00:00:00'),
(9, 'Camping', 0, '0000-00-00 00:00:00'),
(10, 'Languages', 1, '2015-06-23 17:05:25'),
(11, 'Trip', 1, '2015-06-23 17:05:33'),
(12, 'Concert', 1, '2015-06-23 17:05:52'),
(13, 'Cafe', 0, '2015-06-23 17:07:09'),
(14, 'BBQ', 0, '2015-06-23 17:07:14'),
(15, 'Alcohol', 0, '2015-06-23 17:09:06'),
(16, 'Theather', 0, '2015-06-23 17:11:14'),
(17, 'Culture', 0, '2015-06-23 17:11:30'),
(18, 'Dance', 0, '2015-06-23 17:15:51');

-- 
-- Вывод данных для таблицы user_forgot_password
--
INSERT INTO user_forgot_password VALUES
(2, 'emai', 'daa', '2015-06-19 15:00:54', 1),
(8, 'ss', 'saa', '2015-06-19 17:33:18', 0),
(9, 'email', 'bee', '2015-06-19 18:07:52', 0),
(10, 'horodetskyyv@gmail.com', '4a8e4104a720d1b29ffae9281d5d467efa1d7c90c38e99aac2cfe19a0b48c6fb', '2015-06-19 19:19:12', 0),
(11, 'horodetskyyv@gmail.com', 'a896ca1542d983f0b3d6083e3f2c2a6abd6f72e63381187f99c8cb9b011d427b', '2015-06-19 19:19:13', 0),
(12, 'horodetskyyv@gmail.com', 'cec11753cc274e7c459169adafaeb843147332ba6726090c541bd5bbaa05e441', '2015-06-19 19:19:55', 0),
(13, 'horodetskyyv@gmail.com', '63b9a8e5407950f42b3d3eb72bd6f952e1e0e1b6568ffe6a929a9b3c2dc531aa', '2015-06-19 19:20:11', 0),
(14, 'horodetskyyv@gmail.com', 'e104cdaeeb1cb69b741d7dd7420e2004c503292c4538a7d28e92d4129a025b17', '2015-06-19 19:20:11', 0),
(15, 'horodetskyyv@gmail.com', '84053794e68992f48ab0fac700c41963584ef7904a259162b30d44b1d72e2945', '2015-06-19 19:20:12', 0),
(16, 'horodetskyyv@gmail.com', '67133a82962b447bedededb5e505526b2793b1e0d3453a0629aead64883cc933', '2015-06-19 19:20:12', 0),
(17, 'horodetskyyv@gmail.com', '919b7a52b1034d94333112eb5ede6264d9a7cf23dd9566b36e395f3e36b54b90', '2015-06-19 19:20:12', 0),
(18, 'horodetskyyv@gmail.com', '49d767cb055d9c24aef670671052ba6ceb1c2eec98e623eacc9d25cecd126fdb', '2015-06-19 19:20:13', 0),
(19, 'horodetskyyv@gmail.com', '507e51669e9c9fe98c8b62f775966e0230a0f13f1bf74289e27f5c069148229d', '2015-06-19 19:20:13', 0),
(20, 'horodetskyyv@gmail.com', '1b49f3d799f44c9ea936a77cd8b4b6975b9ab424bed88a99d7833e900bd74e3d', '2015-06-19 20:12:30', 0),
(21, 'neutron80@list.ru', '6890ef946ecf5c960760cd4b590a248daaa33806d6b0b76ca769ac487c1f4410', '2015-06-23 18:02:09', 0),
(22, 'neutron80@list.ru', 'b9c797150883a688180a0c324e9f19e7a05385e3fa365765eabcb929e6f13c46', '2015-06-23 18:05:59', 1),
(23, 'neutron80@list.ru', '4838c5656f7b12fd6b609589fa1af6e00dad4d65b90554d12dbf87fcfb0f38db', '2015-06-23 18:06:08', 1),
(24, 'horodetskyyv@gmail.com', '30fb213a55949dd6ffb15598709af9e1462a3091309ff47815875bbb6dca7a55', '2015-06-23 18:08:02', 0),
(25, 'horodetskyyv@gmail.com', '009babfdee0600eda11598311f3eea2c37bf305e332c0bf6af3d271f389db28e', '2015-06-24 10:55:54', 0),
(26, 'tgrynchuk@gmail.com', '82888ca4ff5d2073b359be2da67d91c3a2d69403726611a5ed6add391ba7a584', '2015-07-16 11:19:44', 0),
(27, 'tgrynchuk@gmail.com', '43b3bf4e7a9656e3c3860f02c88703e60731370bba08c9cfe78ba722d17c19b6', '2015-07-16 11:21:22', 1),
(28, 'horodetskyyv@gmail.com', '3061dbc506b2400bdf733099bdf46c64f132758177a4cc52903ae0d838700a5d', '2015-07-16 11:29:51', 1),
(29, 'tgrynchuk@gmail.com', 'a066dab112aced9ef4dae263922ee2d5afe6fc45ff770b1a9d9e791992b19fe4', '2015-07-16 11:30:06', 1),
(30, 'tgrynchuk@gmail.com', '7c22f37e4b51511dcf9b04504ac5c864f3c0b93c233c0c97deef04a32653c2b9', '2015-07-16 11:34:46', 0),
(31, 'neutron80@list.ru', '787df8a44ee53b1a2a21f5453eb7c21b6865d393563126134e8776e0f688f4b9', '2015-07-16 11:37:29', 1),
(32, 'neutron80@list.ru', '32c39ba20ddae67b400ed8724e57fe23b03c1a721cd15400fca7ec50e9ed0932', '2015-07-16 11:38:41', 1),
(33, 'tgrynchuk@gmail.com', '2911e0dbc1778125449357253bd328c87aca0fba27acdbee09699fa23e97428c', '2015-07-27 13:59:39', 0),
(34, 'tgrynchuk@gmail.com', 'a3b0fe07faf9aeb2f2b0301051a44f3b638357e796788a544cc9dcba917beebc', '2015-07-27 14:26:45', 1),
(35, 'tgrynchuk@gmail.com', '6d0190cb4d13c556ce76b15d2ad68b5b8b22085cc0b393767156515e2b115c75', '2015-07-27 14:27:27', 1),
(36, 'tgrynchuk@yahoo.com', '03695d68d224816d1bf0789b2f2cc168fac8867c441b304c5af8d630fa0a46c7', '2015-07-27 14:37:00', 1),
(37, 'tgrynchuk@yahoo.com', '2c0dd16a8d76a5f16a5c472281f6586ac7d8c81b4d9161e17dfb5778c3086022', '2015-07-27 14:46:33', 1),
(38, 'tgrynchuk@gmail.com', '26055eb8230ada42739b47b383fe4999e0d1a9f87bbef2797fbc159b574f429a', '2015-07-27 14:50:48', 1),
(39, 'tgrynchuk@gmail.com', '573ea5dfb00aeb6094c62a43c4eeb9152cec713d7b1e97ed3906b9628dace9de', '2015-07-27 14:51:12', 1),
(40, 'tgrynchuk@gmail.com', '5004bde5acf9b45e35d8f4a21d18b5ec828b8d7482b3672af998feba7bb3cc78', '2015-07-27 14:54:20', 1),
(41, 'tgrynchuk@gmail.com', '9d523864f1c7e1b8183a37e29e6b105e669f04486ec5d7dbccf3d11d9043119b', '2015-07-27 14:55:40', 0);

-- 
-- Вывод данных для таблицы user_type
--
INSERT INTO user_type VALUES
(1, 'admin'),
(3, 'guide'),
(4, 'manager'),
(7, 'unconfirmed_guide'),
(8, 'unconfirmed_user'),
(2, 'user');

-- 
-- Вывод данных для таблицы country
--
INSERT INTO country VALUES
(1, 1, 3, 'Ukraine', 0),
(2, 2, 3, 'Polland', 0),
(3, 1, 2, 'Україна', 0),
(4, 2, 2, 'Польща', 0),
(5, 1, 4, 'Украина', 0),
(6, 2, 4, 'Польша', 0),
(7, 3, 3, 'Germany', 0),
(8, 3, 2, 'Німеччина', 0),
(9, 3, 4, 'Германия', 0),
(10, 4, 3, 'Australia', 0),
(11, 4, 2, 'Австралія', 0),
(12, 4, 4, 'Австралия', 0),
(13, 5, 3, 'Sweden', 0),
(14, 5, 2, 'Швеція', 0),
(15, 5, 4, 'Швеция', 0),
(16, 6, 3, 'USA', 0),
(17, 6, 2, 'США', 0),
(18, 6, 4, 'США', 0);

-- 
-- Вывод данных для таблицы city
--
INSERT INTO city VALUES
(1, 1, 3, 'Lviv', 1, '0000-00-00 00:00:00', 0, 49.83968, 24.02971),
(2, 2, 3, 'Kyiv', 1, '0000-00-00 00:00:00', 0, NULL, NULL),
(3, 3, 3, 'Warsaw', 2, '0000-00-00 00:00:00', 0, NULL, NULL),
(4, 4, 3, 'Krakow', 2, '0000-00-00 00:00:00', 0, NULL, NULL),
(5, 1, 2, 'Львів', 3, '0000-00-00 00:00:00', 0, 49.83968, 24.02971),
(6, 2, 2, 'Київ', 3, '0000-00-00 00:00:00', 0, NULL, NULL),
(7, 3, 2, 'Варшава', 4, '0000-00-00 00:00:00', 0, NULL, NULL),
(8, 4, 2, 'Краків', 4, '0000-00-00 00:00:00', 0, NULL, NULL),
(9, 1, 4, 'Львов', 5, '0000-00-00 00:00:00', 0, 49.83968, 24.02971),
(10, 2, 4, 'Киев', 5, '0000-00-00 00:00:00', 0, NULL, NULL),
(11, 3, 4, 'Варшава', 6, '0000-00-00 00:00:00', 0, NULL, NULL),
(12, 4, 4, 'Краков', 6, '0000-00-00 00:00:00', 0, NULL, NULL),
(13, 5, 3, 'Hamburg', 7, '2015-06-23 01:36:43', 0, NULL, NULL),
(14, 5, 2, 'Гамбург', 8, '2015-06-23 01:37:12', 0, NULL, NULL),
(15, 5, 4, 'Гамбург', 9, '2015-06-23 01:38:09', 0, NULL, NULL),
(16, 6, 3, 'Berlin', 7, '2015-06-23 09:05:16', 0, NULL, NULL),
(17, 6, 2, 'Берлін', 8, '2015-06-23 09:05:17', 0, NULL, NULL),
(18, 6, 4, 'Берлин', 9, '2015-06-23 09:05:17', 0, NULL, NULL),
(19, 7, 3, 'Duesseldorf', 7, '2015-06-23 09:44:55', 0, NULL, NULL),
(20, 7, 2, 'Дюссельдорф', 8, '2015-06-23 09:44:55', 0, NULL, NULL),
(21, 7, 4, 'Дюссельдорф', 9, '2015-06-23 09:44:55', 0, NULL, NULL),
(22, 8, 3, 'Sydney', 10, '2015-06-23 11:01:12', 0, NULL, NULL),
(23, 8, 2, 'Сідней', 11, '2015-06-23 11:01:49', 0, NULL, NULL),
(24, 8, 4, 'Сидней', 12, '2015-06-23 11:02:14', 0, NULL, NULL),
(25, 9, 3, 'Melbourne', 10, '2015-06-23 11:52:56', 0, NULL, NULL),
(26, 9, 2, 'Мельборн', 11, '2015-06-23 11:53:31', 0, NULL, NULL),
(27, 9, 4, 'Мельборн', 12, '2015-06-23 11:53:51', 0, NULL, NULL),
(28, 10, 3, 'Stockholm', 13, '2015-06-23 13:17:28', 0, NULL, NULL),
(29, 10, 2, 'Стокгольм', 14, '2015-06-23 13:18:10', 0, NULL, NULL),
(30, 10, 4, 'Стокгольм', 15, '2015-06-23 13:18:35', 0, NULL, NULL),
(31, 11, 3, 'Los Angeles', 16, '2015-06-23 14:21:49', 0, NULL, NULL),
(32, 11, 2, 'Лос Анджелес', 17, '2015-06-23 14:22:49', 0, NULL, NULL),
(33, 11, 4, 'Лос Анджелес', 18, '2015-06-23 14:23:10', 0, NULL, NULL),
(34, 12, 3, 'San Francisco', 16, '2015-06-23 15:39:01', 0, NULL, NULL),
(35, 12, 2, 'Сан Франциско', 17, '2015-06-23 15:39:35', 0, NULL, NULL),
(36, 12, 4, 'Сан Франциско', 18, '2015-06-23 15:39:59', 0, NULL, NULL),
(37, 13, 3, 'Las Vegas', 16, '2015-06-23 15:50:49', 0, NULL, NULL),
(38, 13, 2, 'Лас Вегас', 17, '2015-06-23 15:50:49', 0, NULL, NULL),
(39, 13, 4, 'Лас Вегас', 18, '2015-06-23 15:50:49', 0, NULL, NULL),
(40, 14, 3, ' San Leandro', 16, '2015-06-23 16:25:54', 0, NULL, NULL),
(41, 14, 2, 'Сан Леандро', 17, '2015-06-23 16:26:31', 0, NULL, NULL),
(42, 14, 4, 'Сан Леандро', 18, '2015-06-23 16:27:00', 0, NULL, NULL),
(43, 15, 2, 'Харків', 3, '2015-08-02 22:13:34', 0, NULL, NULL),
(44, 15, 3, 'Kharkiv', 1, '2015-08-02 22:13:34', 0, NULL, NULL);

-- 
-- Вывод данных для таблицы address
--
INSERT INTO address VALUES
(1, 1, 2, 'вулиця Калнишевського 12', 5, '0000-00-00 00:00:00', NULL, NULL),
(2, 2, 2, 'вулиця Степана Бандери', 5, '0000-00-00 00:00:00', NULL, NULL),
(3, 3, 2, 'Міжнародна 2', 7, '0000-00-00 00:00:00', NULL, NULL),
(4, 4, 2, 'вулиця Хрещатик 12', 6, '0000-00-00 00:00:00', NULL, NULL),
(5, 5, 2, 'вулиця Мельникова 5', 6, '0000-00-00 00:00:00', NULL, NULL),
(6, 6, 2, 'площа Ринок 14', 5, '0000-00-00 00:00:00', NULL, NULL),
(7, 7, 2, 'проспект Свободи 28', 5, '0000-00-00 00:00:00', 49.844003, 24.026193),
(8, 8, 3, 'plac Zamkowy 4', 3, '0000-00-00 00:00:00', NULL, NULL),
(9, 9, 2, 'Pidkovy square, 1', 1, '0000-00-00 00:00:00', NULL, NULL),
(10, 1, 3, 'Kalnishevskogo str 12', 1, '0000-00-00 00:00:00', NULL, NULL),
(11, 2, 3, 'Stepan Bandera street', 1, '0000-00-00 00:00:00', NULL, NULL),
(12, 3, 3, 'Mijnarodna street 2', 3, '0000-00-00 00:00:00', NULL, NULL),
(13, 4, 3, 'Hreshchatyk 12', 2, '0000-00-00 00:00:00', NULL, NULL),
(14, 5, 3, 'Melnykova 5', 2, '0000-00-00 00:00:00', NULL, NULL),
(15, 10, 3, 'dsf', 1, '2015-06-19 10:11:24', NULL, NULL),
(16, 10, 2, 'dfhjkl', 5, '2015-06-19 10:11:25', NULL, NULL),
(17, 10, 4, 'dsf', 9, '2015-06-19 10:11:25', NULL, NULL),
(18, 11, 3, 'dsf', 1, '2015-06-19 10:11:30', NULL, NULL),
(19, 11, 2, 'dfhjkl', 5, '2015-06-19 10:11:30', NULL, NULL),
(20, 11, 4, 'dsf', 9, '2015-06-19 10:11:30', NULL, NULL),
(21, 12, 3, 'dsf', 1, '2015-06-19 10:14:37', NULL, NULL),
(22, 12, 2, 'dfhjkl', 5, '2015-06-19 10:14:37', NULL, NULL),
(23, 12, 4, 'dsf', 9, '2015-06-19 10:14:37', NULL, NULL),
(24, 13, 3, 'dsf', 1, '2015-06-19 10:15:12', NULL, NULL),
(25, 13, 2, 'dfhjkl', 5, '2015-06-19 10:15:12', NULL, NULL),
(26, 13, 4, 'dsf', 9, '2015-06-19 10:15:12', NULL, NULL),
(27, 14, 3, 'dsf', 1, '2015-06-19 10:16:49', NULL, NULL),
(28, 14, 2, 'dfhjkl', 5, '2015-06-19 10:16:49', NULL, NULL),
(29, 14, 4, 'dsf', 9, '2015-06-19 10:16:49', NULL, NULL),
(30, 15, 3, 'dsf', 1, '2015-06-19 10:18:52', NULL, NULL),
(31, 15, 2, 'dfhjkl', 5, '2015-06-19 10:18:52', NULL, NULL),
(32, 15, 4, 'dsf', 9, '2015-06-19 10:18:52', NULL, NULL),
(33, 16, 3, 'dsf', 1, '2015-06-19 10:35:22', NULL, NULL),
(34, 16, 2, 'dfhjkl', 5, '2015-06-19 10:35:22', NULL, NULL),
(35, 16, 4, 'dsf', 9, '2015-06-19 10:35:22', NULL, NULL),
(36, 17, 3, 'safd', 1, '2015-06-19 10:39:42', NULL, NULL),
(37, 17, 2, 'xzc', 5, '2015-06-19 10:39:42', NULL, NULL),
(38, 17, 4, 'asd', 9, '2015-06-19 10:39:42', NULL, NULL),
(39, 18, 3, 'd', 1, '2015-06-19 10:50:32', NULL, NULL),
(40, 18, 2, 'dfg', 5, '2015-06-19 10:50:33', NULL, NULL),
(41, 18, 4, 'd', 9, '2015-06-19 10:50:33', NULL, NULL),
(42, 19, 3, ' St. Pauli 5', 13, '2015-06-23 01:42:15', NULL, NULL),
(43, 20, 3, 'St. Pauli 18', 13, '2015-06-23 02:10:51', NULL, NULL),
(44, 20, 2, '', 14, '2015-06-23 02:10:52', NULL, NULL),
(45, 20, 4, '', 15, '2015-06-23 02:10:52', NULL, NULL),
(46, 21, 3, '', 2, '2015-06-23 08:01:51', NULL, NULL),
(47, 21, 2, 'fdfdfdf', 6, '2015-06-23 08:01:52', NULL, NULL),
(48, 21, 4, '', 10, '2015-06-23 08:01:52', NULL, NULL),
(49, 22, 3, 'Posnaki', 2, '2015-06-23 08:13:54', NULL, NULL),
(50, 22, 2, 'Позняки', 6, '2015-06-23 08:13:54', NULL, NULL),
(51, 22, 4, '', 10, '2015-06-23 08:13:54', NULL, NULL),
(52, 23, 3, 'St. Pauli 18', 13, '2015-06-23 08:26:17', NULL, NULL),
(53, 23, 2, '', 14, '2015-06-23 08:26:17', NULL, NULL),
(54, 23, 4, '', 15, '2015-06-23 08:26:17', NULL, NULL),
(55, 24, 3, '', 13, '2015-06-23 08:31:10', NULL, NULL),
(56, 24, 2, 'St.Pauli 18', 14, '2015-06-23 08:31:10', NULL, NULL),
(57, 24, 4, '', 15, '2015-06-23 08:31:10', NULL, NULL),
(58, 25, 3, 'St. Pauli 18', 13, '2015-06-23 08:34:06', NULL, NULL),
(59, 25, 2, '', 14, '2015-06-23 08:34:06', NULL, NULL),
(60, 25, 4, '', 15, '2015-06-23 08:34:06', NULL, NULL),
(61, 26, 3, '', 13, '2015-06-23 08:41:12', NULL, NULL),
(62, 26, 2, '', 14, '2015-06-23 08:41:13', NULL, NULL),
(63, 26, 4, '', 15, '2015-06-23 08:41:13', NULL, NULL),
(64, 27, 3, '', 13, '2015-06-23 08:46:03', NULL, NULL),
(65, 27, 2, '', 14, '2015-06-23 08:46:03', NULL, NULL),
(66, 27, 4, '', 15, '2015-06-23 08:46:03', NULL, NULL),
(67, 28, 3, '', 13, '2015-06-23 09:09:07', NULL, NULL),
(68, 28, 2, ' Eitelstraße 85, 10317', 14, '2015-06-23 09:09:07', NULL, NULL),
(69, 28, 4, '', 15, '2015-06-23 09:09:07', NULL, NULL),
(70, 29, 3, ' EitelstraÃe 85', 13, '2015-06-23 09:11:56', NULL, NULL),
(71, 29, 2, '', 14, '2015-06-23 09:11:56', NULL, NULL),
(72, 29, 4, '', 15, '2015-06-23 09:11:56', NULL, NULL),
(73, 30, 3, ' LangebrÃ¼ckenstraÃe 14, 36037', 16, '2015-06-23 09:22:14', NULL, NULL),
(74, 30, 2, '', 17, '2015-06-23 09:22:14', NULL, NULL),
(75, 30, 4, '', 18, '2015-06-23 09:22:14', NULL, NULL),
(76, 31, 3, ' Warschauer StraÃe 34, 10243', 16, '2015-06-23 09:37:31', NULL, NULL),
(77, 31, 2, '', 17, '2015-06-23 09:37:31', NULL, NULL),
(78, 31, 4, '', 18, '2015-06-23 09:37:31', NULL, NULL),
(79, 32, 3, ' Franklinstraße 24', 19, '2015-06-23 09:46:54', NULL, NULL),
(80, 32, 2, '', 20, '2015-06-23 09:46:54', NULL, NULL),
(81, 32, 4, '', 21, '2015-06-23 09:46:54', NULL, NULL),
(82, 33, 3, ' FranklinstraÃe 24', 19, '2015-06-23 09:51:27', NULL, NULL),
(83, 33, 2, '', 20, '2015-06-23 09:51:27', NULL, NULL),
(84, 33, 4, '', 21, '2015-06-23 09:51:28', NULL, NULL),
(85, 34, 3, ' Tempelhofer Ufer 12', 19, '2015-06-23 10:01:31', NULL, NULL),
(86, 34, 2, '', 20, '2015-06-23 10:01:31', NULL, NULL),
(87, 34, 4, '', 21, '2015-06-23 10:01:31', NULL, NULL),
(88, 35, 3, ' Eitelstrase 85', 19, '2015-06-23 10:13:24', NULL, NULL),
(89, 35, 2, '', 20, '2015-06-23 10:13:24', NULL, NULL),
(90, 35, 4, '', 21, '2015-06-23 10:13:24', NULL, NULL),
(91, 36, 3, ' Beyerhaus, Ernst-Schneller-Strasse 6', 19, '2015-06-23 10:23:37', NULL, NULL),
(92, 36, 2, '', 20, '2015-06-23 10:23:37', NULL, NULL),
(93, 36, 4, '', 21, '2015-06-23 10:23:37', NULL, NULL),
(94, 37, 3, ' Prenzlauer Allee 180', 16, '2015-06-23 10:29:10', NULL, NULL),
(95, 37, 2, '', 17, '2015-06-23 10:29:11', NULL, NULL),
(96, 37, 4, '', 18, '2015-06-23 10:29:11', NULL, NULL),
(97, 38, 3, ' Prenzlauer Allee 187', 16, '2015-06-23 10:31:28', NULL, NULL),
(98, 38, 2, '', 17, '2015-06-23 10:31:28', NULL, NULL),
(99, 38, 4, '', 18, '2015-06-23 10:31:29', NULL, NULL),
(100, 39, 3, ' Gorlitzer Strasse 52', 16, '2015-06-23 10:40:49', NULL, NULL),
(101, 39, 2, '', 17, '2015-06-23 10:40:50', NULL, NULL),
(102, 39, 4, '', 18, '2015-06-23 10:40:50', NULL, NULL),
(103, 40, 3, ' Hirschbrunnen, 10825', 16, '2015-06-23 10:47:01', NULL, NULL),
(104, 40, 2, '', 17, '2015-06-23 10:47:01', NULL, NULL),
(105, 40, 4, '', 18, '2015-06-23 10:47:01', NULL, NULL),
(106, 41, 3, 'Burgstrasse 5', 16, '2015-06-23 10:52:24', NULL, NULL),
(107, 41, 2, '', 17, '2015-06-23 10:52:24', NULL, NULL),
(108, 41, 4, '', 18, '2015-06-23 10:52:24', NULL, NULL),
(109, 42, 3, '96 Union Street', 22, '2015-06-23 11:04:39', NULL, NULL),
(110, 42, 2, '', 23, '2015-06-23 11:04:39', NULL, NULL),
(111, 42, 4, '', 24, '2015-06-23 11:04:39', NULL, NULL),
(112, 43, 3, ' 96 Union Street', 22, '2015-06-23 11:09:15', NULL, NULL),
(113, 43, 2, '', 23, '2015-06-23 11:09:15', NULL, NULL),
(114, 43, 4, '', 24, '2015-06-23 11:09:15', NULL, NULL),
(115, 44, 3, ' South Bank parkrun, South Brisbane, Queensland, Australia', 22, '2015-06-23 11:17:26', NULL, NULL),
(116, 44, 2, '', 23, '2015-06-23 11:17:26', NULL, NULL),
(117, 44, 4, '', 24, '2015-06-23 11:17:26', NULL, NULL),
(118, 45, 3, ' 456 Macquarie Street, South Hobart, Tasmania', 22, '2015-06-23 11:26:10', NULL, NULL),
(119, 45, 2, '', 23, '2015-06-23 11:26:10', NULL, NULL),
(120, 45, 4, '', 24, '2015-06-23 11:26:10', NULL, NULL),
(121, 46, 3, ' Hotel Sweeneys - Sydney CBD, Clarence Street', 22, '2015-06-23 11:33:16', NULL, NULL),
(122, 46, 2, '', 23, '2015-06-23 11:33:16', NULL, NULL),
(123, 46, 4, '', 24, '2015-06-23 11:33:16', NULL, NULL),
(124, 47, 3, ' Bourke Street, 12', 25, '2015-06-23 11:54:48', NULL, NULL),
(125, 47, 2, '', 26, '2015-06-23 11:54:48', NULL, NULL),
(126, 47, 4, '', 27, '2015-06-23 11:54:48', NULL, NULL),
(127, 48, 3, ' Imperial Hotel, Bourke Street', 25, '2015-06-23 11:57:37', NULL, NULL),
(128, 48, 2, '', 26, '2015-06-23 11:57:37', NULL, NULL),
(129, 48, 4, '', 27, '2015-06-23 11:57:38', NULL, NULL),
(130, 49, 3, '', 25, '2015-06-23 12:08:13', NULL, NULL),
(131, 49, 2, '', 26, '2015-06-23 12:08:13', NULL, NULL),
(132, 49, 4, '', 27, '2015-06-23 12:08:13', NULL, NULL),
(133, 50, 3, '', 22, '2015-06-23 12:15:26', NULL, NULL),
(134, 50, 2, '', 23, '2015-06-23 12:15:26', NULL, NULL),
(135, 50, 4, '', 24, '2015-06-23 12:15:27', NULL, NULL),
(136, 51, 3, ' Pat Fagan Park, Coolangatta QLD 4225', 25, '2015-06-23 12:57:43', NULL, NULL),
(137, 51, 2, '', 26, '2015-06-23 12:57:43', NULL, NULL),
(138, 51, 4, '', 27, '2015-06-23 12:57:43', NULL, NULL),
(139, 52, 3, ' Melbourne VIC', 25, '2015-06-23 13:05:12', NULL, NULL),
(140, 52, 2, '', 26, '2015-06-23 13:05:13', NULL, NULL),
(141, 52, 4, '', 27, '2015-06-23 13:05:13', NULL, NULL),
(142, 53, 3, '', 28, '2015-06-23 13:20:38', NULL, NULL),
(143, 53, 2, '', 29, '2015-06-23 13:20:38', NULL, NULL),
(144, 53, 4, '', 30, '2015-06-23 13:20:38', NULL, NULL),
(145, 54, 3, '', 28, '2015-06-23 13:23:40', NULL, NULL),
(146, 54, 2, '', 29, '2015-06-23 13:23:40', NULL, NULL),
(147, 54, 4, '', 30, '2015-06-23 13:23:40', NULL, NULL),
(148, 55, 3, '', 28, '2015-06-23 13:36:23', NULL, NULL),
(149, 55, 2, '', 29, '2015-06-23 13:36:27', NULL, NULL),
(150, 55, 4, '', 30, '2015-06-23 13:36:31', NULL, NULL),
(151, 56, 3, ' Ralambshovsparken', 28, '2015-06-23 13:47:13', NULL, NULL),
(152, 56, 2, '', 29, '2015-06-23 13:47:13', NULL, NULL),
(153, 56, 4, '', 30, '2015-06-23 13:47:13', NULL, NULL),
(154, 57, 3, ' Froken Anderssons Kaffebar', 28, '2015-06-23 13:54:35', NULL, NULL),
(155, 57, 2, '', 29, '2015-06-23 13:54:35', NULL, NULL),
(156, 57, 4, '', 30, '2015-06-23 13:54:35', NULL, NULL),
(157, 58, 3, ' Karlskronaplan 1, Malmo', 28, '2015-06-23 14:02:33', NULL, NULL),
(158, 58, 2, '', 29, '2015-06-23 14:02:34', NULL, NULL),
(159, 58, 4, '', 30, '2015-06-23 14:02:34', NULL, NULL),
(160, 59, 3, '', 31, '2015-06-23 14:26:02', NULL, NULL),
(161, 59, 2, '', 32, '2015-06-23 14:26:03', NULL, NULL),
(162, 59, 4, '', 33, '2015-06-23 14:26:03', NULL, NULL),
(163, 60, 3, '', 31, '2015-06-23 14:28:44', NULL, NULL),
(164, 60, 2, '', 32, '2015-06-23 14:28:44', NULL, NULL),
(165, 60, 4, '', 33, '2015-06-23 14:28:44', NULL, NULL),
(166, 61, 3, ' Surly Goat', 31, '2015-06-23 15:07:14', NULL, NULL),
(167, 61, 2, '', 32, '2015-06-23 15:07:14', NULL, NULL),
(168, 61, 4, '', 33, '2015-06-23 15:07:14', NULL, NULL),
(169, 62, 3, '', 31, '2015-06-23 15:10:44', NULL, NULL),
(170, 62, 2, '', 32, '2015-06-23 15:10:44', NULL, NULL),
(171, 62, 4, '', 33, '2015-06-23 15:10:44', NULL, NULL),
(172, 63, 3, ' Heritage Square Gold Line Station, 3545 Pasadena ', 31, '2015-06-23 15:14:50', NULL, NULL),
(173, 63, 2, '', 32, '2015-06-23 15:14:50', NULL, NULL),
(174, 63, 4, '', 33, '2015-06-23 15:14:50', NULL, NULL),
(175, 64, 3, '', 31, '2015-06-23 15:18:21', NULL, NULL),
(176, 64, 2, '', 32, '2015-06-23 15:18:21', NULL, NULL),
(177, 64, 4, '', 33, '2015-06-23 15:18:21', NULL, NULL),
(178, 65, 3, '', 31, '2015-06-23 15:21:10', NULL, NULL),
(179, 65, 2, '', 32, '2015-06-23 15:21:10', NULL, NULL),
(180, 65, 4, '', 33, '2015-06-23 15:21:10', NULL, NULL),
(181, 66, 3, '', 31, '2015-06-23 15:24:05', NULL, NULL),
(182, 66, 2, '', 32, '2015-06-23 15:24:05', NULL, NULL),
(183, 66, 4, '', 33, '2015-06-23 15:24:05', NULL, NULL),
(184, 67, 3, '', 34, '2015-06-23 15:41:44', NULL, NULL),
(185, 67, 2, '', 35, '2015-06-23 15:41:44', NULL, NULL),
(186, 67, 4, '', 36, '2015-06-23 15:41:45', NULL, NULL),
(187, 68, 3, '', 34, '2015-06-23 15:43:37', NULL, NULL),
(188, 68, 2, '', 35, '2015-06-23 15:43:37', NULL, NULL),
(189, 68, 4, '', 36, '2015-06-23 15:43:37', NULL, NULL),
(190, 69, 3, '', 37, '2015-06-23 15:52:55', NULL, NULL),
(191, 69, 2, '', 38, '2015-06-23 15:52:55', NULL, NULL),
(192, 69, 4, '', 39, '2015-06-23 15:52:55', NULL, NULL),
(193, 70, 3, '', 34, '2015-06-23 16:00:18', NULL, NULL),
(194, 70, 2, '', 35, '2015-06-23 16:00:18', NULL, NULL),
(195, 70, 4, '', 36, '2015-06-23 16:00:18', NULL, NULL),
(196, 71, 3, '', 31, '2015-06-23 16:08:13', NULL, NULL),
(197, 71, 2, '', 32, '2015-06-23 16:08:13', NULL, NULL),
(198, 71, 4, '', 33, '2015-06-23 16:08:13', NULL, NULL),
(199, 72, 3, '', 34, '2015-06-23 16:10:53', NULL, NULL),
(200, 72, 2, '', 35, '2015-06-23 16:10:53', NULL, NULL),
(201, 72, 4, '', 36, '2015-06-23 16:10:53', NULL, NULL),
(202, 73, 3, 'Chreshchatyk 5', 2, '2015-06-23 16:16:55', NULL, NULL),
(203, 73, 2, 'Хрещатик 5', 6, '2015-06-23 16:16:55', NULL, NULL),
(204, 73, 4, '', 10, '2015-06-23 16:16:55', NULL, NULL),
(205, 74, 3, '', 34, '2015-06-23 16:21:06', NULL, NULL),
(206, 74, 2, '', 35, '2015-06-23 16:21:06', NULL, NULL),
(207, 74, 4, '', 36, '2015-06-23 16:21:06', NULL, NULL),
(208, 75, 3, '', 40, '2015-06-23 16:28:45', NULL, NULL),
(209, 75, 2, '', 41, '2015-06-23 16:28:45', NULL, NULL),
(210, 75, 4, '', 42, '2015-06-23 16:28:45', NULL, NULL),
(211, 76, 3, ' Artema street, 131Ð²', 2, '2015-06-23 16:38:06', NULL, NULL),
(212, 76, 2, 'ÐÑÐ»Ð¸ÑÑ ÐÑÑÐµÐ¼Ð° 131Ð²', 6, '2015-06-23 16:38:06', NULL, NULL),
(213, 76, 4, 'ÑÐ». ÐÑÑÐµÐ¼Ð° 131Ð²', 10, '2015-06-23 16:38:06', NULL, NULL),
(214, 77, 3, 'Панча', 1, '2015-06-23 16:53:11', NULL, NULL),
(215, 77, 2, 'Pancha str.', 5, '2015-06-23 16:53:11', NULL, NULL),
(216, 77, 4, '', 9, '2015-06-23 16:53:11', NULL, NULL),
(217, 78, 3, '', 1, '2015-06-23 16:56:33', NULL, NULL),
(218, 78, 2, '', 5, '2015-06-23 16:56:33', NULL, NULL),
(219, 78, 4, '', 9, '2015-06-23 16:56:33', NULL, NULL),
(220, 79, 3, '', 37, '2015-07-15 12:35:39', NULL, NULL),
(221, 79, 2, '', 38, '2015-07-15 12:35:39', NULL, NULL),
(222, 79, 4, NULL, 39, '2015-07-15 12:35:39', NULL, NULL),
(223, 80, 3, '', 1, '2015-07-16 02:39:59', NULL, NULL),
(224, 80, 2, '???????????', 5, '2015-07-16 02:39:59', NULL, NULL),
(225, 80, 4, NULL, 9, '2015-07-16 02:39:59', NULL, NULL),
(226, 81, 3, '', 3, '2015-07-16 02:40:55', NULL, NULL),
(227, 81, 2, 'bivka', 7, '2015-07-16 02:40:55', NULL, NULL),
(228, 81, 4, NULL, 11, '2015-07-16 02:40:55', NULL, NULL),
(229, 82, 3, '', 22, '2015-07-16 02:41:57', NULL, NULL),
(230, 82, 2, 'Kangoo', 23, '2015-07-16 02:41:57', NULL, NULL),
(231, 82, 4, NULL, 24, '2015-07-16 02:41:57', NULL, NULL),
(232, 83, 3, '', 28, '2015-07-16 02:43:03', NULL, NULL),
(233, 83, 2, 'kokoloso', 29, '2015-07-16 02:43:03', NULL, NULL),
(234, 83, 4, NULL, 30, '2015-07-16 02:43:03', NULL, NULL),
(235, 84, 3, '', 16, '2015-07-16 02:45:45', NULL, NULL),
(236, 84, 2, 'alianzArena', 17, '2015-07-16 02:45:45', NULL, NULL),
(237, 84, 4, NULL, 18, '2015-07-16 02:45:45', NULL, NULL),
(238, 85, 3, '', 4, '2015-07-16 02:48:13', NULL, NULL),
(239, 85, 2, 'Poolw', 8, '2015-07-16 02:48:13', NULL, NULL),
(240, 85, 4, NULL, 12, '2015-07-16 02:48:13', NULL, NULL),
(241, 86, 3, '', 34, '2015-07-16 02:49:51', NULL, NULL),
(242, 86, 2, 'kasiiao 13', 35, '2015-07-16 02:49:51', NULL, NULL),
(243, 86, 4, NULL, 36, '2015-07-16 02:49:51', NULL, NULL),
(244, 87, 3, '', 22, '2015-07-16 02:50:57', NULL, NULL),
(245, 87, 2, 'sind', 23, '2015-07-16 02:50:57', NULL, NULL),
(246, 87, 4, NULL, 24, '2015-07-16 02:50:57', NULL, NULL),
(247, 88, 3, 'Maidan Nezalezhnosti', 2, '2015-07-16 02:53:50', NULL, NULL),
(248, 88, 2, '', 6, '2015-07-16 02:53:50', NULL, NULL),
(249, 88, 4, NULL, 10, '2015-07-16 02:53:50', NULL, NULL),
(250, 89, 3, '', 1, '2015-07-16 09:24:53', NULL, NULL),
(251, 89, 2, 'Окунеського', 5, '2015-07-16 09:24:53', NULL, NULL),
(252, 89, 4, NULL, 9, '2015-07-16 09:24:54', NULL, NULL),
(253, 90, 3, '', 16, '2015-07-16 12:15:31', NULL, NULL),
(254, 90, 2, '', 17, '2015-07-16 12:15:31', NULL, NULL),
(255, 90, 4, NULL, 18, '2015-07-16 12:15:31', NULL, NULL),
(256, 91, 3, '', 1, '2015-07-26 16:28:57', NULL, NULL),
(257, 91, 2, '', 5, '2015-07-26 16:28:57', NULL, NULL),
(258, 91, 4, NULL, 9, '2015-07-26 16:28:57', NULL, NULL),
(259, 92, 3, '', 31, '2015-07-26 16:57:19', NULL, NULL),
(260, 92, 2, 'пр. Чорновола', 32, '2015-07-26 16:57:19', NULL, NULL),
(261, 92, 4, NULL, 33, '2015-07-26 16:57:19', NULL, NULL),
(262, 93, 3, '', 1, '2015-07-27 14:10:28', NULL, NULL),
(263, 93, 2, '', 5, '2015-07-27 14:10:28', NULL, NULL),
(264, 93, 4, NULL, 9, '2015-07-27 14:10:28', NULL, NULL),
(265, 94, 3, '', 1, '2015-07-27 14:58:08', NULL, NULL),
(266, 94, 2, '', 5, '2015-07-27 14:58:08', NULL, NULL),
(267, 94, 4, NULL, 9, '2015-07-27 14:58:08', NULL, NULL),
(268, 95, 3, 'st.', 28, '2015-07-27 15:22:01', NULL, NULL),
(269, 95, 2, '', 29, '2015-07-27 15:22:01', NULL, NULL),
(270, 95, 4, NULL, 30, '2015-07-27 15:22:01', NULL, NULL);

-- 
-- Вывод данных для таблицы user
--
INSERT INTO user VALUES
(1, 'Володимир', 'Городецький', 'horodetskyyv@gmail.com', 1, 'male', 3, '+380637638967', NULL, NULL, 1, 1, '2e9eb77babb47cfe1b415e58b94141bd', 114, '0000-00-00 00:00:00', NULL),
(2, 'Андрій', 'Городецький', 'neutron80@list.ru', 3, 'male', 3, '+380652233445', NULL, NULL, 1, 1, '055c386458fcd480739b40b2b5f9f574', 98, '0000-00-00 00:00:00', NULL),
(3, 'Стас', 'Пьєха', 'someemail@gmail.com', 2, 'male', 4, '+380666677889', NULL, NULL, 0, 4, '55555', 116, '0000-00-00 00:00:00', NULL),
(4, 'Robert', 'Doms', 'owndomsemail@gmail.com', 3, 'male', 2, NULL, NULL, NULL, 1, 5, '33344', 106, '0000-00-00 00:00:00', NULL),
(5, 'Wlotko', 'Zlotkovich', 'anotheremail@gmail.com', 3, 'male', 8, '+710487740597', NULL, NULL, 1, 3, '151413', 107, '0000-00-00 00:00:00', NULL),
(6, 'Pamela', 'Boobs', 'mybigboobs@gmail.com', 2, 'female', 3, NULL, NULL, NULL, 1, 2, '44445', 109, '0000-00-00 00:00:00', NULL),
(8, 'Elon', 'Mask', 'elon.mask@gmail.com', 2, 'male', 3, '+380635560235', NULL, NULL, 0, 1, 'fa570d0d84034aeafa6116c395ce7bed', 2, '0000-00-00 00:00:00', NULL),
(11, 'John', 'Bonjovi', 'john.bonjovi@gmail.com', 2, 'male', 3, '+380635562355', NULL, NULL, 1, 14, 'qwerty', 8, '0000-00-00 00:00:00', NULL),
(12, 'Bill', 'Gates', 'bill.gates@gmail.com', 2, 'male', 3, '+380965020123', NULL, NULL, 1, 12, 'qwerty', 9, '0000-00-00 00:00:00', NULL),
(13, 'Oleg', 'Maksymuk', 'drolgmaks@gmail.com', 2, 'male', 2, NULL, '1643249359244458', NULL, 1, 1, '', 115, '0000-00-00 00:00:00', NULL),
(14, 'тарас', 'Шургот', 'tarasuchok@ukr.net', 2, 'male', 3, '+3800961243', NULL, NULL, 1, 24, 'asdfghjkl;''', 117, '2015-06-19 10:15:12', NULL),
(15, 'Serg', 'Horeliy', 'sdsfghjk@ukr.net', 2, 'male', 3, '+380', NULL, NULL, 0, 36, '123456', 10, '2015-06-19 10:39:42', NULL),
(16, 'sfdgh', 'sdfds', 'asd@ukr.net', 2, 'male', 3, '+380', NULL, NULL, 0, 39, '123456', 10, '2015-06-19 10:50:33', NULL),
(17, 'Andreia', 'Krumenauer', 'krumenauer@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 43, '52b0f49b49d23c72c21211fb0c98cd70', 103, '2015-06-23 02:10:52', NULL),
(18, 'Lens', 'Bilder', 'bilder@gmail.com', 2, 'male', 3, '', NULL, NULL, 1, 67, '48f5a30af053adef4a005d2a8590539f', 104, '2015-06-23 09:09:08', NULL),
(19, 'Andrea', 'Caroli', 'caroli@gmail.com', 2, 'male', 3, '', NULL, NULL, 1, 79, '89adca43df19fc6747b99c3d562d54a8', 118, '2015-06-23 09:46:54', NULL),
(20, 'Marian', 'Ditrich', 'ditrich@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 94, 'cc93915f275fbdc339469c3dbcaf0734', 101, '2015-06-23 10:29:11', NULL),
(21, 'Alison', 'Kangoo', 'kangoo@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 109, '6e1c33e57a6a67e97467a3a93431420b', 108, '2015-06-23 11:04:40', NULL),
(22, 'Victoria', 'Kiwi', 'vic_kiwi@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 124, 'a1b916cf1d62216c54855f2c5ca2afde', 110, '2015-06-23 11:54:49', NULL),
(23, 'Bettina', 'Sommer', 'sommer1985@gmail.com', 2, 'male', 2, '', NULL, NULL, 1, 142, 'db64abe63ba13fc2ff804c142f5d34b5', 10, '2015-06-23 13:20:38', NULL),
(24, 'Crystina', 'Gonzalez', 'gonzalez@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 259, 'fc0aaeee7c483e2e9ca466169b92caf7', 147, '2015-06-23 14:26:03', NULL),
(25, 'Sarah', 'Irvine', 'irvine@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 184, '43770d7604c720e1a6862909b26e30c6', 112, '2015-06-23 15:41:45', NULL),
(26, 'Lora', 'Palmer', 'lorapalmer1980@gmail.com', 2, 'female', 3, '', NULL, NULL, 1, 196, '1603451fb125637b93580e461eeeee7f', 143, '2015-06-23 16:08:13', NULL),
(27, 'Іван', 'Нестеренко', 'nestor@gmail.com', 2, 'male', 3, '+380098145487', NULL, NULL, 1, 202, 'cc32539c1f531e023a98ad266ed1a4f9', 105, '2015-06-23 16:16:55', NULL),
(28, 'Ігор', 'Возняк', 'igora@gmail.com', 2, 'male', 3, '+380975562641', NULL, NULL, 1, 214, '9259971a81fe144d6df10162c0f63d5a', 96, '2015-06-23 16:53:11', NULL),
(30, 'Volodimir', 'Gorodetsky', NULL, 8, 'male', 3, NULL, NULL, '121342775', 1, NULL, '', 10, '2015-06-29 15:45:26', NULL),
(31, 'Vladymyr', 'Horodetskyy', NULL, 8, 'male', 2, NULL, '919785968081047', NULL, 1, NULL, '', 10, '2015-07-09 20:01:39', NULL),
(32, 'Pamela', 'Anderson', 'pamelka@gmail.com', 2, 'female', 2, '050', NULL, NULL, 1, 220, '1d2e2d1b5a68b67b6ebe3b6d213063db', 111, '2015-07-15 12:35:39', NULL),
(33, 'Andriy', 'Gavrilyuk', NULL, 8, 'male', 3, NULL, NULL, '214100970', 1, NULL, '', 10, '2015-07-15 23:19:24', NULL),
(34, 'Andrey', 'Gavrilyk', NULL, 8, 'male', 3, NULL, '964963720220608', NULL, 1, NULL, '', 10, '2015-07-15 23:47:05', NULL),
(35, 'Victor', 'Valdes', 'vivivi@gmail.com', 7, 'male', 3, '+3809645678912', NULL, NULL, 1, 223, '9c9ef6db8c8ab87403fc7c6cf576c31e', 124, '2015-07-16 02:39:59', NULL),
(36, 'Andrey', 'Popov', 'vanyha@gmail.com', 2, 'male', 3, '+380968546974', NULL, NULL, 1, 226, '6030412f63bd04e3aa9208e1b7b0c1f5', 10, '2015-07-16 02:40:55', NULL),
(37, 'Steven', 'Berbatov', 'barb@gmail.com', 2, 'male', 3, '+380664569812', NULL, NULL, 1, 229, '43a39a1475bc772f8e4c361c61ff117e', 10, '2015-07-16 02:41:57', NULL),
(38, 'Taras', 'Grynchuk', 'tgrynchuk@gmail.com11', 7, 'male', 3, '+380992589674', NULL, NULL, 1, 232, 'a74405868d6efdb569c9c42182efa24c', 10, '2015-07-16 02:43:03', NULL),
(39, 'Filip', 'Moraes', 'marra@gmail.com', 7, 'male', 3, '+380664567821', NULL, NULL, 1, 235, '13133f679bd297984b96fb52a6423b28', 10, '2015-07-16 02:45:45', NULL),
(40, 'Amanda', 'Velasa', 'vela@gmail.com', 2, 'female', 3, '+380664578965', NULL, NULL, 1, 238, 'd53333ace85f908ea00bd0f38c714c69', 119, '2015-07-16 02:48:13', NULL),
(41, 'Kasper', 'Weeks', 'kasp@gmail.com', 2, 'male', 3, '+3806645678321', NULL, NULL, 1, 241, '95e4e89593c01525eaedf0bdd3ec6fb8', 122, '2015-07-16 02:49:51', NULL),
(42, 'Gummy', 'Bing', 'bing@gmail.com', 2, 'male', 3, '+380664567895', NULL, NULL, 1, 244, 'a85ec80845f7c1f64c78ebb5d044b851', 120, '2015-07-16 02:50:57', NULL),
(43, 'Deny', 'Cook', 'cok@gmail.com', 2, 'male', 3, '+380984561278', NULL, NULL, 1, 247, '54014f69ebfc73286dcff14e770f4217', 121, '2015-07-16 02:53:50', NULL),
(44, 'Тарас', 'Гринчук', NULL, 8, NULL, 2, NULL, '858069340927448', NULL, 1, NULL, '', 10, '2015-07-26 22:17:43', NULL),
(45, 'Тарас', 'Гринчук', NULL, 8, NULL, 3, NULL, NULL, '7729343', 1, NULL, '', 10, '2015-07-27 13:28:27', NULL),
(46, 'Taras', 'Grynchuk', 'tgrynchuk@gmail.com122', 2, 'male', 3, '09845454848', NULL, NULL, 1, 262, 'a74405868d6efdb569c9c42182efa24c', 10, '2015-07-27 14:10:28', NULL),
(47, 'Taras', 'Grynchuk', 'tgrynchuk@gmail.com', 2, 'male', 3, '0985848484', NULL, NULL, 1, 265, 'a74405868d6efdb569c9c42182efa24c', 10, '2015-07-27 14:58:08', NULL),
(48, 'Ігор', 'Стороженко', 'igor@gmail.com', 2, 'male', 3, '', NULL, NULL, 1, 268, 'bd24b1fc384f9fac5199134bddcbd60c', 10, '2015-07-27 15:22:01', NULL);

-- 
-- Вывод данных для таблицы comment_user
--
INSERT INTO comment_user VALUES
(1, 1, 4, 'Пиво яке він приносить не дуже !', '2015-06-17 09:36:41'),
(2, 3, 6, 'Привіт , класно по зависали, можна повторити ... ', '2015-06-17 09:36:41'),
(3, 1, 8, 'Very cool guy', '2015-07-07 11:52:49'),
(4, 2, 8, 'Класний мен) вміє організувати', '2015-07-07 11:54:09'),
(5, 11, 8, 'I know Elon) very long time))))', '2015-07-07 11:56:57'),
(6, 12, 8, 'I m using car produced by Elon', '2015-07-07 11:57:31'),
(8, 24, 8, 'It was very cool to visite last presentation of Tesla car!', '2015-07-07 12:05:05'),
(9, 8, 8, 'Thanks, Cristina!', '2015-07-07 12:05:27'),
(11, 8, 11, 'Good!)) ', '2015-07-15 19:00:08'),
(13, 8, 24, 'Great!)', '2015-07-15 19:10:52'),
(14, 8, 12, 'Good!)) ', '2015-07-15 19:11:30'),
(15, 8, 2, 'COoool!)!))) i`m satisfied!!!', '2015-07-15 19:12:20'),
(16, 20, 17, 'good friend!)', '2015-07-15 19:34:05'),
(17, 20, 17, 'good boy!)', '2015-07-15 19:34:24'),
(20, 24, 17, 'i`m happy with him', '2015-07-15 19:54:19'),
(21, 18, 25, 'very clever!)', '2015-07-15 20:15:32'),
(22, 17, 8, 'Cool boy!))', '2015-07-15 20:41:41'),
(23, 8, 24, 'wow!', '2015-07-16 06:37:19'),
(24, 24, 8, 'Comment to Elon Mask !', '2015-07-16 09:32:33'),
(28, 8, 8, 'Answer on comment', '2015-07-16 12:21:15'),
(30, 26, 24, 'She is my best friend!!!', '2015-07-26 13:28:04'),
(31, 24, 2, 'very nice guide)', '2015-08-02 17:47:36');

-- 
-- Вывод данных для таблицы event
--
INSERT INTO event VALUES
(1, 'Похід в Криївку', 'Оригінальна та популярна кнайпа. Щоб потрапити туди, необхідно пройти цікаву процедуру «фейс-контролю»\r\nУ “Криївці” панує атмосфера пабу – люди приходять сюди компаніями, під вечір починають хором співати пісні...', '2015-07-19 12:00:00', '2015-06-19 13:30:00', 6, 2, 'active', 3, '2015-06-19 10:05:55', 10, 0, NULL),
(2, 'Ексурсія по Оперному Театру', 'Львівський національний академічний театр опери та балету імені Соломії Крушельницької ', '2015-06-27 17:00:00', '2015-06-27 19:00:00', 7, 4, 'active', 4, '2015-06-19 10:05:55', 15, 0, NULL),
(3, 'Royal Castle', 'The Royal Castle in Warsaw is a castle residency and was the official residence of the Polish monarchs', '2015-06-25 14:00:00', '2015-06-25 16:00:00', 3, 1, 'active', 5, '2015-06-19 10:05:55', 20, 0, NULL),
(4, 'Похід в нічний клуб Fashion Club', 'Запальна вечірка в найкращих традиціях львівської молоді в клубі Фешн клаб', '2015-06-14 22:00:00', '2015-06-14 05:00:00', 9, 6, 'active', 6, '2015-06-19 10:05:55', 5, 0, NULL),
(5, 'Захід Фестиваль', 'Zaxid festival near Ivano Frankovo', '2015-06-18 00:00:00', '2015-08-18 00:00:00', 9, 24, 'done', 7, '2015-06-19 10:05:55', 20, 0, NULL),
(6, '150 th Weekly Monday', 'Start time at 20:00 every Monday!', '2015-07-27 02:14:41', '2015-07-27 02:14:50', 45, 17, 'cancelled', 12, '2015-06-23 02:15:55', 20, 0, NULL),
(7, 'dfdfdf', 'aaaaaaaaaaaaaaaaaaaaaa', '2015-06-23 01:01:00', '2015-06-23 02:04:00', 46, 17, 'cancelled', 11, '2015-06-23 08:01:52', 10, 1, NULL),
(8, 'Banana Boat Party', 'Kiev Regular Party \r\nMusic...Dances... \r\nFREE WELCOME TROPICAL DRINKS, SMILES AND HUGS :))))', '2015-07-28 07:05:00', '2015-07-29 08:02:00', 49, 17, 'active', 14, '2015-06-23 08:13:54', 15, 0, NULL),
(9, 'Barbecue by Brisbane Beach', 'Come to Brisbane''s beach along the river for a BBQ (and a swim afterwards in summer months). Alternate Tuesday''s to the official couchsurfing event nearby. Meet at one of the free BBQs at the north end of the pool.', '2015-07-29 09:00:00', '2015-07-29 08:35:02', 58, 17, 'active', 15, '2015-06-23 08:34:06', 20, 1, NULL),
(10, 'Bed and Breakslow Club', 'Strangers meet to eat together delicious homemade food and by the means of art, bodywork, storytelling, creativity and more classical psychological training make important and enjoyable steps towards self-growth.', '2015-08-25 09:00:00', '2015-08-25 09:00:00', 64, 17, 'active', 16, '2015-06-23 08:46:03', 15, 0, NULL),
(11, 'Yoga + Free Dinner TUESDAYS', 'Hello, \r\nMy name is Lenc. \r\nI give Ashtanga Yoga sessions in my Yoga Room (Eitelstr 85, Sbahn Lichtenberg). \r\nSessions are nearly every day also on weekends. ', '2015-08-04 09:00:00', '2015-08-04 09:00:00', 70, 2, 'active', 17, '2015-06-23 09:11:56', 5, 0, NULL),
(12, 'Trommelsession im Cafe© Panama Fulda', 'Wir sind eine Trommelgruppe aus Fulda, die sich jeden Dienstag um 19Uhr im CafÃ© Panama trifft. Bei uns kann jeder mitmachen, man braucht keine Vorkenntnisse, da wir frei trommeln, und alles kostenlos. :)', '2015-08-03 10:00:00', '2015-08-03 09:22:37', 73, 18, 'active', 18, '2015-06-23 09:22:14', 8, 0, NULL),
(13, ' KARAOKE - Wednesday ', 'K-TV / Norebang / Karaoke - Korean/Japanese style:\r\nThis is the biweekly Karaoke meetup at:\r\nMonster Ronson''s Ichiban Karaoke. WHY: \r\nBecause it''s a great way to meet people, have fun, break the ice and make a fool of yourself ... :P', '2015-07-29 09:00:00', '2015-07-29 09:00:00', 76, 18, 'active', 19, '2015-06-23 09:37:31', 18, 0, NULL),
(14, 'Bonjour Duesseldorf', 'Salut à tous,nous nous rencontrons ce mercredi 1er octobre - comme tous les mercredis- à 19:30h chez "Léon", Franklinstr. 24 pour parler français !', '2015-08-05 09:00:00', '2015-08-05 09:00:00', 82, 19, 'active', 20, '2015-06-23 09:51:28', 15, 0, NULL),
(15, 'YOGA IN ENGLISH IN KREUZBERG', 'Welcome everyone to practice the ancient Indian science of harmonizing the body with the cosmos, to calm down the mind and see clearly our real nature.\r\n\r\nI invite you to come and practice with us!\r\n', '2015-08-26 09:00:00', '2015-08-26 09:00:00', 85, 19, 'active', 22, '2015-06-23 10:01:32', 15, 0, NULL),
(16, 'Ashtanga Yoga Thursdays', 'I give Ashtanga Yoga sessions in my Yoga Room (Eitelstr 85, Sbahn Lichtenberg). Sessions are nearly every day also on weekends.  ', '2015-07-30 09:00:00', '2015-07-30 09:00:00', 88, 19, 'active', 23, '2015-06-23 10:13:24', 15, 0, NULL),
(17, 'French Conversational Meeting', 'Salut à toutes et à tous, Améliorez et pratiquez vos connaissances de langue dans une ambiance agréable.', '2015-08-19 09:00:00', '2015-08-19 09:00:00', 91, 19, 'active', 24, '2015-06-23 10:23:37', 10, 0, NULL),
(18, 'Crazy Ping Pong Thursday', 'Cheers all,\r\nas it became really popular to play the crazy ping pong at Atopia every friday we bring it now on for thursdays aswell. We play china.', '2015-08-13 09:00:00', '2015-08-13 09:00:00', 97, 20, 'active', 25, '2015-06-23 10:31:29', 8, 0, NULL),
(19, 'swing dancing at Nest', 'Shake it to the best swinging hot jazz music on Thursdays at Nest, Gorzitzer Str 52. Nest is a big, beautiful cafe with spacious floors, friendly staff, and sympathetic neighbors. ', '2015-08-05 09:00:00', '2015-08-05 09:00:00', 100, 20, 'active', 26, '2015-06-23 10:40:50', 18, 0, NULL),
(20, 'Park Workout', 'To all friends of sports and fitness, \r\nWe are gathering to a group workout event! Every Sunday at 10:30am at the "Hirschbrunnen / Goldener Hirsch Statue" next to Ubahn Rathaus SchÃ¶neberg. \r\nFeel free to join! ItÂ´s all free! ', '2015-08-13 09:00:00', '2015-08-13 09:00:00', 103, 20, 'active', 27, '2015-06-23 10:47:01', 20, 0, NULL),
(21, 'Free french lessons', 'Hello everybody! I''m a french guy and would like to start teaching french! I''m looking for some people to train. Please feel free to join! Beginners will get lessons on paper and will learn to do sentences, fluent speakers will improve their french by chatting!', '2015-08-25 09:00:00', '2015-08-25 09:00:00', 106, 20, 'active', 28, '2015-06-23 10:52:24', 8, 0, NULL),
(22, 'Sydney CS Monthly Drinks', 'Each month CS Sydney comes together to celebrate our CS family and welcome new members to the city with a bit of a party.\r\n\r\nWe have been at the PBH since around July 2010\r\n\r\nWe are generally on the rooftop area (up two flights of stairs) :) Sometimes we are one floor down.', '2015-08-19 09:00:00', '2015-08-19 09:00:00', 112, 21, 'active', 29, '2015-06-23 11:09:15', 20, 0, NULL),
(23, 'Barbecue by Brisbane Beach', 'Come to Brisbane''s beach along the river for a BBQ (and a swim afterwards in summer months). Alternate Tuesday''s to the official couchsurfing event nearby. Meet at one of the free BBQs at the north end of the pool.', '2015-08-29 09:00:00', '2015-08-29 09:00:00', 115, 21, 'active', 30, '2015-06-23 11:17:26', 15, 0, NULL),
(24, 'volxkitch at the arts factory', 'A weekly vegan community meal, cooked by a team of volunteers and available to the public for an optional donation. The meal is cooked from donations from Foodbank, local market gardens where possible. We also often have local world music bands play or a pianist belting out some Tom Waits songs.', '2015-08-05 09:00:00', '2015-08-05 09:00:00', 118, 21, 'active', 31, '2015-06-23 11:26:11', 15, 0, NULL),
(25, 'Polyglot Sydney - Language', 'What: It''s a cultural & language exchange night \r\nWhen: Every Wednesday night, 7:30pm until late \r\nWhere: Hotel Sweeney, Rooftop (next level down if it rains) \r\n236 Clarence Street, Sydney CBD. (corner of Clarence & Druitt Streets) ', '2015-07-31 09:00:00', '2015-07-31 09:00:00', 121, 21, 'active', 32, '2015-06-23 11:33:16', 10, 0, NULL),
(26, 'FREE comedy & happy hour drinks', 'Australia''s best up and coming comedians as well as the likes of Celia Pacquola (ABC, Network 10), Tommy Little (NOVA FM), Michael Chamberlin (ROVE Live) and more.', '2015-07-29 09:00:00', '2015-07-29 09:00:00', 127, 22, 'active', 33, '2015-06-23 11:57:38', 5, 0, NULL),
(27, 'MIELE - Melbourne Italian/English Language Exchange', 'An opportunity to practise language, whether it''s to improve your Italian or English speaking skills... All levels of language welcome. Come along and share the knowledge!', '2015-07-31 09:00:00', '2015-07-31 09:00:00', 130, 22, 'active', 34, '2015-06-23 12:08:13', 7, 0, NULL),
(28, 'Bike across Oz', 'I want to cycle from Sydney to Alice springs and then to Perth. \r\nI want to find someone who is determined and willing to accept this grand adventure with me. \r\nNothing has been finalized yet, just need to find someone who is willing to make the journey with me. ', '2015-08-25 09:00:00', '2015-08-14 09:00:00', 133, 22, 'active', 35, '2015-06-23 12:15:27', 25, 0, NULL),
(29, 'Matt''s BOOTCAMP', 'Matt''s BOOTCAMP takes your fitness to a whole new level.\r\n\r\nSuitable for people of all fitness levels.\r\n\r\nWhether you want to join in regularly or as a once off on your trip come along and have some fun.\r\n\r\nOnly $10 per person.', '2015-08-11 09:00:00', '2015-08-30 09:00:00', 136, 22, 'active', 36, '2015-06-23 12:57:44', 30, 0, NULL),
(30, 'Critical Mass (Cycling event)', 'Critical Mass transforms a dreary commute into a radiant celebration of a vibrant urban life and reclaiming of public space. Bikes are fun and every bicyclist is one less car. Critical Mass is proof that we are each makers of history and shapers of our own social life. Bike Love creates a people powered community that enhances the urban experience, the city is alive!', '2015-08-18 09:00:00', '2015-08-18 09:00:00', 139, 22, 'active', 37, '2015-06-23 13:05:13', 50, 0, NULL),
(31, 'Hints for Stockholm', 'Hello everyone,\r\n\r\nThis event should summarize the basic information most people ask for in the main Stockholm forum.', '2015-07-25 09:00:00', '2015-07-30 09:00:00', 145, 23, 'active', 38, '2015-06-23 13:23:40', 25, 0, NULL),
(32, 'short yoga, guided meditation', 'Dear all. \r\nI invite you to a tender journey to your inner world. \r\nThe meditation is going to be guru and religion free, comes from own experiences, psychotherapy, and a bit from classical yoga ( like light and love visualization, activating 5 senses)', '2015-07-30 09:00:00', '2015-07-30 09:00:00', 148, 23, 'active', 39, '2015-06-23 13:36:31', 10, 0, NULL),
(33, 'Ultimate Frisbee and Beer!', 'Bring warm training clothes depending on weather and good shoes; football shoes (cleats) is an advantage! We will play for almost 2 hours. We sometimes go and grab a beer afterwards if there''s interest. And also possible to take a dip in the lake! :)', '2015-08-26 09:00:00', '2015-08-31 09:00:00', 151, 23, 'active', 40, '2015-06-23 13:47:14', 8, 0, NULL),
(34, 'Regular friday beers', 'OBS NEW VENUE!!! OBS NEW VENUE!!! OBS NEW VENUE!!!\r\n\r\nHey! Lets meet on fridays to hang out together in the Heart of SOFO at an nice cosy bar.', '2015-08-28 09:00:00', '2015-08-28 09:00:00', 154, 23, 'active', 41, '2015-06-23 13:54:35', 6, 0, NULL),
(35, 'Regular CS Meeting at Trols in Malmo', 'Come to Tröls and meet new, old, potential CS members or bring a friend - the more the merrier. Have a some beer (they have many to choose from) or wine and chat, share travel stories, CS tips and plan for new CS events!!', '2015-08-19 09:00:00', '2015-08-19 09:00:00', 157, 23, 'active', 42, '2015-06-23 14:02:34', 18, 0, NULL),
(36, 'Venice Beach Photowalk', 'Meeting Location: We''ll meet at the front of a cafe called 212 Pier (http://www.212pier.com/). The cafe is located at 212 Pier Ave., Santa Monica, CA 90405, and has wifi, bathrooms, and coffee!', '2015-07-29 09:00:00', '2015-08-01 09:00:00', 163, 24, 'active', 44, '2015-06-23 14:28:44', 10, 0, NULL),
(37, 'Karaoke at Craft Beer Bar in West Hollywood', 't''s Wednesday so it''s time to come down to what the Thrillist.com calls the best local bar in West Hollywood (http://bit.ly/1Hv1316) and sing some Karaoke! Lots of Craft Beers and Karaoke songs to choose from! Starts every Wednesday at 10p till close. Tell the Karaoke Host you''re from Couchsurfing.com and we''ll do our best to get you on the mic (time permitting.)', '2015-07-22 09:00:00', '2015-06-22 09:00:00', 166, 24, 'active', 45, '2015-06-23 15:07:14', 7, 0, NULL),
(38, 'Metro Party and BBQ', 'I''m hosting a Metro party and BBQ at my place! The concept is you should come by Metro train or bus instead of driving :) I live 1/2 block from the Heritage Sq. Metro station (Cypress/Highland Park) on the Gold Line so take public trans!', '2015-08-18 09:00:00', '2015-08-18 09:00:00', 169, 24, 'active', 46, '2015-06-23 15:10:44', 7, 0, NULL),
(39, 'Dance Party Bike Ride', 'No One Can Here You Scream In Space - Bike with us to some cool places to dance like animals. We will go some places where you never thought you''d be dancing. Costumes encouraged - space travel clothes or caveman chic.', '2015-08-18 09:00:00', '2015-08-18 09:00:00', 172, 24, 'active', 47, '2015-06-23 15:14:50', 9, 0, NULL),
(40, 'CS Meetup: Festival of Masks Leimert Park', 'Weekly CS Meetups in LA which explore the vast culture of LA. I strive to select events all-inclusive in our community: Free, All ages & with cultural focus on the wonderful culture & immigrant diaspora here in this vast sprawling international city. ', '2015-08-19 09:00:00', '2015-08-20 09:00:00', 175, 24, 'active', 48, '2015-06-23 15:18:21', 20, 0, NULL),
(41, 'communication of Chinese Kongfu-Taichi', 'Hello, nice to meet you. I am spring from China, I am going to US stay in there for 1 month. if you want to be my friend. or want to learning Chinese Taichi and Kongfu, just contact me! . I am very out going and love to travel. :) I can speak English.thank. etc... hope we can be good frined!', '2015-08-19 09:00:00', '2015-08-19 09:00:00', 178, 24, 'active', 49, '2015-06-23 15:21:10', 7, 0, NULL),
(42, 'LA Museums Tour', 'We offer small groups private tours of museums and art galleries throughout the LA region.\r\n\r\nGeneral tours include background on each museum and its history as well as an introduction to the collection. Focus tours address particular strengths of a museum collection. Seasonal tours are developed for select exhibitions. All tours are 90-minutes in length. Tours can be adapted to family', '2015-08-10 09:00:00', '2015-08-10 09:00:00', 181, 2, 'active', 150, '2015-06-23 15:24:05', 9, 0, NULL),
(43, 'Road trip to Yosemite', 'Hello CS friends. I am planning on hiring a car in San Francisco to drive to Yosemite and the surrounding area to hike and camp. I''m looking for some friendly, likeminded people to join me and share the costs of the car and gas, and who enjoy the outdoors and singing songs around the campfire in the evening.', '2015-07-31 09:00:00', '2015-08-02 09:00:00', 187, 25, 'active', 51, '2015-06-23 15:43:37', 12, 0, NULL),
(44, 'Las Vegas to Colorado Springs', ' I''m planning on travelling to Colorado Springs via the Grand Canyon. Anyone who would like to join me for part or all of the journey is more than welcome. You would have to chip in a little money for gas and pay for your own food etc. ', '2015-08-24 09:00:00', '2015-08-27 09:00:00', 190, 25, 'active', 52, '2015-06-23 15:52:55', 5, 0, NULL),
(45, 'Trip SF to Yosemite', 'Planning to take a Gotobus or car share to Yosemite this week, anybody likes to join hit me up!', '2015-08-27 09:00:00', '2015-08-31 09:00:00', 193, 25, 'active', 53, '2015-06-23 16:00:18', 6, 0, NULL),
(46, 'Welcome Summer Bonfire', 'Hi Couchsurfers,\r\n\r\nSummer is here, so lets get together and celebrate with a bonfire/potlock/bbq. Hopefully it will be sunny and warmer.\r\n\r\nâ¢ What to bring: \r\n* Yourself, good mood, your music instrument, kite, volleyball...etc. \r\n* Some finger food (ready to-BBQ, fruits, home-made) to share', '2015-08-28 09:00:00', '2015-08-31 09:00:00', 199, 26, 'active', 54, '2015-06-23 16:10:53', 8, 0, NULL),
(47, 'RV Trip To Vancouver BC', 'Im  leaving in Kyiv and want  to  trip to The  Redwoods National forest and then Portland, Seattle and Vancouver. Looking to be in Vancouver by the 1st roughly. ', '2015-08-01 09:00:00', '2015-08-14 09:00:00', 205, 27, 'active', 55, '2015-06-23 16:21:06', 7, 0, NULL),
(48, 'Road Trip NorCal cannabis then Seattle', 'Are you looking to see the north west? I''m \r\n very out going person who is looking for a person or two wanting to hit the road. There is no set date but I would like to be in California by the 4th of September.', '2015-09-01 09:00:00', '2015-09-17 09:00:00', 208, 27, 'active', 56, '2015-06-23 16:28:45', 4, 0, NULL),
(49, 'German Language Exchange Club', 'This group is meant to build language exchange in Kyiv. It will be a platform to plan conversation meetings for several languages. Would you like to practice or find partners to learn a foreign language? We are now working to create a few groups for different languages with at least one person responsible for each.', '2015-08-27 16:41:19', '2015-08-29 16:41:32', 211, 27, 'active', 57, '2015-06-23 16:38:06', 3, 0, NULL),
(50, 'Арт Барбекю у Палаці мистецтв', 'ЩОВИХІДНИХ літо мистецтва та смаку у Львові. Тераса Палацу Мистецтв влітку 2015 перетвориться на свято креативу, музики та смачної їжі.', '2015-08-29 09:00:00', '2015-07-31 09:00:00', 217, 28, 'filled', 58, '2015-06-23 16:56:33', 5, 1, NULL),
(51, 'Нова подія', 'Просто  подія', '2015-07-16 09:00:00', '2015-07-18 18:00:00', 250, 25, 'active', 11, '2015-07-16 09:24:54', NULL, 0, NULL),
(52, 'New event', 'desr', '2015-07-17 03:10:00', '2015-07-30 07:16:00', 253, 8, 'active', 11, '2015-07-16 12:15:31', 3, 0, NULL),
(53, 'Зустріч однокласників', 'Проста зустріч', '2015-07-27 18:00:00', '2015-07-27 22:00:00', 256, 24, 'active', 11, '2015-07-26 16:28:57', 30, 0, NULL);

-- 
-- Вывод данных для таблицы friend_user
--
INSERT INTO friend_user VALUES
(2, 11, 8, '0000-00-00 00:00:00'),
(4, 12, 8, '0000-00-00 00:00:00'),
(6, 13, 8, '0000-00-00 00:00:00'),
(10, 3, 8, '2015-06-19 05:59:05'),
(11, 5, 8, '2015-06-19 05:59:15'),
(16, 28, 27, '2015-06-23 17:24:02'),
(17, 27, 28, '2015-06-23 17:24:11'),
(18, 26, 28, '2015-06-23 17:24:24'),
(19, 28, 26, '2015-06-23 17:24:31'),
(20, 28, 22, '2015-06-23 17:28:21'),
(21, 6, 26, '2015-06-23 17:28:21'),
(22, 24, 28, '2015-06-23 17:28:21'),
(23, 24, 27, '2015-06-23 17:28:21'),
(26, 23, 17, '2015-06-23 17:28:21'),
(27, 17, 23, '2015-06-23 17:28:21'),
(28, 23, 21, '2015-06-23 17:28:21'),
(29, 21, 11, '2015-06-23 17:28:21'),
(30, 21, 27, '2015-06-23 17:28:21'),
(31, 27, 20, '2015-06-23 17:28:21'),
(32, 20, 27, '2015-06-23 17:28:21'),
(33, 18, 19, '2015-06-23 17:28:21'),
(34, 18, 25, '2015-06-23 17:28:21'),
(35, 25, 18, '2015-06-23 17:28:21'),
(36, 25, 19, '2015-06-23 17:28:21'),
(46, 8, 22, '2015-07-15 18:58:57'),
(49, 2, 24, '2015-07-15 19:05:04'),
(50, 2, 23, '2015-07-15 19:05:21'),
(51, 2, 18, '2015-07-15 19:05:23'),
(52, 2, 28, '2015-07-15 19:05:25'),
(54, 17, 8, '2015-07-15 19:21:09'),
(55, 17, 2, '2015-07-15 19:21:21'),
(56, 17, 24, '2015-07-15 19:21:54'),
(57, 17, 25, '2015-07-15 19:22:19'),
(58, 17, 20, '2015-07-15 19:22:20'),
(59, 17, 13, '2015-07-15 19:22:22'),
(60, 17, 28, '2015-07-15 19:22:26'),
(61, 20, 17, '2015-07-15 19:32:14'),
(62, 20, 23, '2015-07-15 19:32:21'),
(63, 20, 22, '2015-07-15 19:32:36'),
(64, 20, 21, '2015-07-15 19:32:37'),
(65, 20, 12, '2015-07-15 19:32:38'),
(66, 20, 4, '2015-07-15 19:32:39'),
(67, 20, 2, '2015-07-15 19:32:41'),
(68, 20, 19, '2015-07-15 19:32:41'),
(69, 20, 32, '2015-07-15 19:32:43'),
(70, 20, 18, '2015-07-15 19:32:43'),
(71, 19, 18, '2015-07-15 19:43:22'),
(72, 19, 20, '2015-07-15 19:43:25'),
(73, 19, 23, '2015-07-15 19:43:36'),
(74, 19, 14, '2015-07-15 19:43:37'),
(75, 19, 11, '2015-07-15 19:43:38'),
(76, 19, 26, '2015-07-15 19:43:39'),
(77, 19, 5, '2015-07-15 19:43:41'),
(78, 19, 32, '2015-07-15 19:43:46'),
(79, 19, 27, '2015-07-15 19:48:44'),
(81, 24, 17, '2015-07-15 19:49:48'),
(82, 24, 21, '2015-07-15 19:49:55'),
(83, 24, 18, '2015-07-15 19:49:56'),
(84, 24, 25, '2015-07-15 19:49:57'),
(85, 24, 19, '2015-07-15 19:49:58'),
(86, 24, 6, '2015-07-15 19:49:59'),
(87, 24, 4, '2015-07-15 19:50:00'),
(88, 24, 32, '2015-07-15 19:50:02'),
(89, 27, 19, '2015-07-15 19:56:06'),
(90, 27, 24, '2015-07-15 19:56:10'),
(91, 27, 18, '2015-07-15 19:56:22'),
(92, 27, 17, '2015-07-15 19:56:22'),
(93, 27, 5, '2015-07-15 19:56:23'),
(94, 27, 4, '2015-07-15 19:56:24'),
(95, 27, 26, '2015-07-15 19:56:26'),
(96, 27, 12, '2015-07-15 19:56:27'),
(97, 27, 8, '2015-07-15 19:56:32'),
(98, 27, 14, '2015-07-15 19:56:59'),
(99, 28, 24, '2015-07-15 20:01:21'),
(100, 28, 17, '2015-07-15 20:01:23'),
(101, 28, 18, '2015-07-15 20:01:34'),
(102, 28, 21, '2015-07-15 20:01:34'),
(103, 28, 20, '2015-07-15 20:01:35'),
(104, 28, 6, '2015-07-15 20:01:36'),
(105, 28, 4, '2015-07-15 20:01:37'),
(106, 28, 25, '2015-07-15 20:01:39'),
(107, 22, 28, '2015-07-15 20:04:50'),
(108, 22, 20, '2015-07-15 20:04:52'),
(109, 22, 23, '2015-07-15 20:05:23'),
(110, 22, 19, '2015-07-15 20:05:23'),
(111, 22, 24, '2015-07-15 20:05:24'),
(112, 22, 5, '2015-07-15 20:05:25'),
(113, 22, 11, '2015-07-15 20:05:27'),
(114, 22, 21, '2015-07-15 20:05:30'),
(115, 22, 2, '2015-07-15 20:05:31'),
(116, 21, 20, '2015-07-15 20:10:50'),
(117, 21, 23, '2015-07-15 20:10:51'),
(118, 21, 22, '2015-07-15 20:10:55'),
(119, 21, 24, '2015-07-15 20:11:02'),
(120, 21, 17, '2015-07-15 20:11:13'),
(121, 21, 14, '2015-07-15 20:11:14'),
(122, 21, 5, '2015-07-15 20:11:15'),
(123, 18, 20, '2015-07-15 20:13:15'),
(124, 18, 27, '2015-07-15 20:13:15'),
(125, 18, 22, '2015-07-15 20:13:30'),
(126, 18, 17, '2015-07-15 20:13:30'),
(127, 18, 5, '2015-07-15 20:13:31'),
(128, 18, 8, '2015-07-15 20:13:32'),
(129, 8, 27, '2015-07-15 20:18:07'),
(130, 8, 5, '2015-07-15 20:18:08'),
(131, 23, 24, '2015-07-15 20:34:13'),
(132, 23, 19, '2015-07-15 20:34:13'),
(133, 23, 20, '2015-07-15 20:34:15'),
(134, 23, 13, '2015-07-15 20:34:27'),
(135, 23, 18, '2015-07-15 20:34:28'),
(136, 23, 27, '2015-07-15 20:34:28'),
(141, 8, 21, '2015-07-16 03:38:53'),
(145, 8, 12, '2015-07-16 06:34:27'),
(149, 8, 18, '2015-07-16 09:27:01'),
(154, 8, 3, '2015-07-16 12:18:53'),
(155, 8, 28, '2015-07-16 12:20:27'),
(156, 8, 26, '2015-07-16 12:25:12'),
(157, 24, 8, '2015-07-16 12:29:01'),
(158, 8, 24, '2015-07-16 12:29:07'),
(160, 8, 11, '2015-07-25 18:11:07'),
(161, 8, 2, '2015-07-25 18:49:38'),
(162, 26, 24, '2015-07-26 13:24:30'),
(163, 24, 26, '2015-07-26 13:26:17'),
(165, 24, 11, '2015-07-26 16:50:41'),
(166, 8, 17, '2015-07-26 22:00:38'),
(167, 8, 4, '2015-07-26 22:05:28'),
(168, 2, 8, '2015-08-02 15:12:09'),
(169, 24, 2, '2015-08-02 17:46:13');

-- 
-- Вывод данных для таблицы getmoney
--

-- Таблица guideme.getmoney не содержит данных

-- 
-- Вывод данных для таблицы message_user
--
INSERT INTO message_user VALUES
(1, 2, 4, 'Привіт,  як справи', 1, '0000-00-00 00:00:00'),
(2, 4, 2, 'дДовре, в тебе як ?', 1, '0000-00-00 00:00:00'),
(3, 8, 24, 'Привіт', NULL, '2015-07-16 09:37:16'),
(4, 24, 8, 'Привіт)))', NULL, '2015-07-16 09:37:44'),
(5, 8, 24, 'Супер)', NULL, '2015-07-16 09:38:08'),
(6, 24, 8, 'Їдеш на фестиваль захід ?', NULL, '2015-07-16 09:38:18'),
(7, 8, 24, 'так)  а ти ?', NULL, '2015-07-16 09:38:25'),
(8, 19, 1, 'hi admin', NULL, '2015-07-16 10:53:03'),
(9, 24, 8, 'hello', NULL, '2015-07-16 12:30:14'),
(10, 8, 27, 'fgfdg', NULL, '2015-07-16 12:30:29'),
(11, 24, 8, 'hello', NULL, '2015-07-16 12:30:36'),
(12, 24, 8, 'fghf', NULL, '2015-07-16 12:30:44'),
(13, 24, 8, 'dgf', NULL, '2015-07-16 12:30:47'),
(14, 24, 1, 'alon mask told\n me something bad', NULL, '2015-07-16 12:31:25'),
(15, 24, 8, '<h1>xzcfcxf</h1>', NULL, '2015-07-16 12:31:43'),
(16, 8, 24, 'Hi Cris!', NULL, '2015-07-25 18:49:06'),
(17, 26, 24, 'Hello! How have done in your exam. today?', NULL, '2015-07-26 13:32:37'),
(18, 24, 26, 'Very bad. You know I am very weak in Mathematics.', NULL, '2015-07-26 13:33:39'),
(19, 26, 24, 'So I am. But I practised it as far as possible.', NULL, '2015-07-26 13:34:03'),
(20, 24, 26, 'I expected to score 60% marks but question was very stiff.', NULL, '2015-07-26 13:46:33'),
(21, 26, 24, 'I think it is. I could not work out the objective type questions.', NULL, '2015-07-26 14:00:11'),
(22, 24, 26, 'How are you?', NULL, '2015-07-26 16:36:21'),
(23, 26, 24, 'Fine, and you?', NULL, '2015-07-26 16:36:52'),
(24, 24, 8, 'Hi Elon!', NULL, '2015-07-26 16:44:06'),
(25, 8, 24, 'Go to Hell!!!!!!!!!', NULL, '2015-07-26 16:44:29'),
(26, 24, 1, 'Dear Admin! Please, ban user Elon Musk', NULL, '2015-07-26 16:45:18'),
(27, 8, 12, 'Hello Bill', NULL, '2015-07-26 22:00:18');

-- 
-- Вывод данных для таблицы raiting_user
--
INSERT INTO raiting_user VALUES
(1, 2, 5, 10, '0000-00-00 00:00:00'),
(2, 4, 1, 15, '0000-00-00 00:00:00'),
(3, 15, 4, 2, '2015-07-11 15:23:46'),
(4, 11, 23, 2, '2015-07-11 15:23:46'),
(5, 27, 20, 2, '2015-07-11 15:23:46'),
(6, 19, 20, 3, '2015-07-11 15:23:46'),
(7, 31, 19, 2, '2015-07-11 15:23:46'),
(8, 14, 26, 4, '2015-07-11 15:23:47'),
(9, 31, 6, 3, '2015-07-11 15:23:47'),
(10, 21, 5, 2, '2015-07-11 15:23:47'),
(11, 24, 12, 2, '2015-07-11 15:23:47'),
(12, 15, 22, 4, '2015-07-11 15:23:47'),
(13, 30, 18, 3, '2015-07-11 15:23:47'),
(14, 24, 13, 2, '2015-07-11 15:23:47'),
(15, 31, 13, 3, '2015-07-11 15:23:47'),
(16, 14, 5, 2, '2015-07-11 15:23:47'),
(17, 14, 20, 3, '2015-07-11 15:23:47'),
(18, 3, 24, 4, '2015-07-11 15:23:47'),
(19, 12, 20, 3, '2015-07-11 15:23:47'),
(20, 12, 22, 2, '2015-07-11 15:23:47'),
(21, 23, 31, 3, '2015-07-11 15:23:48'),
(22, 2, 23, 2, '2015-07-11 15:23:48'),
(23, 6, 21, 4, '2015-07-11 15:23:48'),
(24, 23, 20, 4, '2015-07-11 15:23:48'),
(25, 8, 18, 4, '2015-07-11 15:23:48'),
(26, 1, 8, 4, '2015-07-11 15:23:48'),
(27, 16, 8, 3, '2015-07-11 15:23:48'),
(28, 6, 18, 2, '2015-07-11 15:23:56'),
(29, 30, 12, 3, '2015-07-11 15:23:56'),
(30, 28, 2, 3, '2015-07-11 15:23:56'),
(31, 15, 3, 3, '2015-07-11 15:23:57'),
(32, 13, 16, 2, '2015-07-11 15:23:57'),
(33, 20, 15, 3, '2015-07-11 15:23:57'),
(34, 16, 17, 4, '2015-07-11 15:23:57'),
(35, 1, 15, 3, '2015-07-11 15:23:57'),
(36, 22, 4, 4, '2015-07-11 15:23:57'),
(37, 25, 20, 2, '2015-07-11 15:23:57'),
(38, 14, 16, 3, '2015-07-11 15:23:58'),
(39, 12, 26, 4, '2015-07-11 15:23:58'),
(40, 31, 8, 2, '2015-07-11 15:23:58'),
(41, 5, 15, 2, '2015-07-11 15:23:58'),
(42, 20, 4, 4, '2015-07-11 15:23:59'),
(43, 26, 22, 3, '2015-07-11 15:23:59'),
(44, 25, 21, 2, '2015-07-11 15:24:00'),
(45, 16, 13, 3, '2015-07-11 15:24:00'),
(46, 2, 3, 3, '2015-07-11 15:24:00'),
(47, 18, 11, 4, '2015-07-11 15:24:00'),
(48, 4, 30, 2, '2015-07-11 15:24:00'),
(49, 8, 24, 2, '2015-07-11 15:24:00'),
(50, 30, 16, 4, '2015-07-11 15:24:00'),
(51, 13, 30, 3, '2015-07-11 15:24:00'),
(52, 4, 6, 2, '2015-07-11 15:24:00'),
(53, 18, 16, 2, '2015-07-11 15:24:00'),
(54, 24, 14, 4, '2015-07-11 15:24:00'),
(55, 25, 5, 4, '2015-07-11 15:24:01'),
(56, 13, 1, 3, '2015-07-11 15:24:01'),
(57, 18, 23, 4, '2015-07-11 15:24:01'),
(58, 21, 23, 3, '2015-07-11 15:24:01'),
(59, 12, 14, 4, '2015-07-11 15:24:01'),
(60, 28, 6, 2, '2015-07-11 15:24:01'),
(61, 14, 6, 4, '2015-07-11 15:24:01'),
(62, 5, 8, 4, '2015-07-11 15:24:01'),
(63, 30, 26, 3, '2015-07-11 15:24:01'),
(64, 4, 13, 4, '2015-07-11 15:24:01'),
(65, 14, 18, 2, '2015-07-11 15:24:01'),
(66, 5, 23, 2, '2015-07-11 15:24:01'),
(67, 20, 30, 2, '2015-07-11 15:24:01'),
(68, 27, 15, 2, '2015-07-11 15:24:02'),
(69, 24, 27, 2, '2015-07-11 15:24:02'),
(70, 26, 3, 2, '2015-07-11 15:24:02'),
(71, 13, 22, 3, '2015-07-11 15:24:02'),
(72, 11, 27, 2, '2015-07-11 15:24:02'),
(73, 25, 28, 2, '2015-07-11 15:24:02'),
(74, 21, 22, 2, '2015-07-11 15:24:02'),
(75, 15, 16, 4, '2015-07-11 15:24:02'),
(76, 1, 26, 2, '2015-07-11 15:24:02'),
(77, 1, 22, 4, '2015-07-11 15:24:02'),
(78, 1, 31, 2, '2015-07-11 15:24:02'),
(79, 31, 28, 2, '2015-07-11 15:24:02'),
(80, 25, 19, 3, '2015-07-11 15:24:02'),
(81, 28, 4, 3, '2015-07-11 15:24:02'),
(82, 30, 15, 3, '2015-07-11 15:24:02'),
(83, 15, 13, 3, '2015-07-11 15:24:02'),
(84, 20, 18, 2, '2015-07-11 15:24:03'),
(85, 8, 19, 3, '2015-07-11 15:24:03'),
(86, 6, 13, 3, '2015-07-11 15:24:03'),
(87, 21, 11, 2, '2015-07-11 15:24:03'),
(88, 27, 25, 4, '2015-07-11 15:24:03'),
(89, 30, 8, 2, '2015-07-11 15:24:03'),
(90, 28, 31, 4, '2015-07-11 15:24:03'),
(91, 30, 27, 2, '2015-07-11 15:24:03'),
(92, 31, 16, 4, '2015-07-11 15:24:03'),
(93, 21, 17, 3, '2015-07-11 15:24:03'),
(94, 31, 15, 2, '2015-07-11 15:24:03'),
(95, 23, 3, 2, '2015-07-11 15:24:03'),
(96, 23, 5, 2, '2015-07-11 15:24:03'),
(97, 19, 16, 3, '2015-07-11 15:24:03'),
(98, 26, 23, 3, '2015-07-11 15:24:03'),
(99, 2, 16, 2, '2015-07-11 15:24:03'),
(100, 11, 24, 4, '2015-07-11 15:24:03'),
(101, 19, 15, 2, '2015-07-11 15:24:03'),
(102, 15, 6, 3, '2015-07-11 15:24:03'),
(103, 8, 11, 4, '2015-07-16 01:00:52'),
(104, 8, 27, 3, '2015-07-16 06:42:04'),
(105, 24, 8, 4, '2015-07-16 09:42:19'),
(106, 8, 13, 5, '2015-07-16 12:32:44'),
(107, 24, 2, 5, '2015-08-02 17:47:02');

-- 
-- Вывод данных для таблицы service
--
INSERT INTO service VALUES
(1, 2, 'Excursion', 200, '0000-00-00 00:00:00', NULL, 0, 1),
(3, 2, 'Bus seats', 20, '2015-06-24 12:01:50', '', 0, 1),
(4, 2, 'Excursion', 50, '2015-06-24 12:02:22', 'An proffecional excursion on museum', 0, 0),
(5, 2, 'Bus seats', 10, '2015-06-24 12:02:37', '', 0, 0),
(6, 2, 'Room in hostel', 50, '2015-06-24 12:04:24', '', 0, 0),
(7, 2, 'Ticket in train', 20, '2015-07-03 20:36:16', '', 0, 0),
(8, 2, 'hotel', 50, '2015-07-03 20:49:09', '', 0, 1),
(9, 2, 'Room in hotel', 50, '2015-07-04 12:49:36', 'room in luxury hotel', 0, 1),
(10, 2, 'Excursion on museum', 55, '2015-07-05 00:33:57', 'An professional excursion on museum', 1, 0),
(11, 2, 'Four Season Resort', 25, '2015-07-08 10:00:32', 'saa', 1, 0),
(12, 2, 'Bus seats', 2, '2015-07-08 10:01:12', '', 1, 0),
(13, 2, 'Four Season Resort', 25, '2015-07-08 10:11:18', '', 1, 0),
(14, 2, 'Excursion ssasa', 50, '2015-07-08 10:11:23', 'An proffecional excursion on museum', 1, 0),
(15, 2, '212', 22, '2015-07-08 10:14:17', '', 1, 0),
(16, 2, '', 0, '2015-07-08 11:01:07', '', 1, 0),
(17, 2, 's', 0, '2015-07-08 11:03:35', '', 1, 0),
(18, 2, 'Taxi', 40, '2015-07-15 20:56:49', 'after party taxi to home', 1, 0),
(19, 2, 'Taxi', 55, '2015-07-15 21:00:52', 'After party taxi to home', 1, 0),
(20, 2, 'Bed', 15, '2015-07-15 21:03:43', 'Bed for sleep', 0, 1),
(21, 2, 'Ticket in train', 20, '2015-07-15 22:33:28', 'Service for tickets', 1, 0),
(22, 2, 'Ticket in train', 20, '2015-07-15 22:35:54', 'Service for tickets', 1, 0),
(23, 2, 'Mat for YOGA', 15, '2015-07-15 22:56:27', 'Foam or canvas mat', 1, 0),
(24, 2, 'Socks', 7, '2015-07-15 22:57:24', 'Canvas or palyester socks', 1, 0),
(25, 2, 'Towel', 14, '2015-07-15 22:58:07', 'Towel for bathing', 1, 0),
(26, 2, 'Food', 6, '2015-07-15 23:09:52', 'Fruits, vegetables and drinks', 0, 1),
(27, 2, 'Food', 7, '2015-07-15 23:10:16', 'Fruits, vegetables and drinks', 1, 0),
(28, 2, 'Ð¡ÑÐµÐ¿Ð°Ð½', 123, '2015-07-16 11:11:22', '', 0, 1),
(29, 2, 'Bus seats', 15, '2015-07-16 12:38:42', '', 1, 0),
(30, 2, 'ticket', 12, '2015-07-16 12:40:43', '', 0, 1),
(31, 2, 'Dinner in the cafe', 10, '2015-08-02 17:30:40', '', 0, 0),
(32, 2, 'Dinner in the cafe', 15, '2015-08-02 17:32:22', '', 1, 0);

-- 
-- Вывод данных для таблицы user_language
--
INSERT INTO user_language VALUES
(1, 1, 3),
(3, 1, 4),
(4, 8, 2),
(5, 8, 4),
(6, 8, 6),
(8, 24, 2),
(9, 24, 3),
(10, 24, 5),
(11, 25, 2),
(12, 25, 4),
(14, 8, 8),
(15, 24, 7);

-- 
-- Вывод данных для таблицы user_logining
--
INSERT INTO user_logining VALUES
(1, 2, '2015-07-03 11:13:28'),
(2, 2, '2015-07-03 11:16:06'),
(3, 2, '2015-07-03 11:26:35'),
(4, 2, '2015-07-03 11:31:47'),
(5, 2, '2015-07-03 11:33:21'),
(6, 2, '2015-07-03 11:34:31'),
(7, 2, '2015-07-03 11:34:56'),
(8, 2, '2015-07-03 11:37:00'),
(9, 2, '2015-07-03 11:38:35'),
(10, 2, '2015-07-03 11:45:58'),
(11, 2, '2015-07-03 11:48:29'),
(12, 2, '2015-07-03 11:54:06'),
(13, 2, '2015-07-03 12:02:38'),
(14, 2, '2015-07-03 12:05:25'),
(15, 2, '2015-07-03 14:32:31'),
(16, 2, '2015-07-03 14:44:42'),
(17, 2, '2015-07-03 14:45:46'),
(18, 2, '2015-07-03 14:50:31'),
(19, 2, '2015-07-03 14:56:38'),
(20, 2, '2015-07-03 14:57:15'),
(21, 2, '2015-07-03 14:59:43'),
(22, 2, '2015-07-03 15:39:50'),
(23, 2, '2015-07-03 15:45:16'),
(24, 2, '2015-07-03 15:54:47'),
(25, 2, '2015-07-03 16:11:18'),
(26, 2, '2015-07-03 20:35:34'),
(27, 2, '2015-07-03 20:45:11'),
(28, 2, '2015-07-04 08:54:27'),
(29, 2, '2015-07-04 09:13:26'),
(30, 2, '2015-07-04 09:14:36'),
(31, 2, '2015-07-04 09:23:59'),
(32, 2, '2015-07-04 09:27:37'),
(33, 2, '2015-07-04 09:37:10'),
(34, 2, '2015-07-04 10:44:21'),
(35, 2, '2015-07-04 12:01:07'),
(36, 2, '2015-07-04 12:22:00'),
(37, 2, '2015-07-04 12:24:11'),
(38, 2, '2015-07-04 12:25:35'),
(39, 2, '2015-07-04 12:28:45'),
(40, 2, '2015-07-04 12:40:05'),
(41, 2, '2015-07-04 12:41:01'),
(42, 2, '2015-07-04 12:44:21'),
(43, 2, '2015-07-04 12:45:25'),
(44, 2, '2015-07-04 12:47:16'),
(45, 2, '2015-07-04 12:48:10'),
(46, 2, '2015-07-04 12:48:40'),
(47, 2, '2015-07-04 13:17:20'),
(48, 2, '2015-07-04 13:43:49'),
(49, 2, '2015-07-04 13:50:40'),
(50, 2, '2015-07-04 13:54:14'),
(51, 2, '2015-07-04 14:47:50'),
(52, 2, '2015-07-04 14:49:00'),
(53, 2, '2015-07-04 14:52:24'),
(54, 2, '2015-07-04 14:54:35'),
(55, 2, '2015-07-04 14:55:55'),
(56, 2, '2015-07-04 14:56:38'),
(57, 2, '2015-07-04 14:57:48'),
(58, 2, '2015-07-04 14:58:53'),
(59, 2, '2015-07-04 15:00:53'),
(60, 2, '2015-07-05 00:31:12'),
(61, 2, '2015-07-05 00:33:41'),
(62, 8, '2015-07-07 11:47:59'),
(63, 24, '2015-07-07 12:02:24'),
(64, 2, '2015-07-07 22:59:20'),
(65, 2, '2015-07-07 23:06:12'),
(66, 2, '2015-07-07 23:39:58'),
(67, 2, '2015-07-08 09:41:38'),
(68, 2, '2015-07-08 10:04:20'),
(69, 2, '2015-07-08 10:07:07'),
(70, 2, '2015-07-08 10:10:56'),
(71, 2, '2015-07-08 10:13:56'),
(72, 2, '2015-07-08 10:39:27'),
(73, 2, '2015-07-08 11:10:34'),
(74, 2, '2015-07-08 13:02:15'),
(75, 2, '2015-07-08 13:10:01'),
(76, 2, '2015-07-08 13:14:02'),
(77, 2, '2015-07-08 13:15:20'),
(78, 2, '2015-07-08 13:46:02'),
(79, 2, '2015-07-08 13:48:30'),
(80, 2, '2015-07-08 13:53:13'),
(81, 2, '2015-07-08 13:57:28'),
(82, 2, '2015-07-08 14:17:37'),
(83, 2, '2015-07-08 14:19:33'),
(84, 2, '2015-07-08 14:28:45'),
(85, 2, '2015-07-08 14:29:29'),
(86, 2, '2015-07-08 14:30:18'),
(87, 2, '2015-07-08 14:31:31'),
(88, 2, '2015-07-08 14:32:53'),
(89, 2, '2015-07-08 14:34:52'),
(90, 2, '2015-07-08 14:35:40'),
(91, 2, '2015-07-08 14:48:50'),
(92, 2, '2015-07-08 14:50:23'),
(93, 2, '2015-07-08 14:57:52'),
(94, 2, '2015-07-08 15:00:07'),
(95, 2, '2015-07-08 15:53:37'),
(96, 2, '2015-07-08 16:00:26'),
(97, 2, '2015-07-08 16:12:53'),
(98, 2, '2015-07-08 17:36:46'),
(99, 2, '2015-07-08 17:41:06'),
(100, 2, '2015-07-08 18:25:20'),
(101, 2, '2015-07-08 18:42:54'),
(102, 2, '2015-07-08 18:46:21'),
(103, 2, '2015-07-08 18:46:57'),
(104, 2, '2015-07-08 20:49:12'),
(105, 2, '2015-07-08 21:16:25'),
(106, 2, '2015-07-08 21:18:37'),
(107, 1, '2015-07-08 21:18:53'),
(108, 2, '2015-07-08 21:36:29'),
(109, 2, '2015-07-08 21:38:49'),
(110, 2, '2015-07-08 21:40:11'),
(111, 2, '2015-07-09 13:02:04'),
(112, 1, '2015-07-09 13:02:25'),
(113, 2, '2015-07-09 13:13:34'),
(114, 2, '2015-07-09 13:36:54'),
(115, 2, '2015-07-09 14:01:56'),
(116, 2, '2015-07-09 18:37:10'),
(117, 2, '2015-07-09 18:52:24'),
(118, 2, '2015-07-09 19:07:22'),
(119, 2, '2015-07-09 20:01:30'),
(120, 2, '2015-07-09 20:46:13'),
(121, 2, '2015-07-10 12:00:09'),
(122, 2, '2015-07-10 12:18:26'),
(123, 2, '2015-07-10 12:35:01'),
(124, 2, '2015-07-10 12:38:10'),
(125, 2, '2015-07-10 15:12:45'),
(126, 2, '2015-07-10 15:37:05'),
(127, 2, '2015-07-10 15:46:31'),
(128, 2, '2015-07-10 15:56:39'),
(129, 2, '2015-07-10 20:40:37'),
(130, 2, '2015-07-11 12:34:59'),
(131, 2, '2015-07-11 13:19:29'),
(132, 2, '2015-07-11 13:20:43'),
(133, 2, '2015-07-11 14:53:41'),
(134, 2, '2015-07-11 14:58:28'),
(135, 2, '2015-07-11 15:01:08'),
(136, 1, '2015-07-11 15:01:30'),
(137, 2, '2015-07-11 15:03:06'),
(138, 2, '2015-07-11 15:04:34'),
(139, 8, '2015-07-11 15:13:53'),
(140, 2, '2015-07-11 17:04:15'),
(141, 2, '2015-07-11 17:23:14'),
(142, 2, '2015-07-11 17:26:26'),
(143, 2, '2015-07-11 18:06:35'),
(144, 2, '2015-07-11 18:07:35'),
(145, 2, '2015-07-11 18:54:48'),
(146, 2, '2015-07-11 18:59:29'),
(147, 2, '2015-07-11 19:03:34'),
(148, 8, '2015-07-12 14:27:39'),
(149, 8, '2015-07-12 14:43:13'),
(150, 2, '2015-07-15 11:51:22'),
(151, 2, '2015-07-15 12:13:59'),
(152, 32, '2015-07-15 12:35:52'),
(153, 2, '2015-07-15 12:43:37'),
(154, 8, '2015-07-15 12:49:13'),
(155, 32, '2015-07-15 12:49:24'),
(156, 8, '2015-07-15 12:50:25'),
(157, 2, '2015-07-15 12:50:46'),
(158, 26, '2015-07-15 12:50:55'),
(159, 28, '2015-07-15 12:56:22'),
(160, 2, '2015-07-15 13:19:46'),
(161, 28, '2015-07-15 13:57:49'),
(162, 2, '2015-07-15 14:00:26'),
(163, 8, '2015-07-15 14:06:26'),
(164, 2, '2015-07-15 14:15:07'),
(165, 8, '2015-07-15 19:03:44'),
(166, 8, '2015-07-15 19:04:12'),
(167, 2, '2015-07-15 19:04:45'),
(168, 17, '2015-07-15 19:20:23'),
(169, 8, '2015-07-15 19:28:43'),
(170, 17, '2015-07-15 19:29:31'),
(171, 20, '2015-07-15 19:31:57'),
(172, 19, '2015-07-15 19:43:05'),
(173, 24, '2015-07-15 19:49:28'),
(174, 27, '2015-07-15 19:54:58'),
(175, 28, '2015-07-15 20:01:06'),
(176, 22, '2015-07-15 20:04:34'),
(177, 21, '2015-07-15 20:10:32'),
(178, 8, '2015-07-15 20:12:04'),
(179, 18, '2015-07-15 20:12:45'),
(180, 8, '2015-07-15 20:17:41'),
(181, 24, '2015-07-15 20:21:48'),
(182, 25, '2015-07-15 20:32:15'),
(183, 25, '2015-07-15 20:33:16'),
(184, 23, '2015-07-15 20:33:51'),
(185, 20, '2015-07-15 20:34:57'),
(186, 19, '2015-07-15 20:35:46'),
(187, 24, '2015-07-15 20:36:17'),
(188, 18, '2015-07-15 20:38:43'),
(189, 17, '2015-07-15 20:39:46'),
(190, 24, '2015-07-15 20:45:06'),
(191, 2, '2015-07-15 20:51:33'),
(192, 2, '2015-07-15 20:59:06'),
(193, 8, '2015-07-15 22:27:30'),
(194, 2, '2015-07-15 22:31:56'),
(195, 8, '2015-07-15 22:34:23'),
(196, 2, '2015-07-15 22:35:03'),
(197, 8, '2015-07-15 22:36:22'),
(198, 18, '2015-07-15 22:40:46'),
(199, 22, '2015-07-15 22:42:55'),
(200, 24, '2015-07-15 22:43:29'),
(201, 2, '2015-07-15 22:45:02'),
(202, 24, '2015-07-15 22:46:52'),
(203, 2, '2015-07-15 22:48:29'),
(204, 20, '2015-07-15 23:06:32'),
(205, 2, '2015-07-15 23:08:41'),
(206, 8, '2015-07-15 23:13:54'),
(207, 8, '2015-07-15 23:20:23'),
(208, 8, '2015-07-16 00:04:30'),
(209, 8, '2015-07-16 00:53:05'),
(210, 8, '2015-07-16 00:55:43'),
(211, 8, '2015-07-16 01:17:27'),
(212, 8, '2015-07-16 01:24:36'),
(213, 8, '2015-07-16 02:17:43'),
(214, 24, '2015-07-16 02:20:30'),
(215, 24, '2015-07-16 02:22:41'),
(216, 24, '2015-07-16 02:22:42'),
(217, 24, '2015-07-16 02:27:27'),
(218, 1, '2015-07-16 02:28:27'),
(219, 8, '2015-07-16 02:33:27'),
(220, 8, '2015-07-16 03:13:33'),
(221, 23, '2015-07-16 03:25:02'),
(222, 8, '2015-07-16 03:25:36'),
(223, 23, '2015-07-16 05:50:03'),
(224, 8, '2015-07-16 06:17:11'),
(225, 8, '2015-07-16 06:33:44'),
(226, 8, '2015-07-16 06:35:46'),
(227, 25, '2015-07-16 06:44:57'),
(228, 25, '2015-07-16 06:58:53'),
(229, 8, '2015-07-16 07:03:43'),
(230, 8, '2015-07-16 07:13:12'),
(231, 8, '2015-07-16 09:07:22'),
(232, 21, '2015-07-16 09:21:10'),
(233, 25, '2015-07-16 09:22:56'),
(234, 8, '2015-07-16 09:26:14'),
(235, 8, '2015-07-16 09:29:51'),
(236, 24, '2015-07-16 09:30:55'),
(237, 8, '2015-07-16 09:56:08'),
(238, 8, '2015-07-16 10:03:38'),
(239, 8, '2015-07-16 10:08:46'),
(240, 8, '2015-07-16 10:08:55'),
(241, 8, '2015-07-16 10:47:00'),
(242, 1, '2015-07-16 10:49:04'),
(243, 25, '2015-07-16 10:50:34'),
(244, 1, '2015-07-16 10:51:20'),
(245, 1, '2015-07-16 10:51:45'),
(246, 19, '2015-07-16 10:52:42'),
(247, 24, '2015-07-16 10:55:58'),
(248, 8, '2015-07-16 10:59:55'),
(249, 2, '2015-07-16 11:04:56'),
(250, 1, '2015-07-16 11:08:36'),
(251, 2, '2015-07-16 11:08:59'),
(252, 2, '2015-07-16 11:40:17'),
(253, 8, '2015-07-16 11:45:23'),
(254, 8, '2015-07-16 12:09:38'),
(255, 24, '2015-07-16 12:28:40'),
(256, 2, '2015-07-16 12:37:59'),
(257, 1, '2015-07-16 12:42:56'),
(258, 1, '2015-07-22 11:26:17'),
(259, 8, '2015-07-25 18:08:40'),
(260, 8, '2015-07-26 12:53:34'),
(261, 8, '2015-07-26 12:54:00'),
(262, 8, '2015-07-26 12:55:06'),
(263, 25, '2015-07-26 12:55:57'),
(264, 24, '2015-07-26 13:00:08'),
(265, 26, '2015-07-26 13:01:12'),
(266, 24, '2015-07-26 13:01:24'),
(267, 26, '2015-07-26 13:24:20'),
(268, 24, '2015-07-26 14:41:21'),
(269, 26, '2015-07-26 14:41:38'),
(270, 8, '2015-07-26 14:56:47'),
(271, 24, '2015-07-26 15:14:58'),
(272, 19, '2015-07-26 15:17:32'),
(273, 24, '2015-07-26 15:18:56'),
(274, 8, '2015-07-26 15:23:08'),
(275, 24, '2015-07-26 15:23:49'),
(276, 19, '2015-07-26 15:26:02'),
(277, 24, '2015-07-26 15:26:43'),
(278, 24, '2015-07-26 15:29:25'),
(279, 24, '2015-07-26 15:33:38'),
(280, 24, '2015-07-26 15:34:33'),
(281, 24, '2015-07-26 15:37:16'),
(282, 24, '2015-07-26 16:21:36'),
(283, 24, '2015-07-26 16:23:20'),
(284, 26, '2015-07-26 16:31:27'),
(285, 8, '2015-07-26 16:40:04'),
(286, 8, '2015-07-26 21:58:25'),
(287, 1, '2015-07-26 22:06:15'),
(288, 25, '2015-07-26 22:12:40'),
(289, 26, '2015-07-26 22:48:28'),
(290, 8, '2015-07-27 13:14:40'),
(291, 26, '2015-07-27 13:15:27'),
(292, 8, '2015-07-27 13:22:39'),
(293, 8, '2015-07-27 13:31:01'),
(294, 24, '2015-07-27 13:35:21'),
(295, 46, '2015-07-27 14:10:45'),
(296, 47, '2015-07-27 14:59:29'),
(297, 18, '2015-07-27 19:24:20'),
(298, 25, '2015-07-27 19:33:58'),
(299, 24, '2015-07-27 19:35:06'),
(300, 8, '2015-07-27 19:52:25'),
(301, 47, '2015-07-27 20:14:38'),
(302, 26, '2015-07-27 20:53:35'),
(303, 2, '2015-08-02 13:01:56'),
(304, 24, '2015-08-02 15:00:32'),
(305, 1, '2015-08-02 15:04:19'),
(306, 2, '2015-08-02 15:07:38'),
(307, 26, '2015-08-02 15:08:23'),
(308, 2, '2015-08-02 15:10:45'),
(309, 8, '2015-08-02 15:12:47'),
(310, 2, '2015-08-02 15:16:55'),
(311, 24, '2015-08-02 15:18:17'),
(312, 24, '2015-08-02 15:18:17'),
(313, 2, '2015-08-02 15:20:22'),
(314, 2, '2015-08-02 15:42:51'),
(315, 2, '2015-08-02 15:49:37'),
(316, 2, '2015-08-02 16:58:55'),
(317, 2, '2015-08-02 17:23:51'),
(318, 2, '2015-08-02 17:28:48'),
(319, 24, '2015-08-02 17:35:02'),
(320, 1, '2015-08-02 21:56:34'),
(321, 1, '2015-08-02 21:57:01'),
(322, 1, '2015-08-02 21:57:28'),
(323, 1, '2015-08-09 11:51:39'),
(324, 8, '2015-08-09 11:54:03'),
(325, 1, '2015-08-09 12:00:24'),
(326, 8, '2015-08-09 12:02:11'),
(327, 1, '2015-08-09 13:31:21'),
(328, 1, '2015-11-07 12:23:49'),
(329, 2, '2015-11-07 12:24:54'),
(330, 2, '2015-11-07 12:36:51'),
(331, 1, '2015-11-07 13:34:04'),
(332, 1, '2015-11-07 13:57:54'),
(333, 1, '2015-11-07 14:11:38'),
(334, 1, '2015-11-07 14:17:01'),
(335, 1, '2015-11-07 14:21:25'),
(336, 1, '2015-11-07 14:23:59'),
(337, 1, '2015-11-07 14:27:11'),
(338, 1, '2015-11-07 15:35:48'),
(339, 1, '2015-11-07 16:14:22'),
(340, 1, '2015-11-07 16:27:53'),
(341, 1, '2015-11-07 16:33:07'),
(342, 1, '2015-11-07 16:40:40'),
(343, 1, '2015-11-07 16:43:28'),
(344, 1, '2015-11-07 16:47:34'),
(345, 1, '2015-11-07 16:51:52'),
(346, 1, '2015-11-07 17:45:04'),
(347, 1, '2015-11-07 17:52:21');

-- 
-- Вывод данных для таблицы user_tag
--
INSERT INTO user_tag VALUES
(1, 2, 2, '0000-00-00 00:00:00'),
(2, 4, 4, '0000-00-00 00:00:00'),
(3, 2, 3, '0000-00-00 00:00:00'),
(4, 1, 1, '0000-00-00 00:00:00'),
(5, 6, 3, '0000-00-00 00:00:00'),
(6, 28, 17, '2015-06-23 17:33:11'),
(7, 28, 16, '2015-06-23 17:33:19'),
(8, 28, 15, '2015-06-23 17:33:28'),
(9, 27, 5, '2015-06-23 17:33:40'),
(10, 27, 6, '2015-06-23 17:33:47'),
(11, 26, 5, '2015-06-23 17:33:54'),
(12, 26, 4, '2015-06-23 17:34:00'),
(13, 26, 4, '2015-06-23 17:34:09'),
(14, 21, 1, '2015-06-23 17:34:22'),
(15, 21, 2, '2015-06-23 17:34:29'),
(16, 21, 8, '2015-06-23 17:34:38'),
(17, 21, 9, '2015-06-23 17:34:45'),
(18, 19, 6, '2015-06-23 17:35:17'),
(19, 19, 7, '2015-06-23 17:35:17'),
(20, 19, 10, '2015-06-23 17:35:17'),
(21, 18, 8, '2015-06-23 17:35:27'),
(22, 18, 6, '2015-06-23 17:35:34'),
(23, 18, 13, '2015-06-23 17:35:41'),
(24, 17, 7, '2015-06-23 17:35:58'),
(25, 17, 11, '2015-06-23 17:36:06'),
(26, 17, 16, '2015-06-23 17:36:17'),
(27, 17, 18, '2015-06-23 17:36:23'),
(28, 24, 1, '2015-06-23 17:37:16'),
(29, 24, 2, '2015-06-23 17:37:16'),
(30, 24, 18, '2015-06-23 17:37:16'),
(31, 24, 17, '2015-06-23 17:37:16'),
(32, 24, 9, '2015-06-23 17:37:16'),
(33, 23, 10, '2015-06-23 17:37:34'),
(34, 23, 15, '2015-06-23 17:37:41'),
(35, 23, 18, '2015-06-23 17:37:47'),
(36, 8, 15, '2015-07-07 11:48:46'),
(37, 8, 14, '2015-07-07 11:48:47'),
(38, 8, 13, '2015-07-07 11:48:48'),
(39, 8, 7, '2015-07-07 11:48:49'),
(44, 8, 5, '2015-07-16 09:36:18'),
(45, 8, 2, '2015-07-16 09:36:21'),
(46, 8, 10, '2015-07-16 12:24:25'),
(47, 8, 16, '2015-07-16 12:24:28'),
(48, 24, 7, '2015-07-26 16:55:55'),
(49, 24, 6, '2015-07-26 16:55:59'),
(50, 8, 9, '2015-07-26 22:01:10');

-- 
-- Вывод данных для таблицы wallet
--
INSERT INTO wallet VALUES
(1, 32, 0),
(2, 35, 0),
(3, 36, 0),
(4, 37, 0),
(5, 38, 0),
(6, 39, 0),
(7, 40, 0),
(8, 41, 0),
(9, 42, 0),
(10, 43, 0),
(11, 46, 0),
(12, 47, 0),
(13, 48, 0);

-- 
-- Вывод данных для таблицы withdrawmoney
--
INSERT INTO withdrawmoney VALUES
(2, 2, 25, '2015-07-11 18:51:41'),
(3, 2, 25, '2015-07-15 22:49:12'),
(4, 2, 29, '2015-07-15 22:49:21'),
(5, 2, 50, '2015-07-16 11:10:23'),
(6, 2, 50, '2015-07-16 12:40:26'),
(7, 2, 100, '2015-08-02 15:50:13'),
(8, 2, 50, '2015-08-02 17:39:07');

-- 
-- Вывод данных для таблицы comment_event
--
INSERT INTO comment_event VALUES
(1, '2015-06-08 14:09:32', 1, 4, 'Класний клуб, все на вищому рівні!', '2015-06-17 09:18:12'),
(4, '2015-06-08 16:10:30', 3, 4, 'Не мій рівень! в порівняні з клубом Рай в москві ваще не айс !', '2015-06-17 09:18:12'),
(5, '2015-06-10 14:11:45', 4, 2, 'Дужу красивий театр ', '2015-06-17 09:18:12'),
(6, '2015-06-08 14:13:29', 4, 2, 'Доречі скоро організовую екскурсію туди, шукайте цю подію на головній і приєднюйться, задавайте запитання ,постраюсь всім відповісти', '2015-06-17 09:18:12'),
(7, NULL, 8, 1, 'Привіт мені дуже сподобалася екскурсія!!! Vau Vau Vau!!!', '2015-06-19 11:54:55'),
(19, NULL, 8, 3, 'dgfadsgs', '2015-07-12 14:40:15'),
(20, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:18'),
(21, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:18'),
(22, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:18'),
(23, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(24, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(25, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(26, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(27, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(28, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(29, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(30, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:19'),
(31, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(32, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(33, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(34, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(35, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(36, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(37, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(38, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(39, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:20'),
(40, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:21'),
(41, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:21'),
(42, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:21'),
(43, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:21'),
(44, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:21'),
(45, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:22'),
(46, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:22'),
(47, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:22'),
(48, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:22'),
(49, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:22'),
(50, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:23'),
(51, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:23'),
(52, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:23'),
(53, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:23'),
(54, NULL, 8, 3, 'sgdagsdags', '2015-07-12 14:40:23'),
(55, NULL, 2, 8, 'look forward to!)', '2015-07-15 19:16:40'),
(56, NULL, 17, 10, 'it will be good event!)', '2015-07-15 19:29:53'),
(57, NULL, 20, 19, 'i hope it will be interested!))', '2015-07-15 19:38:04'),
(58, NULL, 20, 18, 'cool ping-pong)', '2015-07-15 19:39:16'),
(59, NULL, 20, 18, 'i like sport!)', '2015-07-15 19:39:28'),
(60, NULL, 20, 20, 'nature!)) i like it!)', '2015-07-15 19:41:42'),
(61, NULL, 20, 21, 'i like it!)', '2015-07-15 19:42:42'),
(62, NULL, 19, 14, 'i hope ', '2015-07-15 19:45:06'),
(63, NULL, 19, 17, 'Good!)) ', '2015-07-15 19:45:58'),
(64, NULL, 24, 36, 'nature!)) i like it!)', '2015-07-15 19:51:13'),
(65, NULL, 24, 38, 'Good!)) ', '2015-07-15 19:51:53'),
(66, NULL, 24, 39, 'Great!)', '2015-07-15 19:52:18'),
(67, NULL, 24, 40, 'i wait for it!)', '2015-07-15 19:52:46'),
(68, NULL, 27, 49, 'i wait for it!!))', '2015-07-15 19:57:49'),
(69, NULL, 27, 47, 'i like trips!)', '2015-07-15 19:58:38'),
(70, NULL, 22, 26, 'Good!)) ', '2015-07-15 20:06:17'),
(71, NULL, 22, 27, 'Great!)', '2015-07-15 20:06:48'),
(72, NULL, 22, 29, 'it will be cool!)', '2015-07-15 20:07:14'),
(73, NULL, 22, 30, 'Good!)) ', '2015-07-15 20:08:14'),
(74, NULL, 18, 13, 'i like it!)0', '2015-07-15 20:13:07'),
(75, NULL, 8, 42, 'Great!)', '2015-07-15 22:27:52'),
(76, NULL, 24, 42, 'it will be cool!)', '2015-07-15 22:47:22'),
(77, NULL, 20, 11, 'i like it!)', '2015-07-15 23:07:02'),
(78, NULL, 8, 5, 'Good!)) ', '2015-07-16 01:16:49'),
(79, NULL, 8, 5, 'Прікольна кобіта!!!', '2015-07-16 06:20:14'),
(85, NULL, 26, 5, '+100', '2015-07-27 22:03:53');

-- 
-- Вывод данных для таблицы event_tag
--
INSERT INTO event_tag VALUES
(1, 1, 1, '2015-06-23 17:09:56'),
(2, 1, 13, '2015-06-23 17:10:10'),
(3, 1, 15, '2015-06-23 17:10:24'),
(4, 2, 16, '2015-06-23 17:13:05'),
(5, 2, 17, '2015-06-23 17:13:05'),
(6, 4, 1, '2015-06-23 17:13:05'),
(7, 4, 15, '2015-06-23 17:13:05'),
(8, 8, 1, '2015-06-23 17:13:05'),
(9, 8, 6, '2015-06-23 17:13:05'),
(10, 8, 5, '2015-06-23 17:13:06'),
(11, 9, 14, '2015-06-23 17:13:27'),
(12, 9, 5, '2015-06-23 17:13:34'),
(13, 11, 6, '2015-06-23 17:13:45'),
(14, 17, 13, '2015-06-23 17:14:00'),
(15, 17, 10, '2015-06-23 17:14:09'),
(16, 22, 13, '2015-06-23 17:14:21'),
(17, 22, 15, '2015-06-23 17:14:33'),
(18, 26, 13, '2015-06-23 17:16:05'),
(19, 26, 15, '2015-06-23 17:16:05'),
(25, 21, 10, '2015-06-23 17:16:56'),
(26, 22, 15, '2015-06-23 17:17:41'),
(27, 23, 14, '2015-06-23 17:17:41'),
(28, 23, 5, '2015-06-23 17:17:41'),
(29, 24, 17, '2015-06-23 17:18:10'),
(30, 25, 10, '2015-06-23 17:18:42'),
(31, 36, 5, '2015-06-23 17:19:32'),
(32, 37, 1, '2015-06-23 17:19:45'),
(33, 37, 13, '2015-06-23 17:20:04'),
(34, 39, 18, '2015-06-23 17:20:25'),
(35, 45, 6, '2015-06-23 17:20:43'),
(36, 45, 5, '2015-06-23 17:21:01'),
(37, 45, 11, '2015-06-23 17:21:10'),
(38, 46, 11, '2015-06-23 17:21:49'),
(39, 46, 5, '2015-06-23 17:21:49'),
(40, 48, 11, '2015-06-23 17:22:06'),
(41, 19, 1, '2015-07-15 19:37:36'),
(42, 19, 17, '2015-07-15 19:37:36'),
(43, 19, 18, '2015-07-15 19:37:36'),
(44, 18, 6, '2015-07-15 19:38:47'),
(45, 20, 5, '2015-07-15 19:40:18'),
(46, 20, 6, '2015-07-15 19:40:18'),
(47, 20, 3, '2015-07-15 19:40:18'),
(48, 38, 14, '2015-07-15 19:51:45'),
(49, 49, 10, '2015-07-15 19:57:15'),
(50, 30, 7, '2015-07-15 20:08:05'),
(51, 12, 13, '2015-07-15 20:14:42'),
(67, 5, 1, '2015-07-16 11:04:27'),
(68, 5, 5, '2015-07-16 11:04:27'),
(69, 5, 4, '2015-07-16 11:04:27'),
(70, 5, 13, '2015-07-16 11:04:27'),
(71, 5, 2, '2015-07-16 11:04:27'),
(72, 42, 17, '2015-08-02 15:52:53');

-- 
-- Вывод данных для таблицы eventpay
--

-- Таблица guideme.eventpay не содержит данных

-- 
-- Вывод данных для таблицы message_event
--
INSERT INTO message_event VALUES
(2, 5, 4, 'Will be cool', '0000-00-00 00:00:00'),
(3, 8, 3, ':)))', '2015-07-12 14:33:52'),
(4, 8, 3, ':)))', '2015-07-12 14:33:52'),
(5, 8, 3, ':)))', '2015-07-12 14:33:52'),
(6, 8, 3, ':))', '2015-07-12 14:33:53'),
(7, 8, 3, ':)', '2015-07-12 14:33:53'),
(8, 8, 3, ':)', '2015-07-12 14:33:53'),
(9, 8, 3, ':))', '2015-07-12 14:33:53'),
(10, 8, 3, ':)))', '2015-07-12 14:33:53'),
(11, 8, 3, ':)))', '2015-07-12 14:33:54'),
(12, 8, 3, ':)))', '2015-07-12 14:33:54'),
(13, 8, 3, ':)', '2015-07-12 14:33:54'),
(14, 8, 3, ':)', '2015-07-12 14:33:54'),
(15, 8, 3, ':)', '2015-07-12 14:33:55'),
(16, 8, 3, ':)', '2015-07-12 14:33:55'),
(17, 8, 3, ':)', '2015-07-12 14:33:55'),
(18, 8, 3, ':)', '2015-07-12 14:33:55'),
(19, 8, 3, ':)', '2015-07-12 14:34:05'),
(20, 8, 3, ':)', '2015-07-12 14:34:05'),
(21, 8, 3, ':)', '2015-07-12 14:34:05'),
(22, 8, 3, ':)', '2015-07-12 14:34:05'),
(23, 8, 3, ':)', '2015-07-12 14:34:05'),
(24, 8, 3, ':)', '2015-07-12 14:34:05'),
(25, 8, 3, ':)', '2015-07-12 14:34:06'),
(26, 8, 3, ':)', '2015-07-12 14:34:06'),
(27, 8, 3, ':)', '2015-07-12 14:34:06'),
(28, 8, 3, ':-)', '2015-07-12 14:34:06'),
(29, 8, 3, ':-)', '2015-07-12 14:34:06'),
(30, 8, 3, ':-)', '2015-07-12 14:34:06'),
(31, 8, 3, ':-)', '2015-07-12 14:34:06'),
(32, 8, 3, ':-)', '2015-07-12 14:35:11'),
(33, 8, 3, ':-)', '2015-07-12 14:35:13'),
(34, 8, 3, ':-)', '2015-07-12 14:35:15'),
(35, 8, 3, ':-)', '2015-07-12 14:36:16'),
(36, 8, 3, ':-)', '2015-07-12 14:36:18'),
(37, 8, 8, ':-)', '2015-07-16 03:46:56'),
(38, 8, 5, 'Привіт всім!', '2015-07-16 11:00:59'),
(39, 24, 5, 'Здоров був!', '2015-07-16 11:04:18'),
(40, 24, 5, ':-)', '2015-07-16 12:36:47'),
(41, 8, 5, 'привіт', '2015-07-16 12:36:59'),
(42, 8, 49, 'Hi!', '2015-07-26 21:59:09'),
(43, 24, 5, 'Як настрій?', '2015-07-27 21:09:53'),
(44, 26, 5, 'Добре', '2015-07-27 21:10:36'),
(45, 24, 5, 'Привіт всім', '2015-07-27 21:22:23'),
(47, 26, 5, 'і тобі привіт!!!!!! )))', '2015-07-27 21:33:08'),
(48, 24, 5, 'Що завтра робимо?', '2015-07-27 22:02:17');

-- 
-- Вывод данных для таблицы rating_event
--
INSERT INTO rating_event VALUES
(1, 2, 2, 2, '0000-00-00 00:00:00'),
(2, 2, 4, 5, '0000-00-00 00:00:00'),
(3, 6, 2, 2, '0000-00-00 00:00:00'),
(4, 8, 3, 5, '2015-07-12 14:40:04'),
(5, 2, 8, 3, '2015-07-15 19:16:17'),
(6, 8, 42, 5, '2015-07-15 22:27:44'),
(7, 24, 42, 4, '2015-07-15 22:47:13'),
(8, 20, 11, 4, '2015-07-15 23:06:56'),
(9, 8, 5, 5, '2015-07-16 01:16:45'),
(10, 8, 8, 5, '2015-07-25 18:20:03'),
(11, 26, 5, 5, '2015-07-27 21:10:57');

-- 
-- Вывод данных для таблицы service_in_event
--
INSERT INTO service_in_event VALUES
(4, NULL, NULL, 4, 42, -1, '2015-07-05 00:28:25', 1, 1),
(5, NULL, NULL, 10, 42, -1, '2015-07-05 00:33:57', 1, 1),
(6, NULL, NULL, 4, 42, -1, '2015-07-09 19:51:28', 0, 0),
(7, NULL, NULL, 5, 42, -1, '2015-07-11 19:04:03', 1, 0),
(8, NULL, NULL, 7, 42, -1, '2015-07-11 19:07:05', 1, 0),
(9, '2015-07-20 02:03:00', NULL, 18, 42, -1, '2015-07-15 20:56:49', 0, 0),
(10, '2015-07-17 01:09:00', '2015-07-18 00:26:00', 19, 42, -1, '2015-07-15 21:00:52', 1, 0),
(11, NULL, NULL, 20, 42, -1, '2015-07-15 21:04:08', 0, 0),
(12, NULL, NULL, 21, 42, -1, '2015-07-15 22:33:28', 1, 0),
(13, NULL, NULL, 22, 42, -1, '2015-07-15 22:35:54', 0, 0),
(14, NULL, NULL, 23, 11, -1, '2015-07-15 22:56:27', 0, 1),
(15, NULL, NULL, 24, 11, -1, '2015-07-15 22:57:24', 0, 1),
(16, NULL, NULL, 25, 11, -1, '2015-07-15 22:58:07', 0, 0),
(17, NULL, NULL, 27, 42, -1, '2015-07-15 23:10:16', 0, 1),
(18, NULL, NULL, 29, 42, -1, '2015-07-16 12:38:42', 0, 0),
(19, NULL, NULL, 30, 11, -1, '2015-07-16 12:41:03', 0, 1),
(20, NULL, NULL, 32, 42, -1, '2015-08-02 17:32:22', 0, 0);

-- 
-- Вывод данных для таблицы user_in_event
--
INSERT INTO user_in_event VALUES
(2, 5, 4, 'guest', 2, 4, 1, 1, '0000-00-00 00:00:00'),
(3, 3, 1, 'resident', 0, 0, 0, 1, '0000-00-00 00:00:00'),
(4, 8, 1, 'resident', 0, 0, 0, 1, '0000-00-00 00:00:00'),
(5, 8, 2, 'resident', 0, 0, 0, 0, '0000-00-00 00:00:00'),
(6, 8, 3, 'resident', 0, 0, 0, 1, '0000-00-00 00:00:00'),
(14, 2, 8, 'resident', 0, 0, 0, 1, '2015-06-29 14:58:12'),
(17, 1, 3, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(19, 2, 42, 'resident', 1, 0, 0, 1, '2015-07-04 17:35:20'),
(20, 4, 2, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(21, 6, 4, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(22, 17, 6, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(23, 17, 7, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(24, 17, 8, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(25, 17, 9, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(26, 17, 10, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(27, 2, 11, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(28, 18, 12, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(29, 18, 13, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(30, 19, 14, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(31, 19, 15, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(32, 19, 16, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(33, 19, 17, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(34, 20, 18, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(35, 20, 19, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(36, 20, 20, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(37, 20, 21, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(38, 21, 22, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(39, 21, 23, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(40, 21, 24, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(41, 21, 25, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(42, 22, 26, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(43, 22, 27, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(44, 22, 28, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(45, 22, 29, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(46, 22, 30, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(47, 23, 31, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(48, 23, 32, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(49, 23, 33, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(50, 23, 34, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(51, 23, 35, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(52, 24, 36, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(53, 24, 37, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(54, 24, 38, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(55, 24, 39, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(56, 24, 40, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(57, 24, 41, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(58, 25, 43, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(59, 25, 44, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(60, 25, 45, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(61, 26, 46, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(62, 27, 47, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(63, 27, 48, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(64, 27, 49, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(65, 28, 50, 'resident', 0, 0, 0, 1, '2015-07-04 17:35:20'),
(66, 5, 4, 'guest', -3, 0, 0, 0, '2015-07-10 12:23:52'),
(67, 1, 42, 'guest', 0, 0, 0, 1, '2015-07-11 15:02:07'),
(68, 2, 18, 'guest', 0, 0, 0, 0, '2015-07-11 19:29:32'),
(69, 3, 2, 'resident', 0, 0, 0, 1, '2015-07-15 13:49:36'),
(70, 5, 2, 'guest', 0, 0, 0, 1, '2015-07-15 13:50:09'),
(71, 8, 8, 'guest', -4, 0, 0, 1, '2015-07-15 19:01:37'),
(72, 2, 16, 'guest', -4, 0, 0, 1, '2015-07-15 19:06:21'),
(73, 2, 21, 'resident', 8, 0, 0, 0, '2015-07-15 19:06:37'),
(74, 8, 48, 'guest', -3, 0, 0, 1, '2015-07-15 19:08:34'),
(75, 2, 48, 'guest', -4, 0, 0, 0, '2015-07-15 19:12:45'),
(76, 2, 46, 'guest', -3, 0, 0, 0, '2015-07-15 19:16:03'),
(77, 8, 42, 'guest', -4, 0, 0, 1, '2015-07-15 19:18:19'),
(78, 20, 17, 'resident', 4, 0, 0, 1, '2015-07-15 19:33:42'),
(79, 19, 26, 'guest', -4, 0, 0, 1, '2015-07-15 19:46:29'),
(80, 19, 35, 'guest', 3, 0, 0, 0, '2015-07-15 19:46:52'),
(81, 19, 13, 'guest', -6, 0, 0, 1, '2015-07-15 19:47:29'),
(82, 19, 2, 'guest', 5, 0, 0, 0, '2015-07-15 19:48:02'),
(83, 19, 49, 'guest', -7, 0, 0, 1, '2015-07-15 19:48:38'),
(85, 27, 8, 'resident', 7, 0, 0, 0, '2015-07-15 19:59:39'),
(86, 27, 45, 'guest', -1, 0, 0, 0, '2015-07-15 19:59:55'),
(87, 27, 23, 'guest', -1, 0, 0, 0, '2015-07-15 20:00:22'),
(88, 27, 38, 'guest', -2, 0, 0, 0, '2015-07-15 20:00:43'),
(89, 28, 1, 'guest', -2, 0, 0, 0, '2015-07-15 20:01:58'),
(90, 28, 26, 'guest', 2, 0, 0, 1, '2015-07-15 20:02:15'),
(91, 28, 22, 'guest', -2, 0, 0, 0, '2015-07-15 20:03:02'),
(93, 28, 27, 'guest', -4, 0, 0, 1, '2015-07-15 20:04:07'),
(94, 22, 4, 'guest', -1, 0, 0, 0, '2015-07-15 20:10:12'),
(98, 19, 5, 'guest', -1, 0, 0, 1, '2015-07-15 20:36:05'),
(100, 17, 5, 'guest', 3, 0, 0, 1, '2015-07-15 20:40:07'),
(101, 18, 42, 'guest', -2, 0, 0, 0, '2015-07-15 22:42:27'),
(102, 22, 42, 'guest', 4, 0, 0, 1, '2015-07-15 22:43:14'),
(103, 24, 42, 'guest', -3, 0, 0, 1, '2015-07-15 22:44:18'),
(104, 25, 11, 'guest', -2, 0, 0, 1, '2015-07-15 23:04:28'),
(105, 20, 11, 'guest', 2, 0, 0, 1, '2015-07-15 23:04:54'),
(106, 31, 18, 'resident', 0, 0, 0, 1, '2015-07-16 09:10:58'),
(107, 31, 25, 'guest', 0, 0, 0, 1, '2015-07-16 09:11:09'),
(108, 31, 46, 'resident', 0, 0, 0, 1, '2015-07-16 09:11:27'),
(109, 31, 23, 'resident', 0, 0, 0, 0, '2015-07-16 09:11:44'),
(111, 31, 49, 'guest', 0, 0, 0, 0, '2015-07-16 09:12:14'),
(112, 31, 28, 'guest', 0, 0, 0, 0, '2015-07-16 09:13:02'),
(113, 25, 51, 'resident', -2, 0, 0, 1, '2015-07-16 09:24:54'),
(115, 31, 11, 'guest', 0, 0, 0, 1, '2015-07-16 11:09:14'),
(116, 38, 42, 'resident', 0, 0, 0, 1, '2015-07-16 11:41:38'),
(117, 8, 4, 'guest', 0, 0, 0, 0, '2015-07-16 12:10:15'),
(118, 8, 52, 'resident', 8, 0, 0, 1, '2015-07-16 12:15:31'),
(120, 24, 43, 'guest', -1, 0, 0, 0, '2015-07-26 13:02:26'),
(121, 24, 28, 'guest', 0, 0, 0, 0, '2015-07-26 13:03:17'),
(124, 24, 17, 'guest', 0, 0, 0, 0, '2015-07-26 13:21:14'),
(125, 24, 10, 'guest', 0, 0, 0, 0, '2015-07-26 13:21:17'),
(126, 24, 11, 'guest', 0, 0, 0, 0, '2015-07-26 13:21:19'),
(127, 24, 53, 'resident', 3, 0, 0, 1, '2015-07-26 16:28:57'),
(128, 8, 49, 'guest', -2, 0, 0, 0, '2015-07-26 21:58:58'),
(129, 24, 5, 'resident', 0, 0, 0, 1, '2015-07-27 20:07:27'),
(130, 47, 5, 'guest', 0, 0, 0, 1, '2015-07-27 20:14:52'),
(131, 8, 5, 'guest', -1, 0, 0, 1, '2015-07-27 20:46:59'),
(136, 26, 5, 'guest', -1, 0, 0, 1, '2015-07-27 21:59:55');

-- 
-- Вывод данных для таблицы walletupdate
--

-- Таблица guideme.walletupdate не содержит данных

-- 
-- Вывод данных для таблицы paid_service
--
INSERT INTO paid_service VALUES
(35, 2, 42, 5, '2015-07-11 13:50:13'),
(36, 3, 42, 6, '2015-06-01 13:50:49'),
(37, 2, 42, 4, '2015-07-10 14:19:46'),
(38, 2, 42, 6, '2015-07-11 14:53:57'),
(39, 1, 42, 6, '2015-07-11 15:02:33'),
(40, 2, 42, 8, '2015-07-11 19:10:10'),
(41, 24, 42, 11, '2015-07-15 22:47:53'),
(42, 24, 42, 6, '2015-07-15 22:48:05'),
(43, 20, 11, 14, '2015-07-15 23:07:41'),
(44, 20, 11, 15, '2015-07-15 23:07:41'),
(45, 31, 11, 14, '2015-07-16 12:40:14'),
(46, 31, 11, 15, '2015-07-16 12:40:14'),
(47, 31, 11, 19, '2015-07-16 12:41:14'),
(48, 24, 42, 17, '2015-08-02 15:30:27'),
(49, 24, 42, 18, '2015-08-02 15:30:27'),
(50, 24, 42, 20, '2015-08-02 17:36:23'),
(51, 24, 42, 9, '2015-08-02 17:36:23');

-- 
-- Восстановить предыдущий режим SQL (SQL mode)
-- 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

-- 
-- Включение внешних ключей
-- 
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;