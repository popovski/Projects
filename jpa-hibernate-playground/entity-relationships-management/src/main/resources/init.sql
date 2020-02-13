    create table hibernate_sequence (
       next_val bigint
    ) ENGINE=InnoDB;
    
    insert into hibernate_sequence values ( 1 );
    
      
        create table role (
       id bigint not null,
        creation_date datetime,
        role_type varchar(255),
        user_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;
    
    
        create table users (
       id bigint not null,
        birthday date,
        creation_date date,
        email varchar(255),
        last_modified_password datetime,
        password varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;