INSERT INTO users (Role, Name, Surname, Email, Username, Password, Hashpassword) VALUES ('admin', 'Jonas', 'Kazlauskas', 'jonas@gmail.com', 'felix','TestPa$6', 'ggg');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (1, 'Are you ready?','Yes;Depends on whoes asking; No', 'Yes');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'Are you ready to learn?','Yes;Depends on whoes asking; No', 'Yes');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (1, 'Are you ready to learn driving?','Yes;Depends on whoes asking; No', 'Yes');
INSERT INTO tests (id,userId,questionIdList,userChosenAnswerList,score,topic) VALUES (1,1,'1;3','Yes,No',0,1);
INSERT INTO tests (id,userId,questionIdList,userChosenAnswerList,score,topic) VALUES (2,1,'2;3','Yes,No',0,2);
INSERT INTO tests (id,userId,questionIdList,userChosenAnswerList,score,topic) VALUES (3,1,'1;2','Yes,No',0,1);