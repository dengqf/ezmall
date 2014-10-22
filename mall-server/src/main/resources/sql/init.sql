/*
Navicat MySQL Data Transfer

Source Server         : testApp
Source Server Version : 50173
Source Host           : 114.215.102.250:3306
Source Database       : testapp

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-06-23 17:35:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_admin`;
CREATE TABLE `tbl_admin` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `USERNAME` varchar(32) COLLATE utf8_bin NOT NULL,
  `PASSWORD` varchar(128) COLLATE utf8_bin NOT NULL,
  `MERCHANT_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '商家名称，商家用户使用',
  `LINKMAN` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TEL` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `TYPE` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '1:管理员 2:基础维护 3:商家',
  `STATUS` varchar(1) COLLATE utf8_bin DEFAULT '0' COMMENT '状态 0 创建 1 启用 -1停止',
  `USER_PICTURE` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '用户图片路径',
  `USER_DESC` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '用户描述',
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tbl_admin
-- ----------------------------
INSERT INTO `tbl_admin` VALUES ('1', '1', 'test', '$shiro1$SHA-256$500000$4b5/4ecPYJEKPzCvpn6iJQ==$+oNO7rVPmLsKWL/6G9ZkjqgAl4GO1PDVnWupSiqOYic=', '1', '1', '2014-06-17 01:18:26');
INSERT INTO `tbl_admin` VALUES ('cfc1f44d93344f75936439e139308238', 'admin', '$shiro1$SHA-256$500000$e53xmbM9qIydwpfQeWINFQ==$2DTkPrdrlhwp4k561fRmGlUqfk18EJM7eNkvPasKJWs=', '2222', 'abc', '010-6066589', '13241967001', 'test@360appcn.com', '北京市通州区', '1', '0', 'upload/admin/admin/logo.jpeg', '1111', '2014-07-04 16:13:51');

-- ----------------------------
-- Table structure for `tbl_admin_role_rp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_admin_role_rp`;
CREATE TABLE `tbl_admin_role_rp` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `role_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
INSERT INTO `tbl_admin_role_rp` VALUES ('e2d46cd9f2ed42b781cf28e577aff098', 'cfc1f44d93344f75936439e139308238', '1');
INSERT INTO `tbl_admin_role_rp` VALUES ('b41ce0fdc89a44fc980aeae3bea90727', 'cfc1f44d93344f75936439e139308238', '2');
INSERT INTO `tbl_admin_role_rp` VALUES ('d180120e57394eef93480acc14a2c089', 'cfc1f44d93344f75936439e139308238', '3');

-- ----------------------------
-- Records of tbl_admin_role_rp
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_brand_info`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_brand_info`;
CREATE TABLE `tbl_brand_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NO` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ENGLISH_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '英文名',
  `LOGO_URL` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'LOGO的地址',
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `tbl_category_brand_rp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category_brand_rp`;
CREATE TABLE `tbl_category_brand_rp` (
  `ID` varchar(32) NOT NULL DEFAULT '',
  `CATEGORY_NO` varchar(4) DEFAULT NULL,
  `BRAND_NO` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌分类关联表';


-- ----------------------------
-- Table structure for `tbl_category_info`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category_info`;
CREATE TABLE `tbl_category_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NO` varchar(4) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(64) COLLATE utf8_bin NOT NULL,
  `STRUCT` varchar(8) COLLATE utf8_bin NOT NULL,
  `STRUCT_NAME` varchar(128) COLLATE utf8_bin NOT NULL,
  `LEVEL` varchar(2) COLLATE utf8_bin NOT NULL,
  `PARENT_NO` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '父分类NO，如果是一级分类为 0000',
  `UPDATE_DATE` datetime DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `VALID_FLAG` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT 'Y:有效，N：无效',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `tbl_category_prop_rp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category_prop_rp`;
CREATE TABLE `tbl_category_prop_rp` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `CATEGORY_NO` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `PROPITEM_NO` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `tbl_goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_info`;
CREATE TABLE `tbl_goods_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NO` varchar(8) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `BRAND_NO` varchar(4) COLLATE utf8_bin NOT NULL,
  `BRAND_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_NO` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '分类编码',
  `CATEGORY_STRUCT` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '分类结构',
  `CATEGORY_STRUCT_NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `MERCHANT_NO` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '商品所属商家NO',
  `MERCHANT_NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `COST_PRICE` double(8,2) DEFAULT NULL COMMENT '成本价',
  `SELL_PRICE` double(8,2) DEFAULT NULL COMMENT '销售价',
  `GIVE_SCORE` int(6) DEFAULT '0' COMMENT '赠送积分',
  `MARKET_PRICE` double(8,2) DEFAULT NULL COMMENT '市场价',
  `EXPRESS_PRICE` double(8,2) DEFAULT '0.00',
  `CURRENCY_TYPE` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '货币类型(0人民币，1美元，2英镑，3韩元，4日元，5港币，6欧元)',
  `STYLE_NO` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '商品款号',
  `SPEC_NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '规格名称',
  `YEAR` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '商品年费',
  `UNIT` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '销售单位',
  `COUNTRY` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '商品所在国家',
  `CITY` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '商品所在城市',
  `GOODS_DESC` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '商品描述',
  `GOODS_PICTURE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商品默认图片路径',
  `THIRD_LINK` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '第三方链接',
  `THIRD_NO` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '第三方商品编号',
  `THIRD_PICTURE` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '第三方商品图片地址',
  `SUPPLIER_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '款色编码或商品在厂商的唯一编码',
  `STATUS` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '状态',
  `VALID_FLAG` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '有效标志',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InfoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `tbl_goods_prop_rp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_prop_rp`;
CREATE TABLE `tbl_goods_prop_rp` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `GOODS_NO` varchar(8) COLLATE utf8_bin NOT NULL,
  `PROP_ITEM_NO` varchar(8) COLLATE utf8_bin NOT NULL,
  `PROP_ITEM_NAME` varchar(32) COLLATE utf8_bin NOT NULL,
  `PROP_ITEM_VALUE` varchar(8) COLLATE utf8_bin NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Table structure for `tbl_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_permission`;
CREATE TABLE `tbl_permission` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `permission_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tbl_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_product_info`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_info`;
CREATE TABLE `tbl_product_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NO` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `INSIDE_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '产品条码',
  `SIZE` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `GOODS_NO` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tbl_product_info
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_propitem_info`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_propitem_info`;
CREATE TABLE `tbl_propitem_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NO` varchar(4) COLLATE utf8_bin NOT NULL,
  `NAME` varchar(32) COLLATE utf8_bin NOT NULL,
  `ITEM_TYPE` int(2) DEFAULT NULL COMMENT '属性类型 0 文本 1 单选 2多选',
  `DEFAULT_VALUE` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '默认值',
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


DROP TABLE IF EXISTS `tbl_friend`;
CREATE TABLE `tbl_friend` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `MEMBER_USERNAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `FRIEND_USERNAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `REMARK` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `STATUS` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0:邀请，1:好友 -1:拒绝',
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户好友列表';
-- ----------------------------
-- Table structure for `tbl_propvalue_info`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_propvalue_info`;
CREATE TABLE `tbl_propvalue_info` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `NO` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ITEM_NO` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tbl_propvalue_info
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_role`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `role_desc` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tbl_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `tbl_role` VALUES ('2', 'system', '数据维护');
INSERT INTO `tbl_role` VALUES ('3', 'merchant', '商家');


-- ----------------------------
-- Records of tbl_role
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_role_permission_rp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_permission_rp`;
CREATE TABLE `tbl_role_permission_rp` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `permission_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tbl_role_permission_rp
-- ----------------------------
DROP TABLE IF EXISTS `tbl_member`;
CREATE TABLE `tbl_member` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `USERNAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `LINKMAN` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `WB_UID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '微博用户ID',
  `WB_TOKEN` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '微博的TOKEN',
  `LOGIN_TYPE` varchar(2) COLLATE utf8_bin DEFAULT '0' COMMENT '0：用户登录，1：WEIBO登录 2：微信登录',
  `UPDATE_DATE` datetime DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `user_name` (`USERNAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tbl_favorite`;
CREATE TABLE `tbl_favorite` (
  `ID` varchar(32)  NOT NULL,
  `GOODS_NO` varchar(8)  NOT NULL,
  `USER_NAME` varchar(32)  NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tbl_order_main`;
CREATE TABLE `tbl_order_main` (
  `ID` varchar(32)  NOT NULL,
  `NO` varchar(8)  NOT NULL,
  `TOTAL_PRICE` double(10,2) NOT NULL COMMENT '订单总价',
  `TOTAL_AMOUNT` int(8) NOT NULL COMMENT '购买商品总数',
  `TOTAL_GIVE_SCORE` int(6) DEFAULT NULL COMMENT '赠送积分总计',
  `TOTAL_PREFERENTITAL_PRICE` double(10,2) DEFAULT NULL COMMENT '优惠金额总计',
  `TOTAL_EXPRESS_PRICE` double(8,2) DEFAULT NULL COMMENT '快递总费用',
  `EXPRESS_COMPANY` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '快递公司',
  `EXPRESS_ORDER_NO` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '快递单号',
  `USERNAME` varchar(32)  NOT NULL COMMENT '下单用户',
  `MERCHANT_NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `LINKMAN` varchar(32)  NOT NULL COMMENT '联系人',
  `ADDRESS` varchar(128)  NOT NULL,
  `TEL` varchar(32)  DEFAULT NULL,
  `MOBILE` varchar(32)  DEFAULT NULL COMMENT '手机号码',
  `ORDER_MESSAGE` varchar(128)  DEFAULT NULL COMMENT '订单留言',
  `PAYMENT` varchar(2)  DEFAULT NULL COMMENT '0=货到付款，1=在线支付。',
  `PAY_FLAG` varchar(2)  DEFAULT NULL COMMENT '支付状态 Y 已支付，N未支付',
  `VIEW_FLAG` varchar(2)  DEFAULT NULL COMMENT '显示标志',
  `SHIPMENT_FLAG` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `STATUS` varchar(2)  DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


DROP TABLE IF EXISTS `tbl_order_sub`;
CREATE TABLE `tbl_order_sub` (
  `ID` varchar(32)  NOT NULL,
  `ORDER_SUB_NO` varchar(12)  DEFAULT NULL,
  `ORDER_NO` varchar(8)  DEFAULT NULL,
  `GOODS_NO` varchar(8)  DEFAULT NULL,
  `PRODUCT_NO` varchar(12)  DEFAULT NULL,
  `PRODUCT_SIZE` varchar(32)  DEFAULT NULL COMMENT '购买的尺码',
  `AMOUNT` int(6) DEFAULT NULL COMMENT '子订单购买总数',
  `ORDER_PRICE` double(8,2) DEFAULT NULL COMMENT '下单的单价',
  `TOTAL_ORDER_PRICE` double(8,2) DEFAULT NULL COMMENT '订单总价=订单价*数量-商家优惠价+快递价',
  `GOODS_PRICE` double(8,2) DEFAULT NULL COMMENT '商品价',
  `GOODS_NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `GOODS_MARKET_PRICE` double(8,2) DEFAULT NULL COMMENT '市场价格',
  `GOODS_PICTURE` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `CURRENCY_TYPE` varchar(2)  DEFAULT NULL COMMENT '货币类型',
  `GOODS_SPEC_NAME` varchar(128)  DEFAULT NULL COMMENT '商品规格',
  `RATE` double(8,2) DEFAULT NULL COMMENT '外币汇率',
  `PREFERENTITAL_PRICE` double(8,2) DEFAULT NULL COMMENT '每件优惠金额',
  `GIVE_SCORE` int(6) DEFAULT NULL COMMENT '单件商品积分',
  `MERCHANT_PREFERENTITAL` double(8,2) DEFAULT NULL COMMENT '商家优惠价格，子订单价=订单单价*数量-商家优惠价',
  `MERCHANT_NAME` varchar(64)  DEFAULT NULL COMMENT '子订单归属商家',
  `EXPRESS_COMPANY` varchar(64)  DEFAULT NULL COMMENT '快递公司名',
  `EXPRESS_ORDER_NO` varchar(64)  DEFAULT NULL COMMENT '快递单号',
  `EXPRESS_PRICE` double(8,2) DEFAULT NULL COMMENT '快递费用（总）',
  `PACKGE_NO` varchar(64)  DEFAULT NULL COMMENT '包裹单号',
  `PACKGE_SUM` int(6) DEFAULT NULL COMMENT '包裹包含订单数',
  `PAY_FLAG` varchar(2)  DEFAULT NULL COMMENT '是否支付，Y支付N未支付',
  `COMMENT_FLAG` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '是否已经评论',
  `SHIPMENT_FLAG` varchar(2)  DEFAULT NULL COMMENT '发货标志 Y已发货，N未发货',
  `PAYMENT` varchar(2)  DEFAULT NULL COMMENT '支付方式，同主表',
  `STATUS` varchar(2)  DEFAULT NULL COMMENT '订单状态',
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tbl_goods_comment`;
CREATE TABLE `tbl_goods_comment` (
  `ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `GOODS_NO` varchar(8) COLLATE utf8_bin NOT NULL,
  `GOODS_NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `GOODS_PICTURE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_NO` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_SUB_NO` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `USERNAME` varchar(32) COLLATE utf8_bin NOT NULL,
  `COMMENT` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `SCORE` int(6) DEFAULT NULL,
  `VALID_FLAG` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ----------------------------
-- Table structure for `tbl_systemmg_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_systemmg_sequence`;
CREATE TABLE `tbl_systemmg_sequence` (
  `seq_name` varchar(50) NOT NULL COMMENT '序列名称',
  `seq_start` int(11) NOT NULL DEFAULT '1' COMMENT '序列起始值,大于0的整数',
  `seq_end` bigint(20) NOT NULL COMMENT '序列最大值,最大9223372036854775807-seq_increment',
  `seq_value` bigint(20) NOT NULL DEFAULT '1' COMMENT '序列当前值',
  `seq_increment` int(11) NOT NULL DEFAULT '1' COMMENT '序列步长,大于0的整数',
  `seq_cycle` tinyint(4) NOT NULL DEFAULT '0' COMMENT '序列是否可循环使用,0-不可循环，1-可循环,2-小时循环，3-天循环',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `seq_desc` varchar(200) DEFAULT NULL COMMENT '序列说明',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列值表';

-- ----------------------------
-- Records of tbl_systemmg_sequence
-- ----------------------------
INSERT INTO `tbl_systemmg_sequence` VALUES ('brand_no_seq', '1000', '9999', '1000', '1', '0', '2014-06-18 16:16:40', '2014-06-21 22:28:07', '品牌编号');
INSERT INTO `tbl_systemmg_sequence` VALUES ('category_no_seq', '1000', '9999', '1000', '1', '0', '2014-06-18 16:16:40', '2014-06-20 23:44:20', '分类编号');
INSERT INTO `tbl_systemmg_sequence` VALUES ('goods_no_seq', '10000000', '99999999', '10000001', '1', '0', '2014-06-18 16:20:32', '2014-06-22 12:40:37', '商品编号');
INSERT INTO `tbl_systemmg_sequence` VALUES ('propitem_no_seq', '1000', '9999', '1000', '1', '0', '2014-06-18 16:16:40', '2014-06-22 13:33:14', '属性项编号');
INSERT INTO `tbl_systemmg_sequence` VALUES ('order_no_seq', '10000000', '99999999', '10000000', '1', '0', '2014-06-29 15:47:36', '2014-06-29 23:00:35', '订单编号');

-- ----------------------------
-- Function structure for `func_sequence_nextval`
-- ----------------------------
DROP FUNCTION IF EXISTS `func_sequence_nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `func_sequence_nextval`(p_seq_name varchar(50)) RETURNS bigint(20)
MODIFIES SQL DATA
DETERMINISTIC
  BEGIN
/*
功能：
	生成序列的下一个值
参数：
	p_seq_name:序列名称
返回值：
	0：错误，序列名称不存在和超过最大值的情况；
	>0:正常值
*/
#定义返回值变量
    declare v_return BIGINT default 0;
