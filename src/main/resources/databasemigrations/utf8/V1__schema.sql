CREATE TABLE `test_entity`
(
    `id`       varchar(36) NOT NULL,
    `name`     varchar(30) NOT NULL,
    `created`  datetime    NOT NULL,
    `modified` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;