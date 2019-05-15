#
#Creates the database and user for this service
#Run this as root
create database jokes;
create user 'jokeuser'@'localhost' identified by 'Passw0rd1';
grant all on jokes to 'jokeuser'@'localhost';