#定义序列最大值变量
    declare v_seq_end BIGINT default 1;
#定义序列起始值变量
    declare v_seq_start int default 1;
#定义序列是否可循环变量
    declare v_seq_cycle tinyint default 0;
#定义序列修改时间变量
    declare v_update_time datetime;
#定义当前序列值变量
    declare v_cur_seq_value BIGINT default 0;
#定义序列步长变量
    declare v_seq_increment int default 0;

#如果序列名称不存在，返回
    select count(*),max(seq_end),max(seq_start),max(seq_cycle),ifnull(max(update_time),now()),max(seq_value),max(seq_increment)
    into v_return,v_seq_end,v_seq_start,v_seq_cycle,v_update_time,v_cur_seq_value,v_seq_increment
    from tbl_systemmg_sequence
    where seq_name=p_seq_name;
    if v_return=0 then
      return v_return;
    end if;

#临界点时更新成序列起始值，符合以下条件：
#按小时循环,如果小时变了
#按天循环,如果天变了
#如果序列达最大值并且可循环
    if (v_seq_cycle=2 and hour(now())<>hour(v_update_time))
       or (v_seq_cycle=3 and day(now())<>day(v_update_time))
       or (v_cur_seq_value+v_seq_increment>v_seq_end and v_seq_cycle=1) then
      UPDATE tbl_systemmg_sequence
      SET seq_value=LAST_INSERT_ID(seq_start),update_time=now()
      where seq_name=p_seq_name and seq_value=v_cur_seq_value;
#如果影响函数为0，表示被别的会话修改，按步长增长
      if row_count()=0 then
        UPDATE tbl_systemmg_sequence
        SET seq_value=LAST_INSERT_ID(seq_value+seq_increment),update_time=now()
        where seq_name=p_seq_name;
      end if;
    else
#正常情况，按步长增长
      UPDATE tbl_systemmg_sequence
      SET seq_value=LAST_INSERT_ID(seq_value+seq_increment),update_time=now()
      where seq_name=p_seq_name;
    end if;

#获取序列增长后值
    SELECT LAST_INSERT_ID() into v_return;

    RETURN v_return;
  END
;;
DELIMITER ;
