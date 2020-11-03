/*
Navicat MySQL Data Transfer

Source Server         : 39.97.230.224_3306
Source Server Version : 80022
Source Host           : 39.97.230.224:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2020-11-03 09:55:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `n_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(10) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `n_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '公共告1', '11111111111', '2018-04-05 00:00:00');
INSERT INTO `notice` VALUES ('2', '公告2', '本周图书销售量再创新高', '2018-04-14 15:40:34');
INSERT INTO `notice` VALUES ('3', '公告3', '你好吗', '2018-04-14 15:42:13');
INSERT INTO `notice` VALUES ('4', null, '儿童袜无无无无无无无无拖无无无无无', '2018-04-14 15:43:34');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `product_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1a799996-f9ce-4e4c-97df-3d4ecfc20fc5', '4', '1');
INSERT INTO `orderitem` VALUES ('30843736-4de4-4cda-8dcc-7e340dd18030', '2', '2');
INSERT INTO `orderitem` VALUES ('30843736-4de4-4cda-8dcc-7e340dd18030', '7', '3');
INSERT INTO `orderitem` VALUES ('38708987-f4c8-40c4-ae68-7efd199ee034', '099fc8ff-4135-4d5a-a381-ceea24352cd8', '1');
INSERT INTO `orderitem` VALUES ('597947d4-4469-4f75-89f5-a2a0963ae6c2', '02f0a0ab-a4ee-41f8-a974-038dbfeceb48', '3');
INSERT INTO `orderitem` VALUES ('5cd3c352-0d7c-4828-9af2-9a4459fc0fbc', '2', '3');
INSERT INTO `orderitem` VALUES ('5cd3c352-0d7c-4828-9af2-9a4459fc0fbc', '6', '2');
INSERT INTO `orderitem` VALUES ('be855da0-53b0-4d00-a02d-47b2682defaf', '9e966ae9-965b-48f8-9a21-f46fee46c484', '1');
INSERT INTO `orderitem` VALUES ('c4efba9e-a864-4d94-9bb2-7e8f8926b4e1', '2', '1');
INSERT INTO `orderitem` VALUES ('d30078be-9a8e-4be2-b9f8-81240cf18196', '3', '2');
INSERT INTO `orderitem` VALUES ('d781546d-bbb5-42d2-9fd3-d75cc933c4e5', '5', '2');
INSERT INTO `orderitem` VALUES ('dfdc6f31-fa79-4710-92b2-8ebf0a91f191', '099fc8ff-4135-4d5a-a381-ceea24352cd8', '1');
INSERT INTO `orderitem` VALUES ('dfdc6f31-fa79-4710-92b2-8ebf0a91f191', '4', '3');
INSERT INTO `orderitem` VALUES ('f3f8b3f0-ba1e-4084-8c6e-0d5cf57eda18', '3', '7');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `paystate` int DEFAULT '0',
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1a799996-f9ce-4e4c-97df-3d4ecfc20fc5', '38', '(*^-^)ρ(^0^*)[喂饭]', '大三的', '214234325235', '0', '2020-06-06 16:03:54', '23');
INSERT INTO `orders` VALUES ('30843736-4de4-4cda-8dcc-7e340dd18030', '152', '但是V的是分', '大三的', '4525378537853', '0', '2020-06-03 11:12:55', '23');
INSERT INTO `orders` VALUES ('38708987-f4c8-40c4-ae68-7efd199ee034', '55', '但是V的是分', '上单给vs', '214234325235', '0', '2020-07-11 15:30:39', '23');
INSERT INTO `orders` VALUES ('597947d4-4469-4f75-89f5-a2a0963ae6c2', '300', '', '', '', '0', '2020-05-26 08:08:49', '17');
INSERT INTO `orders` VALUES ('5cd3c352-0d7c-4828-9af2-9a4459fc0fbc', '149.6', '', '', '', '1', '2020-06-03 10:10:12', '22');
INSERT INTO `orders` VALUES ('be855da0-53b0-4d00-a02d-47b2682defaf', '45', '', '', '', '1', '2020-05-26 08:10:57', '17');
INSERT INTO `orders` VALUES ('c4efba9e-a864-4d94-9bb2-7e8f8926b4e1', '34', '给vb', '给 ', '返回', '1', '2020-05-07 10:45:58', '17');
INSERT INTO `orders` VALUES ('d30078be-9a8e-4be2-b9f8-81240cf18196', '55', '发呢挺好', '儿童节你', ' 如我国房屋认购', '1', '2020-05-07 10:46:02', '17');
INSERT INTO `orders` VALUES ('d781546d-bbb5-42d2-9fd3-d75cc933c4e5', '39', '', '', '', '0', '2020-05-26 08:09:20', '17');
INSERT INTO `orders` VALUES ('dfdc6f31-fa79-4710-92b2-8ebf0a91f191', '169', '', '', '', '1', '2020-04-15 08:09:56', '17');
INSERT INTO `orders` VALUES ('f3f8b3f0-ba1e-4084-8c6e-0d5cf57eda18', '192.5', '', '', '', '1', '2020-05-26 08:10:47', '17');

-- ----------------------------
-- Table structure for `products`
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `pnum` int DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('099fc8ff-4135-4d5a-a381-ceea24352cd8', 'spring cloud', '55', '计算机', '48', '/productImg/f906c3dd-c453-4280-85be-c9defbe10c8d-课程表.jpg', '但是V放不得他人呢你太让人体会他一句');
INSERT INTO `products` VALUES ('1', 'java web', '35', '计算机', '100', '/productImg/b4410f1b-842f-46f9-9e58-6e5b9bce99c6-Nacos多配置集.png', 'asdcfdgv');
INSERT INTO `products` VALUES ('2', '时空穿行', '34', '科技', '1', '/productImg/11/4/d79dc124-de69-4b77-847e-bc461bfdb857.jpg', '222222222222222222222222222222222222222222222222222222');
INSERT INTO `products` VALUES ('3', '大勇和小花的欧洲日记', '27.5', '少儿', '1', '/productImg/12/1/986b5e98-ee73-4717-89fd-b6ac26a8dc2c.jpg', '大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记');
INSERT INTO `products` VALUES ('4', 'Java基础入门', '38', '计算机', '4', '/productImg/12/14/a1ace169-b53a-41c6-bdea-000e5946b2a5.png', 'Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门');
INSERT INTO `products` VALUES ('5', '别做正常的傻瓜', '19.5', '励志', '1', '/productImg/14/1/792116e7-6d83-4be4-b3e5-4dd11b0b4565.jpg', '别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜');
INSERT INTO `products` VALUES ('6', '中国国家地理', '23.8', '社科', '18', '/productImg/2/0/2105fbe5-400f-4193-a7db-d7ebac389550.jpg', '中国国家地理中国国家地理中国国家地理中国国家地理中国国家地理');
INSERT INTO `products` VALUES ('7', '学会宽容', '28', '励志', '12', '/productImg/6/5/a2da626c-c72d-4972-83de-cf48405c5563.jpg', '学会宽容学会宽容学会宽容');
INSERT INTO `products` VALUES ('7835af7b-cb3d-4ecc-8fdd-2889668146fa', 'java   asdse', '60', '外语', '10', '/productImg/c394bf6f-b851-4961-b6ca-6f93cf0ac44a-springcloud-copy工程步骤.png', 'sdvgrgfnghmgj,jh');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `activeCode` varchar(50) DEFAULT NULL,
  `state` int DEFAULT '0',
  `role` varchar(10) DEFAULT '普通用户',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'zy', '123', '男', 'yuyafangh@163.com', '', '', '9039c221-fef5-4f2a-b1b1-5f6944252cca', '1', '普通用户', '2020-06-06 15:01:26');
INSERT INTO `user` VALUES ('23', 'zyh', '123', '男', 'zyh15237228271@163.com', '15237228271', '阿色才疯玩过V热舞', 'f7e81075-e2be-4583-9470-61f5ce7044e4', '1', '超级管理员', '2020-06-06 15:01:20');
