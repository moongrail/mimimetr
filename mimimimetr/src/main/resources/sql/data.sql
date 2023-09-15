INSERT INTO users (username,password)
VALUES ('public','helloworld!')
ON CONFLICT DO NOTHING ;