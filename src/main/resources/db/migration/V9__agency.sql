ALTER TABLE `gtfs_agency` CHANGE `agency_name` `agency_name` VARCHAR (60) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL;

ALTER TABLE `gtfs_agency` CHANGE `agency_phone` `agency_phone` VARCHAR (20) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NULL DEFAULT NULL;

ALTER TABLE `gtfs_agency` CHANGE `agency_fare_url` `agency_fare_url` VARCHAR (30) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NULL DEFAULT NULL;