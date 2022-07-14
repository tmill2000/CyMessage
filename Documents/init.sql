CREATE TABLE user(
	user_id INT NOT NULL AUTO_INCREMENT,
	user_name VARCHAR(4) NOT NULL,
	email VARCHAR(4),
	password VARCHAR(4) NOT NULL, 
	PRIMARY KEY(user_id, user_name)
);
CREATE TABLE user_group_association (
	group_id INT NOT NULL,
	user_id INT,
	role VARCHAR(4),
	FOREIGN KEY(group_id, user_id)
);
CREATE TABLE group (
	group_id INT NOT NULL AUTO_INCREMENT,
	group_name VARCHAR(8),
	PRIMARY KEY(group_id, group_name)
);
CREATE TABLE messages (
	message_id INT NOT NULL AUTO_INCREMENT,
	group_id INT NOT NULL,
	message_body VARCHAR(255),
	timestamp TIMESTAMP,
	sent_by INT,
	PRIMARY KEY(message_id),
	FOREIGN KEY(group_id, sent_by)
);
CREATE TABLE group_events(
	group_id INT NOT NULL,
	event_body VARCHAR(255),
	timestamp TIMESTAMP,
	FOREIGN KEY(group_id)
);