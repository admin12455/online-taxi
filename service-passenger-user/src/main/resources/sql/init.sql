CREATE TABLE `service-passenger-user`.`passenger_user`
(
    `id`               bigint unsigned NOT NULL AUTO_INCREMENT,
    `passenger_phone`  varchar(16) COLLATE utf8mb4_german2_ci DEFAULT NULL,
    `passenger_name`   varchar(16) COLLATE utf8mb4_german2_ci DEFAULT NULL,
    `passenger_gender` tinyint(1) DEFAULT NULL COMMENT '0：女，1：男',
    `state`            tinyint(1) DEFAULT NULL COMMENT '0 有效，1失效',
    `gmt_create`       datetime                               DEFAULT NULL,
    `gmt_modified`     datetime                               DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;
