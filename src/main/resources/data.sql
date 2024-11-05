INSERT INTO users (username, password, active) 
SELECT 'admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

INSERT INTO user_roles (user_id, role)
SELECT id, 'ADMIN'
FROM users
WHERE username = 'admin'
AND NOT EXISTS (
    SELECT 1 FROM user_roles ur
    INNER JOIN users u ON ur.user_id = u.id
    WHERE u.username = 'admin' AND ur.role = 'ADMIN'
);