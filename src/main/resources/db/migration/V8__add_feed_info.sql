CREATE TABLE `gtfs_feed_info`
(
    `id`                  INT         NOT NULL AUTO_INCREMENT,
    `schema_id`           INT         NOT NULL,
    `feed_publisher_name` VARCHAR(20) NOT NULL,
    `feed_publisher_url`  VARCHAR(25) NOT NULL,
    `default_lang`        VARCHAR(5) NULL,
    `feed_contact_email`  VARCHAR(20) NULL,
    `feed_contact_url`    VARCHAR(20) NULL,
    `feed_lang`           VARCHAR(5) NULL,
    `feed_version`        VARCHAR(20) NULL,
    `feed_start_date`     DATE NULL,
    `feed_end_date`       DATE NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_feed_info`
    ADD CONSTRAINT `gtfs_feed_info_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
