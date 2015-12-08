#!/bin/sh


sudo apt-get install postgresql


sudo /etc/init.d/postgresql restart


#https://github.com/snowplow/snowplow/wiki/Setting-up-PostgreSQL

#sudo su - postgres
#psql

# CREATE USER power_user SUPERUSER;
# ALTER USER power_user WITH PASSWORD 'password';
# CREATE USER app_user NOSUPERUSER;
# ALTER USER app_user WITH PASSWORD ‘password’;
# CREATE DATABASE application WITH OWNER app_user;


#sudo vim /etc/postgresql/9.3/main/pg_hba.conf
#replace peer with md5

#psql -U app_user application

