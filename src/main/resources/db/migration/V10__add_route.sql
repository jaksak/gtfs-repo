CREATE TABLE `gtfs_routes`
(
    `id`                  INT      NOT NULL AUTO_INCREMENT,
    `schema_id`           INT      NOT NULL,
    `route_external_id`   VARCHAR(10) NULL,
    `agency_id`           INT NULL,
    `route_short_name`    VARCHAR(5) NULL,
    `route_long_name`     VARCHAR(20) NULL,
    `route_desc`          VARCHAR(10) NULL,
    `route_type`          SMALLINT NOT NULL,
    `route_url`           VARCHAR(10) NULL,
    `route_color`         VARCHAR(6) NULL,
    `route_text_color`    VARCHAR(6) NULL,
    `route_sort_order`    INT NULL,
    `continuous_pickup`   TINYINT NULL,
    `continuous_drop_off` TINYINT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

ALTER TABLE `gtfs_routes`
    ADD CONSTRAINT `gtfs_routes_agency` FOREIGN KEY (`agency_id`) REFERENCES `gtfs_agency` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `gtfs_routes`
    ADD CONSTRAINT `gtfs_routes_schema_version` FOREIGN KEY (`schema_id`) REFERENCES `gtfs_schema_version` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
