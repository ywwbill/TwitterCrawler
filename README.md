# Twitter Crawlers
Code for downloading tweets and Twitter user information. Feel free to email me at wwyang@cs.umd.edu with any questions.

### What can be downloaded:
- Tweets given tweet IDs
- Tweets given user IDs (max 200 tweets per request, max 16 request per ID)
- User screen names given user IDs
- Follower IDs given user IDs (max 5000 followers per ID)
- Friends IDs given user IDs (max 5000 friends per ID)
- Current rate limit of your application

### Dependencies: 
- Java 1.8
- Twitter4J.jar <http://twitter4j.org/en/index.html>
- Keys and access tokens: create **your own application** at <https://apps.twitter.com/> and save your keys and access tokens in a text file (data/keys.txt by default) with four lines

		Consumer Key
		Consumer Secret
		Access Token
		Access Token Secret

### Commands to run:
- See main() in crawlers/*Crawler.java to run different crawlers
	* TweetCrawler.java: Download tweets given tweet/user IDs
	* UserCrawler.java: Download user screen names/follower IDs/friend IDs given user IDs
	* RateLimitCrawler.java: Get the rate limit of your application

### Input format (for your own data!):
- The input could either be a single file or a directory with input files
	* If the input is a single file, you should specify the output file
	* If the input is a directory with input files, you should specify the output directory and output file names are the same with input file names
- Tweets given tweet IDs: Each line contains a tweet ID
- Tweets given user IDs: Each line contains a user ID, also needs to specify number of requests (max 16) per ID and number of tweets per request (max 200)
- User screen names/follower IDs/friend IDs given user IDs: Each line contains a user ID

### Output format:
- Tweets: Each line contains a tweet in JSON format
- User screen names: Each line contains a user's ID and his screen name, separated by tab
- Follower IDs/friend IDs: Each line contains a user's ID and one of his followers'/friends' ID, separated by tab

### Other notes:
- Code example for extracting information from a JSON-format tweet is given in tweet.TweetFromJson.java