## Jokes Service
This repository stores jokes by source and category using a restful api. The main 
feature is to provide a random joke.  Adding parameters for source and category
will limit the joke returned.

### JSON Details
```
Joke:
{
  "joke_id": "1", 
  "source": "Facebook",
  "category": "DADJOKES,
  "joke": "Something Funny"
}
```
```
Category - Not implemented yet
{
  "short_name": "DADJOKES"
  "display_name": "Jokes your father might have told"
}
```
### API Details
#### POST new joke - http:host:port/
NOTE: Doesn't check for existing joke, so duplicates are possible.
#### REQUEST Payload
```
{
	"category": "DADJOKES",
	"source": "Postman",
	"joke": "What do you call a bear with no teeth? A gummy bear!"
}
```
#### RESPONSE PAYLOAD
```
{
    "joke_id": 33
	"category": "DADJOKES",
	"source": "https://www.rd.com/jokes/dad/",
	"joke": "Q. Did you hear about the cheese factory that exploded in France?\nA. There was nothing left but de Brie."
}
```
#### GET random joke - http://host:port/[?category=??? | source=???]
Category and source are optional.  Both can be included to narrow field of jokes to none, one, or both Request Parameters
#### GET joke by id - http://host:port/id
Example http://3.215.21.188:8080/1848
#### Response Payload
````
{
    "jokeId": 1848,
    "source": "https://www.rd.com/jokes/dad/",
    "category": "DADJOKES",
    "joke": "Q. Did you hear about the cheese factory that exploded in France?\nA. There was nothing left but de Brie."
}
````

#### GET all categories - http://host:port/category
