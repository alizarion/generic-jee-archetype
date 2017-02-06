#!/bin/sh

# wait data start
echo "init wildfly";

# postgres tips to call psql without passing password
export PGPASSWORD=$DATASOURCE_PASSWORD


if [ -z $DATASOURCE ] && [ -z  $DATASOURCE_USER ] && [ -z  $DATASOURCE_PASSWORD ] && [ -z  $DATASOURCE_CATALOG ] && [ -z  $DATASOURCE_PORT ] && [ -z  $DATASOURCE_DDL ]; then

echo "DATASOURCE, DATASOURCE_USER ,DATASOURCE_PASSWORD, DATASOURCE_CATALOG starting transient instance";

else
echo "update context.xml datasource ";

mv /opt/context.xml /usr/local/tomcat/conf/

sed -i.bak -e "s;%DATASOURCE%;$DATASOURCE;g" -e "s;%DATASOURCE_USER%;$DATASOURCE_USER;g" -e "s;%DATASOURCE_DDL%;$DATASOURCE_DDL;g" -e "s;%DATASOURCE_PASSWORD%;$DATASOURCE_PASSWORD;g" -e "s;%DATASOURCE_PORT%;$DATASOURCE_PORT;g" -e "s;%DATASOURCE_CATALOG%;$DATASOURCE_CATALOG;g" /usr/local/tomcat/conf/context.xml

echo "Waiting database  start on host $DATASOURCE:$DATASOURCE_PORT";

while ! nc $DATASOURCE $DATASOURCE_PORT </dev/null; do sleep 10; done

while ! nc $DATASOURCE $DATASOURCE_PORT </dev/null; do sleep 10; done

if psql -h $DATASOURCE -U $DATASOURCE_USER  -lqt | cut -d \| -f 1 | grep -qw $DATASOURCE_CATALOG ; then

# database exists
    # $? is 0
echo "$DATASOURCE_CATALOG database existe, process to update .. ";

else
    # ruh-roh
    # $? is 1
echo "$DATASOURCE_CATALOG database does not exist, process to install"

psql -h $DATASOURCE -U $DATASOURCE_USER  -v ON_ERROR_STOP=0 --username "$DATASOURCE_USER" <<-EOSQL

 DROP DATABASE IF EXISTS "$DATASOURCE_CATALOG";

 CREATE DATABASE "$DATASOURCE_CATALOG" WITH ENCODING='UTF8' OWNER=$DATASOURCE_USER;

EOSQL

fi


fi

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh
JBOSS_MODE=${1:-"standalone"}
JBOSS_CONFIG=${2:-"$JBOSS_MODE.xml"}

echo "JBOSS_HOME  : " $JBOSS_HOME
echo "JBOSS_CLI   : " $JBOSS_CLI
echo "JBOSS_MODE  : " $JBOSS_MODE
echo "JBOSS_CONFIG: " $JBOSS_CONFIG

echo "=> Starting WildFly"
$JBOSS_HOME/bin/$JBOSS_MODE.sh -b 0.0.0.0 -c $JBOSS_CONFIG \
    -Dio.alizarion.datasource.driver.platform=$DATASOURCE_PLATFORM \
    -Dio.alizarion.datasource.jndi=$DATASOURCE_JNDI \
    -Dio.alizarion.datasource.name=$DATASOURCE_NAME \
    -Dio.alizarion.datasource.connection.url=$DATASOURCE_CONNECTION \
    -Dio.alizarion.datasource.security.user=$DATASOURCE_USER \
    -Dio.alizarion.datasource.security.password=$DATASOURCE_PASSWORD






