insert into security_users (login, password, active)
values ('admin', '123', true)
     , ('sill', '123', true)
     , ('paulie', '123', true)
     , ('jimmy', '123', false)
     , ('pus', '123', true)
     , ('kriss', '123', true);

insert into user_role
values (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'USER'),
       (4, 'USER'),
       (5, 'USER'),
       (6, 'USER');

insert into users (name, surname, job_title, contacts, security_id)
values ('Тони', 'Сопрано', 'Босс', '+19227042631', 1)
     , ('Сильвио', 'Данте', 'Консильери', '+19083494287', 2)
     , ('Поли', 'Галтиери', 'Капо', '+17478247519', 3)
     , ('Джимми', 'Альтиери', 'Капо', '+19041155345', 4)
     , ('Сальваторе', 'Бонпенсьеро', 'Капо', '+19012319017', 5)
     , ('Кристофер', 'Молтисанти', 'Боец', '+19913477136', 6);