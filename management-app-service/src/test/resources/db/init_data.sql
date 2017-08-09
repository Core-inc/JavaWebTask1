insert into t_user_groups(c_group_id, c_name) values(
0,'admin');
insert into t_user_groups(c_group_id, c_name) values(
1,'manager');
insert into t_user_groups(c_group_id, c_name) values(
2,'developer');

insert into t_skills(c_name) values('Java');
insert into t_skills(c_name) values('Python');


insert into t_user_groups(c_group_id, c_name) values(
0,'admin');

INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at)
      VALUES("testOuterName", "testInterName", "http://test", 0, "03-07-2017", "03-07-2017");
INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at)
      VALUES("Web-site", "Web-site-1", "http://web_site_1", 0, "05-07-2017", "05-07-2017");
INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at)
            VALUES("Game_tanks", "World_of_tanks", "http://world_of_tanks", 0, "07-07-2017", "07-07-2017");
