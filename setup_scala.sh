#!/bin/sh


sudo apt-get update
sudo apt-get upgrade

sudo apt-get install default-jdk

curl https://raw.githubusercontent.com/n8han/conscript/master/setup.sh | sh

# edit .proifle and add : 
#export PATH=$PATH:/home/ubuntu/bin/
 
cs n8han/giter8
g8 scalatra/scalatra-sbt 

