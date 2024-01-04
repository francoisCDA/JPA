/**************************************
		DEMO JPA
***************************************/

CREATE DATABASE IF NOT EXISTS jpa_demo;

DROP DATABASE IF EXISTS jpa_demo;

USE jpa_demo;

select * from person;

/*************************************
		TP TODOLIST
**************************************/

DROP DATABASE IF EXISTS todo_list;

CREATE DATABASE IF NOT EXISTS todo_list;

USE todo_list;


DROP TABLE IF EXISTS todo;

select * from todo join todo_info on todo.info_tache_id = todo_info.id_info_tache;
select * from todo;
select * from todo_info;
/*************************************
		DEMO OneToOne House/adresse
**************************************/


select * from maison join adresse on maison.adresse_id = adresse.id_adresse ;
select * from maison ;
select * from adresse;