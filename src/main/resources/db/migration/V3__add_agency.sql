CREATE TABLE `gtfs_agency`
(
    `id`                 INT         NOT NULL AUTO_INCREMENT,
    `schema_id`          INT         NOT NULL,
    `agency_external_id` VARCHAR(10) NOT NULL,
    `agency_name`        VARCHAR(15) NOT NULL,
    `agency_url`         VARCHAR(25) NOT NULL,
    `agency_timezone`    VARCHAR(15) NOT NULL,
    `agency_lang`        VARCHAR(5) NULL,
    `agency_phone`       VARCHAR(15) NULL,
    `agency_fare_url`    VARCHAR(25) NULL,
    `agency_email`       VARCHAR(25) NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_agency`
    ADD CONSTRAINT `gtfs_agency_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
