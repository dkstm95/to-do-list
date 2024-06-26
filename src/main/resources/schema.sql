create table if not exists `user`
(
    `id` bigint not null auto_increment,
    `email` varchar(30) not null comment '이메일',
    `password` varchar(100) not null comment '비밀번호',
    `nickname` varchar(30) not null comment '닉네임',
    `role` varchar(5) not null comment '권한',
    `enabled` tinyint(1) not null default 1 comment '계정 활성화 여부',
    `created_at` timestamp not null default current_timestamp,
    `updated_at` timestamp not null,
    primary key (`id`)
);

create table if not exists `token`
(
    `id` bigint not null auto_increment,
    `user_id` bigint not null comment 'user id',
    `token` varchar(255) not null comment 'token',
    `token_type` varchar(9) not null comment 'token type',
    `expired` tinyint(1) default 0 comment '토큰 만료 여부',
    `revoked` tinyint(1) default 0 comment '토큰 취소 여부' ,
    `created_at` timestamp not null default current_timestamp,
    `updated_at` timestamp not null,
    primary key (`id`)
);
create unique index `uk_token_token` on `token` (`token`);

create table if not exists `to_do`
(
    `id` bigint not null auto_increment,
    `user_id` bigint not null comment 'user id',
    `title` varchar(30) not null comment '할 일 제목',
    `content` varchar(100) null comment '할 일 내용',
    `status` varchar(7) not null comment '할 일 상태',
    `created_at` timestamp not null default current_timestamp,
    `updated_at` timestamp not null,
    primary key (`id`)
);
create index `idx_to_do_user_id` on `to_do` (`user_id`);