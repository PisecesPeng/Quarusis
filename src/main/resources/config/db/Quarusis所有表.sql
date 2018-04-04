-- 增加用户信息表
CREATE TABLE `user_info` (
  `uin` VARCHAR(10) NOT NULL COMMENT '微信UIN',
  `name` VARCHAR(10) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (uin)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';

-- 增加用户评论历史表
CREATE TABLE `#{uin}_history` (
  `history_id` INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'HistoryID - 自增',
  `page_id` VARCHAR(10) NOT NULL COMMENT 'pageID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户评论历史表';

-- 增加用户page表
CREATE TABLE `user_page` (
  `page_id` INT(4) NOT NULL AUTO_INCREMENT COMMENT 'pageID - 自增',
  `uin` VARCHAR(10) NOT NULL COMMENT '微信UIN',
  `page_topic` VARCHAR(3) NOT NULL COMMENT 'page话题',
  `page_title` VARCHAR(20) NOT NULL COMMENT 'page标题',
  `page_text` VARCHAR(70) NOT NULL COMMENT 'page内容',
  `resource_url` VARCHAR(50) DEFAULT NULL COMMENT 'page资源url',
  `whether_comment` TINYINT DEFAULT 1 COMMENT '是否允许评论，默认允许',
  `comment_sum` INT(4) DEFAULT 0 COMMENT '统计评论数量',
  `whether_read` TINYINT DEFAULT 0 COMMENT '是否已查看最新评论，默认已查看',
  PRIMARY KEY (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户page表';

-- 增加指定page评论表,增加回复评论功能
CREATE TABLE `#{page_id}_comment` (
  `comment_id` INT(4) NOT NULL AUTO_INCREMENT COMMENT 'page的commentID - 自增',
  `toComment_id` INT(4) NOT NULL DEFAULT 0 COMMENT '记录其属于哪条评论的评论 - 为0则为回复page的评论',
  `uin` VARCHAR(10) NOT NULL COMMENT '微信UIN',
  `toUin` VARCHAR(10) COMMENT '回复评论的微信UIN - 为NULL则为回复page的评论',
  `comment_text` VARCHAR(20) NOT NULL COMMENT 'comment内容',
  `comment_heat` INT(4) DEFAULT 0 COMMENT 'comment热度',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '指定page评论表';

-- 增加评论热度表，判断用户是否已经heat
CREATE TABLE `heat` (
  `uin` VARCHAR(10) NOT NULL COMMENT '微信UIN',
  `page_id` INT(4) NOT NULL  COMMENT 'page的ID',
  `comment_id` INT(4) NOT NULL COMMENT 'page的commentID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '评论热度表';

