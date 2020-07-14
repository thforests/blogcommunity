create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	notifier_name varchar(100),
	outer_title varchar(256),
	receiver bigint not null,
	outerId bigint not null,
	type int not null,
	gmt_craete bigint not null,
	status int default 0 not null,
	constraint notification_pk
		primary key (id)
);

