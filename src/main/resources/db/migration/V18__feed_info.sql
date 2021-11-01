ALTER TABLE `gtfs_feed_info` CHANGE `feed_version` `feed_version` VARCHAR (45) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NULL DEFAULT NULL;

ALTER TABLE `gtfs_feed_info` CHANGE `feed_publisher_name` `feed_publisher_name` VARCHAR (40) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL;