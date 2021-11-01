CREATE TABLE `gtfs_stops`
(
    `id`                  INT         NOT NULL AUTO_INCREMENT,
    `schema_id`           INT         NOT NULL,
    `stop_external_id`    VARCHAR(20) NOT NULL,
    `stop_code`           VARCHAR(5) NULL,
    `stop_name`           VARCHAR(40) NULL,
    `stop_desc`           VARCHAR(10) NULL,
    `stop_lat`            VARCHAR(14) NULL,
    `stop_lon`            VARCHAR(14) NULL,
    `zone_id`             VARCHAR(1) NULL,
    `stop_url`            VARCHAR(10) NULL,
    `location_type`       TINYINT NULL,
    `parent_station`      INT NULL,
    `stop_timezone`       VARCHAR(15) NULL,
    `wheelchair_boarding` TINYINT NULL,
    `level_id`            VARCHAR(1) NULL,
    `platform_code`       VARCHAR(3) NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_stops`
    ADD CONSTRAINT `gtfs_stops_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `gtfs_stops`
    ADD CONSTRAINT `gtfs_stops_stops` FOREIGN KEY (`parent_station`) REFERENCES `gtfs_stops` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;