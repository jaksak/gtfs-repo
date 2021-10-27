CREATE TABLE `gtfs_service`
(
    `id`                  INT         NOT NULL AUTO_INCREMENT,
    `schema_id`           INT         NOT NULL,
    `name`                TEXT NULL,
    `external_service_id` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_service`
    ADD CONSTRAINT `gtfs_service_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `gtfs_calendar` CHANGE `external_service_id` `service_id` INT NOT NULL;

ALTER TABLE `gtfs_calendar`
    ADD CONSTRAINT `gtfs_calendar_service` FOREIGN KEY (`service_id`) REFERENCES `gtfs_service` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
