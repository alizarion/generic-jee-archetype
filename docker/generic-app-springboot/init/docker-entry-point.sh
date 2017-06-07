#!/bin/sh

# wait data start
echo "[Generic-Entry-Point] Tomcat Server starting.....";

# postgres tips to call psql without passing password
export PGPASSWORD=$DATASOURCE_PASSWORD

if [ -z $DATASOURCE ] && [ -z  $DATASOURCE_USER ] && [ -z  $DATASOURCE_PASSWORD ] && [ -z  $DATASOURCE_CATALOG ] && [ -z  $DATASOURCE_PORT ] && [ -z  $DATASOURCE_DDL ]; then

echo -e "[Generic-Entry-Point] \e[31mNo parameters specified ; server will start with default parameters.\e[0m";

echo -e "[Generic-Entry-Point] Parameters to set if you want to start the server with another database :";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_HOST :\e[0m Type of database (h2, postgres, ...).";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_USER :\e[0m User for connection.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_PASSWORD :\e[0m Password for connection.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_CATALOG :\e[0m Name of table.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_PORT :\e[0m Port of database.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_DDL :\e[0m Behaviour of database (create-drop, update, ...).";

echo "[Generic-Entry-Point] Starting Tomcat without parameters.";

else
mv /opt/lib/*.jar /usr/local/tomcat/lib

echo "[Generic-Entry-Point] Update context.xml datasource ";
mv /opt/context.xml /usr/local/tomcat/conf/



sed -i.bak -e "s;%DATASOURCE_HOST%;$DATASOURCE_HOST;g" -e "s;%DATASOURCE_USER%;$DATASOURCE_USER;g" -e "s;%DATASOURCE_DDL%;$DATASOURCE_DDL;g" -e "s;%DATASOURCE_PASSWORD%;$DATASOURCE_PASSWORD;g" -e "s;%DATASOURCE_PORT%;$DATASOURCE_PORT;g" -e "s;%DATASOURCE_CATALOG%;$DATASOURCE_CATALOG;g" /usr/local/tomcat/conf/context.xml

echo "[Generic-Entry-Point] Waiting database start on host $DATASOURCE_HOST:$DATASOURCE_PORT";

while ! nc $DATASOURCE_HOST $DATASOURCE_PORT </dev/null; do sleep 10; done

if psql -h $DATASOURCE_HOST -U $DATASOURCE_USER  -lqt | cut -d \| -f 1 | grep -qw $DATASOURCE_CATALOG ; then

# database exists
    # $? is 0
echo "[Generic-Entry-Point] $DATASOURCE_CATALOG database already exists, process to update... ";

else
    # ruh-roh
    # $? is 1
echo "[Generic-Entry-Point] $DATASOURCE_CATALOG database does not exist, process to install..."

psql -h $DATASOURCE_HOST -U $DATASOURCE_USER  -v ON_ERROR_STOP=0 --username "$DATASOURCE_USER" <<-EOSQL

 DROP DATABASE IF EXISTS "$DATASOURCE_CATALOG";

 CREATE DATABASE "$DATASOURCE_CATALOG" WITH ENCODING='UTF8' OWNER=$DATASOURCE_USER;

EOSQL

fi
fi
/usr/local/tomcat/bin/catalina.sh run