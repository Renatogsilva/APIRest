CREATE TABLE Cliente(
	Id bigint not null auto_increment,
    Nome varchar(100) not null,
    email varchar(200) not null,
    telefone varchar(20) not null,
    
    primary key(id)
);