
INSERT INTO salt(Salt) VALUES (X'3ACC7DC48D5CD6782646B0F72BB4AA91');
INSERT INTO users (Role, Name, Surname, Email, Username, Password,HashPassword,saltid ) VALUES ('admin', 'Jonas', 'Kazlauskas', 'jonas@gmail.com', 'felix1',null,'1d353c892dc27539f113e82be283017b8a828593e493c047ac8c0d53e3f8e3c8d5c61a4ce218ceb7f57bb66cd69da32be38053b8c6dad187cfeb60edb2217650',1);
--Admin1#2
INSERT INTO questions (topic, question, answers, correct_answers,hint,question_photo) VALUES (2, 'What does this sign mean??','You need to do a full stop before the sign, You need to do a full stop before the line near the sign, This is a warning to slow down', 'You need to do a full stop before the sign, You need to do a full stop before the line near the sign','red mean danger',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Give way1','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Give way2','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test1','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test2','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test3','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test4','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test5','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test6','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test7','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test8','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test9','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO questions (topic, question, answers, correct_answers,question_photo) VALUES (3, 'Test10','Yes, Depends on whoes asking, No', 'Yes',1);
INSERT INTO tests (user_chosen_answer_list,score,topic) VALUES ('Yes,No',0,1);
INSERT INTO tests (user_chosen_answer_list,score,topic) VALUES ('Yes,No',0,2);
--INSERT INTO tests (id,userId,userChosenAnswerList,score,topic) VALUES (3,1'Yes,No',0,1);

INSERT INTO question_test(test_id, question_id) VALUES (1,5);
INSERT INTO question_test(test_id, question_id) VALUES (1,1);
INSERT INTO question_test(test_id, question_id) VALUES (1,2);
