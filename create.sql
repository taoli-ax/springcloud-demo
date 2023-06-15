create table if not exists products(
    pid int auto_increment primary key,
    pname varchar(20),
    pprice DECIMAL(10,2),
    storecount int,
    description text
);

insert into products values(null,"年糕",2.0,30,"好吃");
insert into products values(null,"鱿鱼须",2.0,20,"也很好吃");
insert into products values(null,"虾子",4.0,15,"不错");