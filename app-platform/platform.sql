#权限相关表===================
DROP TABLE if EXISTS ts_user;
CREATE TABLE ts_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_name varchar(50) NOT NULL COMMENT '用户名',
  password varchar(255) NOT NULL COMMENT '密码',
  name varchar(50) DEFAULT NULL COMMENT '姓名',
  phone varchar(50) DEFAULT NULL COMMENT '电话号码',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  last_login datetime DEFAULT NULL COMMENT '最后登录时间',
  ctime datetime DEFAULT NULL COMMENT '创建时间',
  creator bigint(20) DEFAULT NULL COMMENT '创建人',
  utime datetime DEFAULT NULL COMMENT '修改时间',
  modifier bigint(20) DEFAULT NULL COMMENT '修改人',
  status tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理后台用户表';

DROP TABLE if EXISTS ts_role;
CREATE TABLE ts_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL COMMENT '名称',
  description varchar(50) DEFAULT NULL COMMENT '描述',
  ctime datetime DEFAULT NULL COMMENT '创建时间',
  creator bigint(20) DEFAULT NULL COMMENT '创建人',
  utime datetime DEFAULT NULL COMMENT '修改时间',
  modifier bigint(20) DEFAULT NULL COMMENT '修改人',
  status tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理后台角色表';

