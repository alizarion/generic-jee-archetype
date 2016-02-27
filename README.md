# generic-jee-archetype
Simple projet, posposition de structure Maven permettant le multipackaging d'applications JEE



#pré-requis: 
Maven 3.1 ou supérieur.
JDK 1.7 ou supérieur 

#Build 
* Téléchargez le projet.
* Dans le répertoire racine, faites un :         
        ```mvn clean install```    

#Tester la version JEE sous wildfly : 
* Dans le répertoire wildfly-package, faites un    
        ```mvn wildfly:run -Dwildfly.version=8.2.0.Final````

#L'application 
une simple application permettant d'effectuer des opérations CRUD sur l'entité Person via des appels Rest.   

un client angular permettant de la tester est disponible à cette adresse : 
http://plnkr.co/edit/eHZ0dgTFmveJDIY71gUa?p=preview

#L'application utilise les standards suivants: 
* JPA(Java Persistence) JSR-338 
* JAX-RS 2.0 (Java API for RESTful Web Services) JSR 339
* CDI (Contexts and Dependency Injection) JSR 346
* EJB (Enterprise JavaBeans) JSR 220 



