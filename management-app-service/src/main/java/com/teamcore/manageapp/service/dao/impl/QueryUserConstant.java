package com.teamcore.manageapp.service.dao.impl;

class QueryUserConstant {

    final static String SELECT_ALL_USERS_BY_ROLE =
            "SELECT " +
                    "t_users.id as user_id, t_users.c_name as user_name, " +
                    "c_email, c_password, c_salt, c_created_at, c_updated_at, " +
                    "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
                    "FROM t_users " +
                    "LEFT JOIN t_user_groups " +
                    "ON (t_users.c_user_group_id = t_user_groups.c_group_id) " +
                    "WHERE t_user_groups.c_group_id = :roleId";


    final static String SELECT_ALL_USERS_BY_NAME_AND_ROLE =
            "SELECT " +
                    "t_users.id as user_id, t_users.c_name as user_name, " +
                    "c_email, c_password, c_salt, c_created_at, c_updated_at, " +
                    "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
                    "FROM t_users " +
                    "LEFT JOIN t_user_groups " +
                    "ON (t_users.c_user_group_id = t_user_groups.c_group_id) " +
                    "WHERE t_user_groups.c_group_id = :roleId AND t_users.c_name = :name";


    final static String SELECT_USER_BY_ID_AND_ROLE =
            "SELECT " +
                    "t_users.id as user_id, t_users.c_name as user_name, " +
                    "c_email, c_password, c_salt, c_created_at, c_updated_at, " +
                    "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
                    "FROM t_users " +
                    "LEFT JOIN t_user_groups " +
                    "ON (t_users.c_user_group_id = t_user_groups.c_group_id) " +
                    "WHERE t_user_groups.c_group_id = :roleId AND t_users.id = :id";


    final static String SELECT_USER_BY_EMAIL_AND_ROLE =
            "SELECT " +
                    "t_users.id as user_id, t_users.c_name as user_name, " +
                    "c_email, c_password, c_salt, c_created_at, c_updated_at, " +
                    "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
                    "FROM t_users " +
                    "LEFT JOIN t_user_groups " +
                    "ON (t_users.c_user_group_id = t_user_groups.c_group_id) " +
                    "WHERE t_user_groups.c_group_id = :roleId AND t_users.c_email = :email";

    final static String UPDATE_USER_BY_ROLE = "UPDATE t_users " +
            "SET c_name = :name, c_email = :email, c_password = :password, c_salt = :salt, " +
            "c_created_at = :createdAt, c_updated_at = :updatedAt " +
            "WHERE id = :id AND c_user_group_id = :roleId";

    final static String DELETE_USER_BY_ROLE = "DELETE FROM t_users " +
            "WHERE id = :id AND c_user_group_id = :roleId";

    final static String INSERT_USER = "INSERT INTO t_users " +
            "(c_name, c_email, c_password, c_salt, c_created_at, c_updated_at, c_user_group_id) " +
            "values (:name, :email, :password, :salt, :createdAt, :updatedAt, :roleId)";

}
