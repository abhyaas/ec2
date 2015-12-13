#!/bin/sh


sudo apt-get update
sudo apt-get upgrade

./setup_postgresql.sh
./setup_scala.sh

