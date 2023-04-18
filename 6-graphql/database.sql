create table if not exists Users(
    id integer primary key auto_increment,
    name varchar(50) not null,
    email varchar(50),
    login varchar(50)
);

create table if not exists ToDos (
    id integer primary key auto_increment,
    title varchar (300) not null,
    completed integer(1),
    userId integer,
    foreign key (userId) references Users(id)
);
