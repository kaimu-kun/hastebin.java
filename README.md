# hastebin.java
A simple Hastebin API wrapper for Java. 

# Download:
Latest Stable Version: [GitHub Release](https://github.com/kaimu-kun/hastebin.java/releases/latest)

# Example:

```java 
Hastebin hastebin = new Hastebin();

// Defines the text which you want to post to hastebin
String text = "This is an example";

// Set this boolean to true to receive the url with the raw post
boolean raw = true;

try {
  String url = hastebin.post(text, raw);
  System.out.println(url);
} catch (IOException e) {
  e.printStackTrace();
}
```
