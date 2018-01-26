-- Create table "role" - role for users;
CREATE TABLE IF NOT EXISTS roles(
    id_role SERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

-- Create table "rules" - rules for rule;
CREATE TABLE IF NOT EXISTS rules(
    id_rule SERIAL PRIMARY KEY,
    rule_name VARCHAR(100) NOT NULL UNIQUE,
    rule_description VARCHAR(1000) 
);

-- Create additional table "rolesrules" - rules - role = many to many;
CREATE TABLE IF NOT EXISTS rolesrules(
    id_role INTEGER NOT NULL,
    id_rule INTEGER NOT NULL,
    PRIMARY KEY(id_role, id_rule),
    FOREIGN KEY(id_role) REFERENCES roles(id_role),
    FOREIGN KEY(id_rule) REFERENCES rules(id_rule)
);

-- Create table "users" - users;
CREATE TABLE IF NOT EXISTS users(
    id_user SERIAL PRIMARY KEY,
    user_name VARCHAR(100),
    user_login VARCHAR(50) NOT NULL UNIQUE,
    user_password VARCHAR(50) NOT NULL UNIQUE,
    user_create_date TIMESTAMP DEFAULT now() NOT NULL,
    id_role INTEGER REFERENCES roles(id_role) DEFAULT 2 NOT NULL
);

-- Create table "statuses" - status item;
CREATE TABLE IF NOT EXISTS statuses(
    id_status SERIAL PRIMARY KEY,
    status_name VARCHAR(255) NOT NULL UNIQUE
);

-- Create table "categories" - category item;
CREATE TABLE IF NOT EXISTS categories(
    id_category SERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

-- Create table "items" - table items;
CREATE TABLE IF NOT EXISTS items(
    id_item INTEGER PRIMARY KEY NOT NULL,
    item_name VARCHAR(100) NOT NULL UNIQUE,
    item_description VARCHAR(1000) NOT NULL,
    item_create_date TIMESTAMP DEFAULT now() NOT NULL,
    id_status INTEGER REFERENCES statuses(id_status) NOT NULL,
    id_category INTEGER REFERENCES categories(id_category) NOT NULL,
    FOREIGN KEY(id_item) REFERENCES users(id_user)
);

-- Create table "itemcomments" - table comments for item;
CREATE TABLE IF NOT EXISTS itemcomments(
    id_comment SERIAL PRIMARY KEY,
    comment_text VARCHAR(3000) NOT NULL,
    comment_create_date TIMESTAMP DEFAULT now() NOT NULL,
    id_item INTEGER REFERENCES items(id_item) NOT NULL
);

-- Create table "attachedfiles" - table attached files to item;
CREATE TABLE IF NOT EXISTS attachedfiles(
    id_file SERIAL PRIMARY KEY,
    file_path VARCHAR(1000) NOT NULL,
    file_description VARCHAR(500),
    file_date_attached TIMESTAMP DEFAULT now() NOT NULL,
    id_item INTEGER REFERENCES items(id_item) NOT NULL
);