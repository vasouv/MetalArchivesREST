# MetalArchivesREST

_Simple JAX-RS endpoint and client project. Uses the Client API to get the response from a REST endpoint, parses the response with JSON-P and outputs on an endpoint. Ideal for testing other techs, let's say a frontend in HTML + JS or another microservice than performs further processing._

While searching for REST endpoints to use for testing, I came across the [RESTful API for Encyclopaedia Metallum](http://em.wemakesites.net/#/overview). It's an unofficial API but it's ideal for testing purposes, it returns a single Album, a single Band with all their info etc. So when I want to work with an online store for example, I have the data ready to be used. And if I process them myself, I keep only the useful parts.

## API key
First, you must obtain an API key to use it as a Query Parameter. The API key must be placed in the **key.properties** file to be used in the program.

## Deployment
Being a Java EE 7 Maven web application, it runs anywhere. I find it easier to run on a full Payara server (for dev) and on Payara Micro when I actually want to use it.

For Payara Micro, do `java -jar micro.jar --deploy metalrest.war --port 8384` and then you can use any client to request.

## Endpoints and responses
If you deploy on Payara Micro, the endpoints are shown in the console. Currently these two are ready:
```
.../rest/resource/upcoming
.../rest/resource/album/{id}
```
### Upcoming return
```
[
  {
    "albumID": "645022",
    "albumTitle": "The Plague",
    "bandID": "3540394298",
    "bandName": "Mordenial",
    "releaseDate": "July 10th 2017"
  },
  {
    "albumID": "656183",
    "albumTitle": "Regressus ad...",
    "bandID": "56638",
    "bandName": "Regressus ad Infinitum",
    "releaseDate": "July 10th 2017"
  },
  {
    "albumID": "657802",
    "albumTitle": "Vampire Rising",
    "bandID": "101443",
    "bandName": "Hard Venom",
    "releaseDate": "July 10th 2017"
  }
]
```
### Album return
```
{
  "albumID": "23",
  "albumTitle": "Haven",
  "bandID": "8",
  "bandName": "Dark Tranquillity",
  "releaseDate": "July 17th, 2000"
}
```
