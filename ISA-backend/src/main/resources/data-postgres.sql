INSERT INTO public.user (user_id, user_name, user_surname, user_email, user_jmbg, user_password, user_address, user_city, user_country, user_phone, user_gender, user_type, user_occupation, user_company, points, user_category, dtype, enabled) VALUES (default, 'Pera', 'Peric', 'pera@gmail.com', '123', 'asd', 'Street Number 1', 'Novi Sad', 'Serbia', '12345', 0, 0, 'none', 'FTN', 0, 0, 'UserRegistered', true);

INSERT INTO public.center (center_id, center_name, center_address, center_description, center_avg_grade) VALUES (default, 'Center One', 'Street Number 1', 'description1', 8);
INSERT INTO public.center (center_id, center_name, center_address, center_description, center_avg_grade) VALUES (default, 'Center Two', 'Street Number 2', 'description2', 5);
INSERT INTO public.center (center_id, center_name, center_address, center_description, center_avg_grade) VALUES (default, 'Center Three', 'Street Number 3', 'description3', 7);
INSERT INTO public.center (center_id, center_name, center_address, center_description, center_avg_grade) VALUES (default, 'Center Four', 'Street Number 4', 'description4', 10);
INSERT INTO public.center (center_id, center_name, center_address, center_description, center_avg_grade) VALUES (default, 'Center Five', 'Street Number 5', 'description5', 5);

INSERT INTO public.appointment(id, date, time, center_center_id, user_user_id) VALUES (default, '2023-1-1', '10:00', 1, null);
INSERT INTO public.appointment(id, date, time, center_center_id, user_user_id) VALUES (default, '2023-1-1', '11:00', 1, null);