# kraken
![Jenkins](https://img.shields.io/jenkins/build/http/trevorism-build.eastus.cloudapp.azure.com/kraken)
![Jenkins Coverage](https://img.shields.io/jenkins/coverage/jacoco/http/trevorism-build.eastus.cloudapp.azure.com/kraken)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/kraken)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/kraken)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/kraken)


Java client wrapping [kraken.com](https://www.kraken.com/en-us/features/api)

Current version: 0.6.0

## How to Use 
Kraken has a public and private API. This code uses a wrapper client which delegates to a 
`DefaultPublicKrakenClient` and `DefaultPrivateKrakenClient`. Here's how to create it:

```java
String apiKey = "...";
String apiSecret = "...";
KrakenClient krakenClient = new DefaultKrakenClient(new DefaultPrivateKrakenClient(apiKey, apiSecret));
```

## More reading
These links from Kraken helped me implement this:

https://support.kraken.com/hc/en-us/articles/360000919966-How-to-generate-an-API-key-pair-

https://www.kraken.com/features/api

https://support.kraken.com/hc/en-us/articles/360001491786-API-Error-Codes


## How to Build
`gradle clean build`