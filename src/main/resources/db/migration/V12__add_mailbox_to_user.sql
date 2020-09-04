alter table user
	add mailbox int null;

create unique index user_mailbox_uindex
	on user (mailbox);