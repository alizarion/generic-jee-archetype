# generic-jee-archetype
Projet exemple, prosposition de structure Maven permettant le multipackaging d'applications JEE.



#Pré-requis: 
* Maven 3.1 ou supérieur.    
* JDK 1.7 ou supérieur. 
* Docker et docker-compose.
# Build 

* Téléchargez le projet.
* Dans le répertoire racine, exécuter la commande:         
        ```mvn clean install```    


# L'application 
Une simple application permettant d'effectuer des opérations CRUD sur l'entité Person via des appels Rest.   

Un client Angular permettant de la tester est disponible à cette adresse : 
http://plnkr.co/edit/qzLz3AyOzs8JoA6fqFWR?p=preview  

## Business packages

* `business-entities` : JPA entities and DAO.
* `Service` : CDI injectable trasactional services
* `tools` : Simple helper and tools classes.
* `rest-api`: JAX-RS exposed Rest Endpoint
* `persistence` : persistence.xml file for jpa en

## Deployable packages
Pour chaque package, les déploiements possibles sont Wildfly et Tomcat (packaging .war).
* `deliverable` : Déployer sur un serveur installé localement.
* `docker` : Contient les images Docker avec les serveurs d'applications et le .war à déployer

# L'objectif

Structurer les imports des projets afin de restreindre l'utilisation des frameworks aux standards JEE et réduire l'adhérence du code à des librairies tierces.
les package metiers ont pour seul dépendance javaee
les packages (deliverable et docker) peuvent étendre les dépendances du parent avec des librairoes liées à leurs environnements de déploiement, ces derniers ne doivent contenir aucune intelligence, pas de code fonctionnel.

# L'application utilise les standards suivants: 
* JPA(Java Persistence) JSR-338 
* JAX-RS 2.0 (Java API for RESTful Web Services) JSR 339
* CDI (Contexts and Dependency Injection) JSR 346
* JTA : (JavaTM Transaction API) JSR 907

# Wildfly
* JPA : Hibernate.
* JAX-RS : RestEasy
* CDI : WELD
* Jta : 

## Tester la version Wildfly : 
Pas besoin d'installer wildfly maven le fera pour vous.
* `cd generic-jee-archetype`  
* `mvn clean install`
* `cd deliverable/wildfly-package`   
* `mvn wildfly:run -Dwildfly.version=8.2.0.Final`

# Spring-boot 
* JAX-RS : Jersey
* JTA : Atomikos
* JPA : Spring-data
* CDI : HK2

## Tester la version SpringBoot

* Téléchargez le projet
* `cd generic-jee-archetype`  
* `mvn clean install `  
* `cd deliverable/springboot/fatjar`  
* `mvn clean install`
* `mvn spring-boot:run`   


# Tomcat
* JAX-RS : Jersey
* JTA : Atomikos
* JPA : Spring-data
* CDI : HK2

## Tester la version Tomcat

* Téléchargez le projet
* `cd generic-jee-archetype`
* `mvn clean install `
* `cd deliverable/springboot/fatjar
* `mvn clean install`
* `mvn tomcat7:run-war``

# Docker 

* Téléchargez le projet
* `cd generic-jee-archetype`
* `mvn clean install -P docker`
* `cd docker`
* `ocker-compose up`

