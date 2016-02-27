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

# L'objectif 
L'ojectif de ce projet est de démontrer qu'il est possible de séparer les dépendances relatives aux environnement de déploiement du code de nos projets, en se basant sur l'héritage de dépendances de maven nous pouvons contraindre l'utilisation des libraire souhaité.   
seul les packages (wildfly-package et springboot-package) peuvent étendre les dépendances du parent avec des libs liés à leurs environnement de déploiement, cependant ces derniers ne doivent contenir aucune intelligence, aucun code fonctionnel.
Dans notre exemple, les api JEE7 sont imposé par le POM parent, les packages (business-entities, rest-api, services) contienent notre code métier.

l'application web est généré par wildfly-package.





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
completer le package spring avec les bonne libs(jpa, cdi, jax-rs, ejb)



