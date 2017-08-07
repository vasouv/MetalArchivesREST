# MetalArchivesREST

_Simple JAX-RS endpoint and client project. Uses the Client API to get the response from a REST endpoint, parses the response with JSON-P and outputs on an endpoint. Ideal for testing other techs, let's say a frontend in HTML + JS or another microservice than performs further processing._

While searching for REST endpoints to use for testing, I came across the [RESTful API for Encyclopaedia Metallum](http://em.wemakesites.net/#/overview). It's an unofficial API but it's ideal for testing purposes, it returns a single Album, a single Band with all their info etc. So when I want to work with an online store for example, I have the data ready to be used. And if I process them myself, I keep only the useful parts.

## API key
First, you must obtain an API key to use it as a Query Parameter. The API key must be placed in the **key.properties** file to be used in the program.

## Deployment
Being a Java EE 7 Maven web application, it runs anywhere. I find it easier to run on a full Payara server (for dev) and on Payara Micro when I actually want to use it.

For Payara Micro, do `java -jar <payara micro filename>.jar --deploy <project filename>.war --port 8384` and then you can use any client to request.
