# generic-jee-archetype
Simple projet, posposition de structure Maven permettant le multipackaging d'applications JEE



#pré-requis: 
Maven 3.1 ou supérieur.    
JDK 1.7 ou supérieur. 

# Build 
* Téléchargez le projet.
* Dans le répertoire racine, faites un :         
        ```mvn clean install```    


# L'application 
une simple application permettant d'effectuer des opérations CRUD sur l'entité Person via des appels Rest.   

un client angular permettant de la tester est disponible à cette adresse : 
http://plnkr.co/edit/eHZ0dgTFmveJDIY71gUa?p=preview    

## Business packages

* `business-entities` : JPA entities and DAO.
* `Service` : CDI injectable trasactional services
* `tools` : Simple helper and tools classes.
* `rest-api`: JAX-RS exposed Rest Endpoint
* `persistence` : persistence.xml file for jpa entities

## Deployable packages
* `wildfly-package` : deployable war on wildfly
* `springboot-package` :(TODO) deployagle springboot app
* `tomcat-package` : (TODO) deployable war on tomcat 


# L'objectit

structurer les imports de nos projets afin de restreindre l'utilisation des frameworks aux standards JEE et réduire l'adhérence de nos projets à des librairies tierces.
les package metiers ont pour seul dependance javaee
les packages (wildfly-package et springboot-package) peuvent étendre les dépendances du parent avec des libs liés à leurs environnement de déploiement, ces derniers ne doivent contenir aucune intelligence, pas de code fonctionnel.


# L'application utilise les standards suivants: 
* JPA(Java Persistence) JSR-338 
* JAX-RS 2.0 (Java API for RESTful Web Services) JSR 339
* CDI (Contexts and Dependency Injection) JSR 346
* EJB (Enterprise JavaBeans) JSR 220 


# Les implémentations sous Wildly
* JPA : Hibernate.
* JAX-RS : RestEasy
* CDI : WELD
* EJB : EJB3

# Tester la version Wildfly : 
Pas besoin d'installer wildfly maven le fera pour vous.
* Dans le répertoire wildfly-package, faites un    
        ```mvn wildfly:run -Dwildfly.version=8.2.0.Final````


# Todo
completer le package spring avec les bonnes libs(jpa, cdi, jax-rs, ejb)



