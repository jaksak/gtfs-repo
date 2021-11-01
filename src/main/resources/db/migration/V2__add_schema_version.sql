CREATE TABLE `gtfs_schema_version`
(
    `id`           INT      NOT NULL AUTO_INCREMENT,
    `customer_id`  INT      NOT NULL,
    `created_time` DATETIME NOT NULL,
    `is_active`    BOOLEAN  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_schema_version`
    ADD CONSTRAINT `gtfs_schema_version_customer` FOREIGN KEY (`customer_id`) REFERENCES `gtfs_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
