Scala documentation
=====================================

* Playframework : http://playframework.com/documentation/2.3.x/ScalaHome
* Play reactivemongo plugin : https://github.com/ReactiveMongo/Play-ReactiveMongo
* Reactivemongo : http://reactivemongo.org/
* Secure social : http://securesocial.ws/
* Play-Reactivemongo : http://stephane.godbillon.com/2012/10/18/writing-a-simple-app-with-reactivemongo-and-play-framework-pt-1.html (+ part 2, 3 and 4).

Configuration
=====================================

conf/securesocial-dev.conf
-------------------------------------
```
smtp {
	host=smtp.gmail.com
	#port=25
	ssl=true
	user="USER"
	password="PASSWORD"
	from="EMITER"
}
```

conf/reactivemongo-dev.conf
-------------------------------------
```
mongodb.uri ="mongodb://user:password@url:port/path"
```
