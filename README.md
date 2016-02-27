# generic-jee-archetype
Simple projet, posposition de structure Maven permettant le multipackaging d'applications JEE

#pré-requis: 
Maven 3.1 ou supérieur.
JDK 1.7 ou supérieur 

#Build 
*Téléchargez le projet.
*Dans le répertoire racine, faites un :         
        mvn clean install     

#Tester la version JEE sous wildfly : 
*Dans le répertoire wildfly-package, faites un    
        mvn wildfly:run -Dwildfly.version=8.2.0.Final
