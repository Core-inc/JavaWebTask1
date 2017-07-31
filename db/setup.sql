drop table if exists t_users CASCADE;
drop table if exists t_user_groups CASCADE;
drop table if exists t_developers CASCADE;
drop table if exists t_projects CASCADE;
drop table if exists t_tasks CASCADE;
drop table if exists t_developers_tasks CASCADE;


create table t_user_groups (
	id		serial primary key not null,
	c_name		text not null unique
);

create table t_users (
	id				serial primary key not null,
	c_name       		varchar(255) not null unique,
	c_email 			varchar(255) not null unique,
	c_password   		varchar(255) not null,
	c_salt       		varchar(255) not null,
	c_created_at 		timestamp DEFAULT current_timestamp not null,
	c_updated_at 		timestamp DEFAULT current_timestamp not null,
	c_user_group_id 	integer not null,
	FOREIGN KEY (c_user_group_id) REFERENCES t_user_groups(id)
);


create table t_developers (
	id				serial primary key not null,
	c_user_id			integer not null unique,
	c_skill_level 		integer not null,	
	FOREIGN KEY (c_user_id) REFERENCES t_users(id)
);


create table t_projects (
	id				serial primary key not null,
	c_exter_name		varchar(511) not null,
	c_inter_name		text not null,
	c_specs_link		text not null,
	c_status			integer not null,
	c_created_at 		timestamp DEFAULT current_timestamp not null,
	c_updated_at 		timestamp DEFAULT current_timestamp not null
);

--tasks that developers will do
create table t_tasks (
	id			serial primary key not null,
	c_name			text not null,
	c_cost 			bigint null,
	c_duration		integer null,
	c_status		integer not null,
	c_created_at 	timestamp DEFAULT current_timestamp not null,
	c_updated_at 	timestamp DEFAULT current_timestamp not null,
	c_project_id 	integer not null,
	FOREIGN KEY (c_project_id) REFERENCES t_projects(id)
);

create table t_developers_tasks (
	id 				serial primary key not null,
	c_developer_id 		integer not null,
	c_task_id 			integer not null,
	FOREIGN KEY (c_developer_id) REFERENCES t_users(id),
	FOREIGN KEY (c_task_id) REFERENCES t_tasks(id),
	UNIQUE(c_developer_id)
);