DROP TABLE if EXISTS tb_authority;
CREATE TABLE tb_authority (
  utime datetime NOT NULL COMMENT '修改时间',
  ctime datetime NOT NULL COMMENT '创建时间',
  modifier bigint(20) NOT NULL COMMENT '修改人',
  creator bigint(20) NOT NULL COMMENT '创建人',
  description varchar(255) NOT NULL COMMENT '描述',
  name varchar(255) NOT NULL COMMENT '名称',
  id bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE if EXISTS tb_user_role;
CREATE TABLE tb_user_role (
  user_id bigint(20) NOT NULL COMMENT '用户id',
  role_id bigint(20) NOT NULL COMMENT '角色id',
  id bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE if EXISTS tb_role_authority;
CREATE TABLE tb_role_authority (
  role_id bigint(20) NOT NULL COMMENT '角色id',
  authority_id bigint(20) NOT NULL COMMENT '权限id',
  id bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#系统功能相关表===================
#商品类别表
DROP TABLE if EXISTS tb_good_category;
CREATE TABLE tb_good_category (
id bigint(20) NOT NULL AUTO_INCREMENT,
name varchar(50) NOT NULL COMMENT '商品类别名称',
description varchar(255) DEFAULT NULL COMMENT '商品类别描述',
parent_id bigint(20) DEFAULT NULL COMMENT '父类别id',
utime datetime NOT NULL COMMENT '修改时间',
ctime datetime NOT NULL COMMENT '创建时间',
modifier bigint(20) NOT NULL COMMENT '修改人',
creator bigint(20) NOT NULL COMMENT '创建人',
status int(4) NOT NULL COMMENT '状态',
PRIMARY KEY (id)
) COMMENT '商品类别表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#商品品牌表
DROP TABLE if EXISTS tb_good_brand;
CREATE TABLE tb_good_brand (
id bigint(20) NOT NULL AUTO_INCREMENT,
name varchar(50) NOT NULL COMMENT '品牌名称',
description varchar(255) DEFAULT NULL COMMENT '品牌描述',
parent_id bigint(20) DEFAULT NULL COMMENT '父品牌id',
utime datetime NOT NULL COMMENT '修改时间',
ctime datetime NOT NULL COMMENT '创建时间',
modifier bigint(20) NOT NULL COMMENT '修改人',
creator bigint(20) NOT NULL COMMENT '创建人',
status int(4) NOT NULL COMMENT '状态',
PRIMARY KEY (id)
) COMMENT '商品品牌表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#图片表
DROP TABLE if EXISTS tb_picture;
CREATE TABLE tb_picture (
id bigint(20) NOT NULL AUTO_INCREMENT,
url varchar(255) NOT NULL COMMENT '图片URL',
name varchar(50) NOT NULL COMMENT '图片名称',
description varchar(255) DEFAULT NULL COMMENT '图片描述',
good_id bigint(20) NOT NULL COMMENT '商品ID',
ctime datetime NOT NULL COMMENT '创建时间',
creator bigint(20) NOT NULL COMMENT '创建人',
PRIMARY KEY (id)
) COMMENT '图片表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#商品表
DROP TABLE if EXISTS tb_good;
CREATE TABLE tb_good (
id bigint(20) NOT NULL AUTO_INCREMENT,
name varchar(255) DEFAULT NULL COMMENT '名称',
code varchar(255) DEFAULT NULL COMMENT '编码',
category_id bigint(20) NOT NULL COMMENT '商品分类id',
brand_id bigint(20) NOT NULL COMMENT '品牌id',
price decimal(12,2) DEFAULT NULL COMMENT '价格',
click_cnt int(11) DEFAULT NULL COMMENT '点击量',
collect_cnt int(11) DEFAULT NULL COMMENT '收藏量',
sale_cnt int(11) DEFAULT NULL COMMENT '销售数量',
stock_cnt int(11) DEFAULT NULL COMMENT '库存量',
recommend int(4) DEFAULT NULL COMMENT '是否为推荐商品',
weigth decimal(12,2) DEFAULT NULL COMMENT '重量',
picture varchar(255) DEFAULT NULL COMMENT '主图url',
activity_status int(11) DEFAULT NULL COMMENT '活动状态',
bargain_status int(11) DEFAULT NULL COMMENT '特价状态',
current_price decimal(12,2) DEFAULT NULL COMMENT '现价',
ems_freight decimal(12,2) DEFAULT NULL COMMENT 'EMS运费',
express_freight decimal(12,2) DEFAULT NULL COMMENT '快递运费',
mail_freight decimal(12,2) DEFAULT NULL COMMENT '平邮运费',
utime datetime NOT NULL COMMENT '修改时间',
ctime datetime NOT NULL COMMENT '创建时间',
modifier bigint(20) NOT NULL COMMENT '修改人',
creator bigint(20) NOT NULL COMMENT '创建人',
status int(4) NOT NULL COMMENT '状态',
PRIMARY KEY (id)
) COMMENT '商品表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#商品属性表
DROP TABLE if EXISTS tb_good_attribute;
CREATE TABLE tb_good_attribute (
id bigint(20) NOT NULL AUTO_INCREMENT,
good_id bigint(20) NOT NULL COMMENT '商品id',
name varchar(255) DEFAULT NULL COMMENT '属性名称',
attr_value varchar(255) DEFAULT NULL COMMENT '编码',
utime datetime NOT NULL COMMENT '修改时间',
ctime datetime NOT NULL COMMENT '创建时间',
modifier bigint(20) NOT NULL COMMENT '修改人',
creator bigint(20) NOT NULL COMMENT '创建人',
status int(4) NOT NULL COMMENT '状态',
PRIMARY KEY (id)
) COMMENT '商品属性表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#收货地址表
DROP TABLE if EXISTS tb_address;
CREATE TABLE tb_address (
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL COMMENT '用户id',
addressee varchar(255) NOT NULL COMMENT '收件人姓名',
email varchar(255) DEFAULT NULL COMMENT '收件人邮箱',
tel varchar(255) DEFAULT NULL COMMENT '收件人座机号码',
phone varchar(255) DEFAULT NULL COMMENT '收件人手机号码',
postal_code varchar(255) DEFAULT NULL COMMENT '邮政编码',
province varchar(255) NOT NULL COMMENT '省份',
city varchar(255) NOT NULL COMMENT '城市',
area varchar(255) NOT NULL COMMENT '县/区',
street varchar(255) NOT NULL COMMENT '街道门牌号详细地址',
utime datetime NOT NULL COMMENT '修改时间',
ctime datetime NOT NULL COMMENT '创建时间',
status int(4) NOT NULL COMMENT '状态',
PRIMARY KEY (id)
) COMMENT '收货地址表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#购物车表
DROP TABLE if EXISTS tb_shopping_cart;
CREATE TABLE tb_shopping_cart (
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL COMMENT '用户id',
good_id bigint(20) NOT NULL COMMENT '商品id',
good_cnt int(11) NOT NULL COMMENT '商品数量',
price decimal(12,2) NOT NULL COMMENT '所选商品价格',
ctime datetime NOT NULL COMMENT '创建时间',
status int(4) NOT NULL COMMENT '状态',
PRIMARY KEY (id)
) COMMENT '购物车表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#订单表
DROP TABLE if EXISTS tb_order;
CREATE TABLE tb_order (
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL COMMENT '用户id',
address_id bigint(20) NOT NULL COMMENT '收货地址id',
code varchar(100) NOT NULL COMMENT '订单编码',
good_ids varchar(500) NOT NULL COMMENT '商品id列表，用‘,’分隔',
good_cnt int(11) NOT NULL COMMENT '商品数量',
price decimal(12,2) NOT NULL COMMENT '订单总价',
real_payment decimal(12,2) NOT NULL COMMENT '实际支付价格',
ctime datetime NOT NULL COMMENT '创建时间',
transaction_time bigint(20) NOT NULL COMMENT '成交时间',
status int(4) NOT NULL COMMENT '订单状态',
PRIMARY KEY (id)
) COMMENT '订单表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#物流表
DROP TABLE if EXISTS tb_logistics;
CREATE TABLE tb_logistics (
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id bigint(20) NOT NULL COMMENT '用户id',
order_id bigint(20) NOT NULL COMMENT '订单id',
order_code varchar(100) NOT NULL COMMENT '订单编码',
logistic_code varchar(100) NOT NULL COMMENT '快递单号',
shipper_code varchar(50) NOT NULL COMMENT '快递公司编码',
ctime datetime NOT NULL COMMENT '创建时间',
sign_in_time bigint(20) NOT NULL COMMENT '签收时间',
status int(4) NOT NULL COMMENT '物流状态',
PRIMARY KEY (id)
) COMMENT '物流表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#物流在途追踪表
DROP TABLE if EXISTS tb_logistics_tracker;
CREATE TABLE tb_logistics_tracker (
id bigint(20) NOT NULL AUTO_INCREMENT,
logistic_id bigint(20) NOT NULL COMMENT '物流id',
note varchar(255) NOT NULL COMMENT '在途动态备注',
utime datetime NOT NULL COMMENT '物流更新时间',
PRIMARY KEY (id)
) COMMENT '物流在途追踪表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

#酒柜表
DROP TABLE if EXISTS tb_wine_cabinet;
CREATE TABLE tb_wine_cabinet (
id bigint(20) NOT NULL AUTO_INCREMENT,
good_id bigint(20) NOT NULL COMMENT '商品id',
ctime datetime NOT NULL COMMENT '创建时间',
PRIMARY KEY (id)
) COMMENT '酒柜表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

