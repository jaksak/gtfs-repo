CREATE TABLE `gtfs_calendar`
(
    `id`                  INT         NOT NULL AUTO_INCREMENT,
    `schema_id`           INT         NOT NULL,
    `external_service_id` VARCHAR(10) NOT NULL,
    `monday`              BOOLEAN     NOT NULL,
    `tuesday`             BOOLEAN     NOT NULL,
    `wednesday`           BOOLEAN     NOT NULL,
    `thursday`            BOOLEAN     NOT NULL,
    `friday`              BOOLEAN     NOT NULL,
    `saturday`            BOOLEAN     NOT NULL,
    `sunday`              BOOLEAN     NOT NULL,
    `start_date`          DATE        NOT NULL,
    `end_date`            DATE        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_calendar`
    ADD CONSTRAINT `gtfs_calendar_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
