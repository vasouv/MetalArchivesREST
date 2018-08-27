_Sadly the host em.wemakesites.net is unreachable and therefore this project does not work anymore_

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
.../rest/resource/band/{id}
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
  "coverURL": "https://www.metal-archives.com/images/2/3/23.jpg",
  "releaseDate": "July 17th, 2000",
  "trackList": [
    {
      "length": "03:01",
      "no": 1,
      "title": "The Wonders at Your Feet"
    },
    {
      "length": "03:38",
      "no": 2,
      "title": "Not Built to Last"
    },
    {
      "length": "03:35",
      "no": 3,
      "title": "Indifferent Suns"
    },
    {
      "length": "03:25",
      "no": 4,
      "title": "Feast of Burden"
    },
    {
      "length": "03:32",
      "no": 5,
      "title": "Haven"
    },
    {
      "length": "03:10",
      "no": 6,
      "title": "The Same"
    },
    {
      "length": "03:56",
      "no": 7,
      "title": "Fabric"
    },
    {
      "length": "04:34",
      "no": 8,
      "title": "Ego Drama"
    },
    {
      "length": "03:53",
      "no": 9,
      "title": "Rundown"
    },
    {
      "length": "03:39",
      "no": 10,
      "title": "Emptier Still"
    },
    {
      "length": "06:42",
      "no": 11,
      "title": "At Loss for Words"
    }
  ]
}
```
### Band return
```
{
  "albums": [
    {
      "albumID": "5958",
      "albumTitle": "Demo 1989",
      "releaseDate": "1989"
    },
    {
      "albumID": "4189",
      "albumTitle": "Into the Depths of Sorrow",
      "releaseDate": "1991"
    },
    {
      "albumID": "437000",
      "albumTitle": "Beyond the Crimson Horizon",
      "releaseDate": "1992"
    },
    {
      "albumID": "130220",
      "albumTitle": "Promo",
      "releaseDate": "1993"
    },
    {
      "albumID": "4074",
      "albumTitle": "Through the Darkest Hour",
      "releaseDate": "1994"
    },
    {
      "albumID": "11083",
      "albumTitle": "Days of Doom",
      "releaseDate": "1994"
    },
    {
      "albumID": "40730",
      "albumTitle": "The New Wave of American True Metal",
      "releaseDate": "1996"
    },
    {
      "albumID": "4190",
      "albumTitle": "Downfall",
      "releaseDate": "1996"
    },
    {
      "albumID": "4191",
      "albumTitle": "Adagio",
      "releaseDate": "1998"
    },
    {
      "albumID": "476272",
      "albumTitle": "Justice for All",
      "releaseDate": "2000"
    },
    {
      "albumID": "131453",
      "albumTitle": "Alone",
      "releaseDate": "2006"
    },
    {
      "albumID": "153532",
      "albumTitle": "Hour of Despair",
      "releaseDate": "2007"
    },
    {
      "albumID": "227152",
      "albumTitle": "Hour of Despair",
      "releaseDate": "2009"
    },
    {
      "albumID": "310692",
      "albumTitle": "In Times of Solitude",
      "releaseDate": "2011"
    }
  ],
  "bio": "blahblahblahblah huge text",
  "genre": "Epic Doom Metal",
  "id": "256",
  "name": "Solitude Aeturnus",
  "photo": "https://www.metal-archives.com/images/2/5/6/256_photo.jpg?1722"
}
```
## TODO
- [ ] switch to Wildfly Swarm and run as jar
- [X] findBand() endpoint
- [ ] search endpoint
- [X] add album cover url to Album
- [ ] MicroProfile (switch maybe, if I don't use other APIs)
- [X] use Response to demonstrate usage
- [ ] REFACTORING - extract code to methods
