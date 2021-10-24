create table todo_item (
    id bigserial primary key,
    name varchar(128) not null,
    deadline timestamp,
    created_at timestamp not null,
    priority varchar(10)
);
