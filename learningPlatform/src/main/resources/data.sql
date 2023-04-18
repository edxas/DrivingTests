
INSERT INTO salt(Salt) VALUES (X'3ACC7DC48D5CD6782646B0F72BB4AA91');
INSERT INTO users (Role, Name, Surname, Email, Username, Password,HashPassword,saltid ) VALUES ('admin', 'Jonas', 'Kazlauskas', 'jonas@gmail.com', 'felix1',null,'1d353c892dc27539f113e82be283017b8a828593e493c047ac8c0d53e3f8e3c8d5c61a4ce218ceb7f57bb66cd69da32be38053b8c6dad187cfeb60edb2217650',1);
--Admin1#2
INSERT INTO questions (topic, question, answers, correct_answers,hint,question_photo) VALUES (2, 'What does this sign mean??','You need to do a full stop before the sign, You need to do a full stop before the line near the sign, This is a warning to slow down', 'You need to do a full stop before the sign, You need to do a full stop before the line near the sign','red mean danger',1);

INSERT INTO questions (topic, question, answers, correct_answers) VALUES ('signs', 'This is a Right Turn Not Allowed road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Highway Exit" road sign.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This sign indicates that bicyclists must not use this road.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Police Only" road sign.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Radio Station Ahead" road sign.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Curves on the Road" road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Divided Highway Ends" road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This sign warns the drivers that they should not leave the pavement.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Steep Hill Ahead" road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "U-Turn Only','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Blinkers Only" road sign.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Commercial Vehicles Only" road sign.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Road Shoulder Much Higher Than Road Surface" road sign.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Turn Left Or Go Straight" road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Keep to the Right of the Traffic Island" road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'The broken white lines on the pavement mean that passing is not allowed.','True, False', 'False');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Hospital Ahead" road sign.','True, False', 'True');
INSERT INTO questions (topic, question, answers, correct_answers) VALUES (2, 'This is a "Two-way Traffic" road sign.','True, False', 'False');



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
INSERT INTO tests (userid,score,topic) VALUES (1,4,2);
INSERT INTO tests (userid,score,topic) VALUES (1,7,2);
INSERT INTO tests (userid,score,topic) VALUES (1,5,2);
INSERT INTO tests (userid,score,topic) VALUES (1,4,3);
INSERT INTO tests (userid,score,topic) VALUES (1,2,3);
INSERT INTO tests (userid,score,topic) VALUES (1,2,3);
INSERT INTO tests (userid,score,topic) VALUES (1,4,1);
INSERT INTO tests (userid,score,topic) VALUES (1,9,3);
INSERT INTO tests (userid,score,topic) VALUES (1,8,3);
INSERT INTO tests (userid,score,topic) VALUES (1,8,2);
INSERT INTO tests (userid,score,topic) VALUES (1,9,2);


INSERT INTO question_test(test_id, question_id) VALUES (1,5);
INSERT INTO question_test(test_id, question_id) VALUES (1,1);
INSERT INTO question_test(test_id, question_id) VALUES (1,2);
