Send POST Request with header `application/json` and following body

```
{
	"type": "invoice",
	"language": "nl",
	"file": "test.pdf",
	"specifiers": [
		"be",
		"124"
	],
	"data": {
       "company": "acme",
       "date": "04/05/2018",
       "products": [{
           "name": "Coca Cola",
           "price": "1.80"
       },{
           "name": "Sprite",
           "price": "1.90"
       }]
	}
}
```