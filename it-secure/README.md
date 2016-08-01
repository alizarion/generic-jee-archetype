# Security layer for jee webapps

# author
Bensenouci selim(sbn)

# pré-requis:

Maven 3.1 ou supérieur.
JDK 1.7 ou supérieur.

# On the server side 
## 1 Protect your EndPoints : 
Use this package to secure acces to your endpoints using JAAS annotations

@PermitAll : authorize acces to the endpoint for all authenticated users
@DenyAll :  acces deny for all users.
@RolesAllowed({"admin","users"}) : acces granted for all users that have one of this roles

you can use this annotations on jax-rs methods :    
                  @RolesAllowed("admin")
                  @Path("/")
                  @GET
                  public Response test(){
                    return Response.ok("{\"test\":\"you have the admin role\"}").build();
                  }    

or on class level and all methods will get the same acces level (you can override this by adding annother annotation on the methods) :

              @Path("/test")
              @RolesAllowed("admin")
              public class TestEndPoint {
              
                @GET
                public Response test()  { 
                   return Response.ok("{\"test\":\"you have the admin role\"}").build();
                }
              }

## Write you login module
