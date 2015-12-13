#!/bin/sh


sudo apt-get install postgresql


sudo /etc/init.d/postgresql restart


#https://github.com/snowplow/snowplow/wiki/Setting-up-PostgreSQL

#sudo su - postgres
#psql

# CREATE USER power_user SUPERUSER;
# ALTER USER power_user WITH PASSWORD 'password';
# CREATE USER suraksha_user NOSUPERUSER;
# ALTER USER suraksha_user WITH PASSWORD ‘password’;
# CREATE DATABASE suraksha_db WITH OWNER suraksha_user;


#sudo vim /etc/postgresql/9.3/main/pg_hba.conf
#replace peer with md5
#restart the dbfor it to take effect 
#	sudo /etc/init.d/postgresql restart


#psql -U suraksha_user suraksha_db

#CREATE TABLE ASSAULTS (
#    ID        char(20) NOT NULL,
#    LAT       varchar(10) NOT NULL,
#    LNT       varchar(10) NOT NULL,
#    DATE      date NOT NULL,
#    MODE      integer NOT NULL
#);

