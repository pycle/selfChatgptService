CREATE TABLE `chatgpt_log` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`form` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '发送者' COLLATE 'utf8mb4_unicode_ci',
	`to` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '接受者' COLLATE 'utf8mb4_unicode_ci',
	`content` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '会话内容' COLLATE 'utf8mb4_unicode_ci',
	`continuation_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '会话id' COLLATE 'utf8mb4_unicode_ci',
	`state` INT(11) NOT NULL DEFAULT '0' COMMENT '状态',
	`gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=444
;
