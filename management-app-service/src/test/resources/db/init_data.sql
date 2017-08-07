insert into t_user_groups(c_group_id, c_name) values(
0,'admin');
insert into t_user_groups(c_group_id, c_name) values(
1,'manager');
insert into t_user_groups(c_group_id, c_name) values(
2,'developer');

insert into t_skills(c_name) values('Java');
insert into t_skills(c_name) values('Python');

insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'first', 'first@epam.com', 'qwe', 'qwe', 0);
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'second', 'second@epam.com', 'qwe', 'qwe', 1);
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'third', 'third@epam.com', 'qwe', 'qwe', 2);