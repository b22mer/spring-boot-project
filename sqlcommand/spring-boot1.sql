use
ssafyweb;

create table if not exists file_data
(
    uuid         varchar(50),
    file_name    varchar(80),
    content_type varchar(50)
    );
--     member table
create table if not exists member
(
    name         varchar(50),
    id           varchar(50),
    position     varchar(50),
    pw           varchar(50),
    email        varchar(50),
    phone_number varchar(50),
    admin        int not null,
    PRIMARY KEY (`id`)
    );

-- Security table
create table if not exists SecurityVO
(
    userId varchar(50),
    uuid   varchar(50),
    salt   varchar(50)
    );
CREATE TABLE if not exists board
(
    `code`          int                 NOT NULL ,
    `article_no`     int                not null AUTO_INCREMENT,
    `groupOrd`      int                DEFAULT NULL,
    `groupLayer`    int                DEFAULT NULL,
    `title`         varchar(250)       DEFAULT NULL,
    `content`       longtext,
    `writer`        varchar(50)        DEFAULT NULL,
    hit             int,
    `reg_datetime`  datetime           DEFAULT NULL,
    `id`            VARCHAR(16) NULL DEFAULT NULL,
    PRIMARY KEY (`article_no`),
    INDEX           `board_to_member_id_fk` ( `id` ASC ) VISIBLE,
    CONSTRAINT `board_to_member_id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `ssafyweb`.`member` (`id`)
    on delete cascade
    on update cascade
    ) ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafyweb`.`memo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyweb`.`memo`;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`memo`
(
    `memo_no`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `id`
    VARCHAR
(
    16
) NULL DEFAULT NULL,
    `comment` VARCHAR
(
    500
) NULL DEFAULT NULL,
    `memo_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `article_no` INT NULL DEFAULT NULL,
    PRIMARY KEY
(
    `memo_no`
),
    INDEX `memo_to_board_article_no_fk`
(
    `article_no` ASC
) VISIBLE,
    INDEX `memo_to_member_fk_idx`
(
    `id` ASC
) VISIBLE,
    CONSTRAINT `memo_to_board_article_no_fk`
    FOREIGN KEY
(
    `article_no`
)
    REFERENCES `ssafyweb`.`board`
(
    `article_no`
),
    CONSTRAINT `memo_to_member_id_fk`
    FOREIGN KEY
(
    `id`
)
    REFERENCES `ssafyweb`.`member`
(
    `id`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafyweb`.`file_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafyweb`.`file_info`;

CREATE TABLE IF NOT EXISTS `ssafyweb`.`file_info`
(
    `idx`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `article_no`
    INT
    NULL,
    `save_folder`
    VARCHAR
(
    45
) NULL,
    `original_file` VARCHAR
(
    50
) NULL,
    `save_file` VARCHAR
(
    50
) NULL,
    PRIMARY KEY
(
    `idx`
),
    CONSTRAINT `file_info_to_board_article_no_fk`
    FOREIGN KEY
(
    `article_no`
)
    REFERENCES `ssafyweb`.`board`
(
    `article_no`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
;