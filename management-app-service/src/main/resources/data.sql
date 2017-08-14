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
'first', 'first@epam.com', 'qwe', 'qwe', 0) ON CONFLICT DO NOTHING;
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'second', 'second@epam.com', 'qwe', 'qwe', 1) ON CONFLICT DO NOTHING;
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'third', 'third@epam.com', 'qwe', 'qwe', 2) ON CONFLICT DO NOTHING;
insert into t_users(c_name, c_email, c_password, c_salt, c_user_group_id) values(
'customer', 'igivemoney@epam.com', 'qwe', 'qwe', 3) ON CONFLICT DO NOTHING;

insert into t_customers_projects(id, c_customer_id, c_project_id) values(
1, 4, 1) ON CONFLICT DO NOTHING;

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
