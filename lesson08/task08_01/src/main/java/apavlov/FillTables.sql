-- Fill table "roles";
INSERT INTO roles(role_name) 
    VALUES('administrator'),('user'),('quest');

-- Fill table "rules";
INSERT INTO rules(rule_name, rule_description) 
    VALUES('delete', 'delete item'),
          ('update', 'update item'),
          ('create', NULL);

-- Fill addition table "rolesrules";
INSERT INTO rolesrules(id_role, id_rule) 
    VALUES(1, 1), 
          (1, 2), 
          (1, 3), 
          (2, 2), 
          (2, 3);
          
-- Fill table "users";          
INSERT INTO users(user_name, user_login, user_password, id_role) 
    VALUES('Artem', 'admin', MD5('Qa123456'), 1),
          (NULL, 'user', MD5('qwerty'), 2),
          (NULL, 'quest', MD5('123456'), 3);

-- Fill table "statuses";  
INSERT INTO statuses(status_name)
    VALUES('open'),
          ('wait'),
          ('complete');
          
-- Fill table "categories";  
INSERT INTO categories(category_name)
    VALUES('general');  

-- Fill table "items";
INSERT INTO items(id_item, item_name, item_description, id_status, id_category)
    VALUES(1, 'Item first', 'test item', 1, 1),
          (2, 'Item second', 'test item', 2, 1),
          (3, 'Item three', 'test item', 3, 1);

-- Fill table "itemcomments";
INSERT INTO itemcomments(comment_text, id_item)
    VALUES('Comment first', 1),
          ('Comment second', 1);

-- Fill table "attachedfiles";
INSERT INTO attachedfiles(file_path, id_item)
    VALUES('\\example01.png', 1),
          ('\\example02.png', 2);
