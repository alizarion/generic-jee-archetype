#!/bin/sh

# wait data start
echo "[Generic-Entry-Point] Wildfly Server starting.....";

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}
echo "Vérification des chemins..";
echo "[Generic-Entry-Point] JBOSS_HOME  : " $JBOSS_HOME
echo "[Generic-Entry-Point] JBOSS_CLI   : " $JBOSS_CLI
echo "[Generic-Entry-Point] JBOSS_MODE  : " $JBOSS_MODE
echo "[Generic-Entry-Point] JBOSS_CONFIG: " $JBOSS_CONFIG

# postgres tips to call psql without passing password
export PGPASSWORD=$DATASOURCE_PASSWORD


if [ -z $DATASOURCE_HOST ] && [ -z  $DATASOURCE_USER ] && [ -z  $DATASOURCE_PASSWORD ] && [ -z  $DATASOURCE_CATALOG ] && [ -z  $DATASOURCE_PORT ] && [ -z  $DATASOURCE_DDL ]; then
echo -e "[Generic-Entry-Point] \e[31mNo parameters specified ; server will start with default parameters.\e[0m";

echo -e "[Generic-Entry-Point] Parameters to set if you want to start the server with another database :";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_HOST :\e[0m Type of database (h2, postgres, ...).";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_USER :\e[0m User for connection.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_PASSWORD :\e[0m Password for connection.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_CATALOG :\e[0m Name of table.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_PORT :\e[0m Port of database.";
echo -e "[Generic-Entry-Point]  -  \e[31mDATASOURCE_DDL :\e[0m Behaviour of database (create-drop, update, ...).";

echo "[Generic-Entry-Point] Starting WildFly without parameters.";
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG -Dio.alizarion.jpa.dialect="org.hibernate.dialect.H2Dialect"

else
echo "[Generic-Entry-Point] Waiting database start on host $DATASOURCE_HOST:$DATASOURCE_PORT";

while ! nc $DATASOURCE_HOST $DATASOURCE_PORT </dev/null; do sleep 5; done


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

echo "[Generic-Entry-Point] Starting WildFly with parameters."
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG \
    -Dio.alizarion.datasource.driver.platform="postgres" \
    -Dio.alizarion.datasource.security.user=$DATASOURCE_USER \
    -Dio.alizarion.datasource.security.password=$DATASOURCE_PASSWORD \
    -Dio.alizarion.jpa.dialect="org.hibernate.dialect.PostgreSQL9Dialect" \
    -Dio.alizarion.datasource.connection.url="jdbc:postgresql://$DATASOURCE_HOST:$DATASOURCE_PORT/$DATASOURCE_CATALOG?stringtype=unspecified"
fi







