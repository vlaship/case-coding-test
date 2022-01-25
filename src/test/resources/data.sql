insert into users (firstname,lastname,email) values('admin','admin','admin@admin.com')

insert into cases (title,description,severity,status,user_id) values('title1','desc1',1,'OPEN',1)
insert into cases (title,description,severity,status,user_id) values('title2','desc2',2,'OPEN',1)
insert into cases (title,description,severity,status,user_id) values('title3','desc3',3,'CLOSE',1)

insert into notes (case_id,details) values(1,'details1')
insert into notes (case_id,details) values(2,'details2')
insert into notes (case_id,details) values(3,'details3')
insert into notes (case_id,details) values(1,'details1')
