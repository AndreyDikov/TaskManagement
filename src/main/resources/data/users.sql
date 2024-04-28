insert into security_users (login, password, active)
values ('admin', '$2a$10$JtRcZrjN631R71Q41j6Tau0FwCyTZ7tJhW/.6p21ZP2KkXtD.GvZW', true)
     , ('sill', '$2a$10$f6LWRbofnE5Bs012V4hK9.D3wIAoEHiJME5b8jUtfTb9/08jUa5K6', true)
     , ('paulie', '$2a$10$fmjPEDZoT/KGYygNVJjyGemdxd3F/JtcWiPHObO2uPdkzL1rws19q', true)
     , ('jimmy', '$2a$10$gFOeptzcYdt1M5qKRBTVROwOafSf6s1hWMk/kHGF56F8xNoETLhoq', false)
     , ('pus', '$2a$10$6N2J4do5QLavMKfViYRY7eMYvLzvZNvulEHgEg7nPiDThv1wVHdom', true)
     , ('kriss', '$2a$10$eGOtEQTIFzFFV1RVR3sHtuMhg6lRmQE1ACvLMF6vRA327QNkryZqC', true);

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