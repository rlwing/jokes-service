This repository stores jokes by source and category using a restful api. The main 
feature is to provide a random joke.  Adding parameters for source and category
will limit the joke returned.

JSON Details

Joke:
{
  "joke_id": "1", 
  "source": "Facebook",
  "category": "DADJOKES,
  "joke": "Something Funny"
}

Category - Not implemented yet
{
  "short_name": "DADJOKES"
  "display_name": "Jokes your father might have told"
}

API Details
POST http:host:port/
REQUEST Payload
{
	"category": "DADJOKES",
	"source": "Postman",
	"joke": "What do you call a bear with no teeth? A gummy bear!"
}
RESPONSE PAYLOAD
