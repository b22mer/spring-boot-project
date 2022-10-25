use
backpjt;

--     member table
create table member
(
    name         varchar(50),
    id           varchar(50),
    position     varchar(50),
    pw           varchar(50),
    email        varchar(50),
    phone_number varchar(50)
);

-- Security table
create table SecurityVO
(
    userId varchar(50),
    uuid   varchar(50),
    salt   varchar(50)
);