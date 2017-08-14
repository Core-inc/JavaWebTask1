insert into t_user_groups(c_group_id, c_name) values(
0,'admin') ON CONFLICT DO NOTHING;
insert into t_user_groups(c_group_id, c_name) values(
1,'manager') ON CONFLICT DO NOTHING;
insert into t_user_groups(c_group_id, c_name) values(
2,'developer') ON CONFLICT DO NOTHING;
insert into t_user_groups(c_group_id, c_name) values(
3,'customer') ON CONFLICT DO NOTHING;

insert into t_skills(c_name) values('Java') ON CONFLICT DO NOTHING;
insert into t_skills(c_name) values('Python') ON CONFLICT DO NOTHING;

insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'first', 'first@epam.com', 'ceaf3beb8fa075f0d5a85e7eef339c9b0bce05d9bdce49e0f5f0e970480cb15efbfa5b14e370322c64bf7e276e94718b2b44a88dee198d32b80602db21779b97821377c3535e8994', 'qwe', 0) ON CONFLICT DO NOTHING;
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'second', 'second@epam.com', 'a38a12c2a19754397b855cafadbc9e5eead5257ec2be423084fb6061e2a819800b6a1e0aec3f5d351f1fd77fc3c0679faeae1be8b519a249a2b39f9e2701686cdddbe2e6644b8b3c', 'qwe', 1) ON CONFLICT DO NOTHING;
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'third', 'third@epam.com', 'ac9c7fd2b7f39d90f8ca80b12513b30994a06a473ec28b1fa54eb05445527f5dd23065408b659383f5a55c0b61f89bf51401adb9a6c74aa6b3e89f0453bdb08e7f7578d259d6e301', 'qwe', 2) ON CONFLICT DO NOTHING;
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'customer', 'igivemoney@epam.com', 'dc88053259fb66b0cbb514d10a61a95314c73e2478bc750346f72ec2c36387ca47bfd03e0bf87e70b5ee90dbbc18c54a3a60a895a95174228b56f0ea5ab2dddd814ced0f47bc15de', 'qwe', 3) ON CONFLICT DO NOTHING;


INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at)
VALUES('testExternalName', 'testInternalName', 'http://test', 0, '2016-01-19 15:00:00', '2017-10-27 02:00:00') ON CONFLICT DO NOTHING;

INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at)
VALUES('web_site', 'web_site_1', 'http://site', 0, '2017-10-15 02:00:00', '2017-10-15 02:00:00') ON CONFLICT DO NOTHING;

INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at)
VALUES('game_tanks', 'world_of_tanks', 'http://tanks', 0, '2017-10-12 01:00:00', '2017-10-12 01:00:00') ON CONFLICT DO NOTHING;


INSERT INTO t_project_requests (c_exter_name, c_specs_link, c_customer_name, c_customer_email)
VALUES('project1', 'To make web', 'Ben', 'ben@mail.ru') ON CONFLICT DO NOTHING;

INSERT INTO t_project_requests (c_exter_name, c_specs_link, c_customer_name, c_customer_email)
VALUES('project2', 'To make a game', 'Kevin', 'kevin@mail.ru') ON CONFLICT DO NOTHING;

INSERT INTO t_project_requests (c_exter_name, c_specs_link, c_customer_name, c_customer_email)
VALUES('project3', 'To make a product', 'Vasilisa', 'sitnikova.va@mail.ru') ON CONFLICT DO NOTHING;


INSERT INTO t_tasks (c_name, c_cost, c_duration, c_status, c_project_id)
VALUES ('testing_task', 10, 5, 1, 1) ON CONFLICT DO NOTHING;
