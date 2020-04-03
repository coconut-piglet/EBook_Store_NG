drop table if exists wishlist;

create table wishlist (
wishid int(8) not null auto_increment,
bookname varchar(16) not null,
primary key (wishid)
);

select * from wishlist;