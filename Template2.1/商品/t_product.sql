/*
 Navicat Premium Data Transfer

 Source Server         : 阿里小高
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 39.106.121.103:3306
 Source Schema         : template

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 10/06/2020 19:11:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `no` int(11) NOT NULL,
  `proName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `proInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `money` double(255, 3) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE,
  UNIQUE INDEX `proName`(`proName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (2798282, 'skmei——阿玛尼', '时刻美 skmei 手表男士超薄系列时尚简约石英学生手表情侣手表 黑色男款', 99.000, 14, 3);
INSERT INTO `t_product` VALUES (3997258, '百搭翡翠5975J-001', '百达翡丽特别限量175周年纪念款18K玫瑰金自动机械男表', 500000.000, 0, 3);
INSERT INTO `t_product` VALUES (4057989, '碧根果', '每日坚果炒货干果进口休闲零食地方特产160g/袋', 45.000, 6, 2);
INSERT INTO `t_product` VALUES (4604126, '华为nova5pro', '华为nova5pro手机 绮境森林 8GB+128GB全网通', 2155.000, 46, 1);
INSERT INTO `t_product` VALUES (5486038, '古法5——衡水老白干', '衡水老白干 白酒礼盒 古法五 老白干香型 38度 500ml', 158.000, 666, 4);
INSERT INTO `t_product` VALUES (9454099, ' iPhone XS', 'Apple iPhone XS (A2100) 256GB 金色 移动联通电信4G手机', 5999.000, 4, 1);
INSERT INTO `t_product` VALUES (9615032, '华为 Mate 20 Pro', '屏内指纹版麒麟980芯片全面屏超大广角徕卡三摄8GB+128GB', 3599.000, 10, 1);
INSERT INTO `t_product` VALUES (10610324, ' iphone 11 ', 'Apple 苹果 iphone 11 手机 绿色 全网通 64GB', 4999.000, 15, 1);
INSERT INTO `t_product` VALUES (23105270, ' iPhone 11 Pro Max', 'Apple (A2220) 256GB 暗夜绿色 移动联通电信4G手机 双卡双待', 10899.000, 3, 1);
INSERT INTO `t_product` VALUES (24108120, '阿玛尼AR11244', '满天星手表 女新款玫瑰金钢制表带镶钻石英轻奢女士腕表礼物', 3990.000, 4, 3);
INSERT INTO `t_product` VALUES (24429952, '罗西尼519916G09C', '女 CHIC系列 本命年 转运珠设计 新年红石英钢带表小萌鼠手链礼盒', 1280.000, 8, 3);
INSERT INTO `t_product` VALUES (31465158, 'OPPO Reno Ace', '8GB+128GB 星际蓝 65W超级闪充 90Hz电竞屏 高通骁龙855Plus ', 2899.000, 19, 1);
INSERT INTO `t_product` VALUES (31756268, '小米  Redmi K30', '王一博同款 120Hz流速屏 前置挖孔双摄 6GB+128GB 深海微光 小米 红米', 1699.000, 46, 1);
INSERT INTO `t_product` VALUES (33662596, '小青花——衡水老白干', '衡水老白干 白酒礼盒 小青花 老白干香型 50度 500ml', 89.000, 1000, 4);
INSERT INTO `t_product` VALUES (36194734, '红五星——衡水老白干', '衡水老白干 白酒礼盒 五星 老白干香型 67度 500mL 高度白酒', 429.000, 6, 4);
INSERT INTO `t_product` VALUES (39085026, '古法15——衡水老白干', '衡水老白干 白酒 古法十五 老白干香型 52度 500ml', 448.000, 34, 4);
INSERT INTO `t_product` VALUES (45771370, '古法20——衡水老白干', '衡水老白干 白酒礼盒 古法二十(20) 老白干香型 67度 500ml 单瓶装', 549.000, 6, 4);
INSERT INTO `t_product` VALUES (50967104, '十八酒坊——衡水老白干', '十八酒坊 醇柔二十 39度480ml单瓶礼盒装白酒（新老包装随机发货）', 899.000, 10, 4);
INSERT INTO `t_product` VALUES (51590217, 'AR1981——阿玛尼', '阿玛尼手表 商务时尚全自动机械镂空男士机械腕表 AR1981', 2690.000, 5, 3);
INSERT INTO `t_product` VALUES (52927596, '古法30——衡水老白干', '衡水老白干 白酒礼盒 古法30 老白干香型 39度 500ml 礼盒装', 999.000, 14, 4);
INSERT INTO `t_product` VALUES (56352008, '华为 Mate 30 Pro 5G ', '麒麟990 OLED环幕屏双4000万徕卡电影四摄8GB+256GB丹霞橙5G', 6399.000, 2, 1);
INSERT INTO `t_product` VALUES (66918251, '夏威夷果', '坚果炒货孕妇坚果干果零食特产265g/袋（外袋中有开口器）', 24.000, 10, 2);
INSERT INTO `t_product` VALUES (69729641, '衡水老白干1915', '衡水老白干 白酒礼盒 1915 老白干香型 39度 500ml 白酒单瓶 ', 1688.000, 1, 4);
INSERT INTO `t_product` VALUES (73913988, '腰果 ', '日坚果炭烧腰果 坚果炒货零食特产 90g/袋', 31.000, 4, 2);
INSERT INTO `t_product` VALUES (85616681, '罗西尼619909W04A', ' 映像系列 学生手表 男表 时尚百搭简约机械表 皮带男士腕表619909W04A', 1180.000, 12, 3);
INSERT INTO `t_product` VALUES (85946259, '罗西尼517793G05I', '勋章系列 男表 潮流时尚机械表 皮带男士腕表21mm蓝皮padi礼盒', 2380.000, 10, 3);
INSERT INTO `t_product` VALUES (87690573, '荣耀9X——华为', '麒麟810 4000mAh续航 4800万超清夜拍 6.59英寸升降全面屏 全网通', 1299.000, 16, 1);
INSERT INTO `t_product` VALUES (90855596, '大青花——衡水老白干', '衡水老白干 白酒礼盒 大青花 老白干香型 40度 500ml 单瓶礼盒白酒', 139.000, 365, 4);
INSERT INTO `t_product` VALUES (91867442, '青花手酿——衡水老白干', ' 衡水老白干 白酒 青花手酿 62度500ml 老白干香型 高度白酒', 125.000, 13, 4);
INSERT INTO `t_product` VALUES (99650677, '伯爵', 'PIAGET伯爵G0A33061自动机械18K白金后镶钻动力储备日历秒针飞返男表', 111600.000, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;
