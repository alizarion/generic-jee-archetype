#Héritage du Dockerfile java8/tomcat
FROM missiontomars.itesoft.com/itesoft/tomcat8

#Ouverture port 8080
EXPOSE 8080

#
ADD webapps/ /usr/local/tomcat/webapps

#
ADD init /opt/

RUN chmod +x /opt/docker-entry-point.sh

#Fichier .sh à exécuter
ENTRYPOINT /opt/docker-entry-point.sh
