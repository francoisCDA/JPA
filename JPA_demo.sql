/**************************************
		DEMO JPA
***************************************/

CREATE DATABASE IF NOT EXISTS jpa_demo;



USE jpa_demo;

select * from person;

/*************************************
		TP TODOLIST
**************************************/

CREATE DATABASE IF NOT EXISTS todo_list;

USE todo_list;

DROP TABLE IF EXISTS todo;


/*************************************
		DEMO OneToOne House/adresse
**************************************/


select * from maison join adresse on maison.adresse_id = adresse.id_adresse ;
select * from maison ;
select * from adresse;