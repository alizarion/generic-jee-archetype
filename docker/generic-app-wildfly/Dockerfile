FROM missiontomars.itesoft.com/itesoft/wildfly-8

#edit app.war
ADD webapps/ /opt/jboss/wildfly/standalone/deployments/

#Ouverture port 8080
EXPOSE 8080

#
ADD init /opt/

RUN chmod +x /opt/docker-entry-point.sh

#Fichier .sh à exécuter
ENTRYPOINT /opt/docker-entry-point.sh