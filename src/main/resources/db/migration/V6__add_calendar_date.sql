CREATE TABLE `gtfs_calendar_date`
(
    `id`             INT     NOT NULL AUTO_INCREMENT,
    `schema_id`      INT     NOT NULL,
    `service_id`     INT     NOT NULL,
    `date`           DATE    NOT NULL,
    `exception_type` TINYINT NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_calendar_date`
    ADD CONSTRAINT `gtfs_calendar_date_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `gtfs_calendar_date`
    ADD CONSTRAINT `gtfs_calendar_date_service` FOREIGN KEY (`service_id`) REFERENCES `gtfs_service` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
