INSERT INTO users (Role, Name, Surname, Email, Username, Password, Hashpassword) VALUES ('admin', 'Jonas', 'Kazlauskas', 'jonas@gmail.com', 'felix','TestPa$6', 'ggg');
INSERT INTO questions (topic, question, answers, correct_answers,hint,question_photo) VALUES (2, 'What does this sign mean??','You need to do a full stop before the sign, You need to do a full stop before the line near the sign, This is a warning to slow down', 'You need to do a full stop before the sign, You need to do a full stop before the line near the sign','red mean danger',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Give way1','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Give way2','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test1','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test2','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test3','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test4','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test5','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test6','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test7','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test8','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test9','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test10','Yes;Depends on whoes asking; No', 'Yes',1);
INSERT INTO tests (id,userId,questionIdList,userChosenAnswerList,score,topic) VALUES (1,1,'1;3','Yes,No',0,1);
INSERT INTO tests (id,userId,questionIdList,userChosenAnswerList,score,topic) VALUES (2,1,'2;3','Yes,No',0,2);
INSERT INTO tests (id,userId,questionIdList,userChosenAnswerList,score,topic) VALUES (3,1,'1;2','Yes,No',0,1);