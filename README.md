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
- Keys and access tokens: create **your own application** at <https://apps.twitter.com/> and get **your own** consumer key, consumer secret, access token and access token secret

## Tweet/User crawlers

### Commands to run tweet/user crawlers:
- Options:
	* -tf: Token file name, necessary if -ck, -cs, -at or -as is not given
	* -ck: Consumer key, necessary if -tf is not given
	* -cs: Consumer secret, necessary if -tf is not given
	* -at: Access token, necessary if -tf is not given
	* -as: Access token secret, necessary if -tf is not given
	* -d: Input and output are directories, default false
	* -i: Input file/directory name, necessary
	* -o: Output file/directory name, necessary
	* -p: #Requests per user when crawling his tweets, default 1, max 16
	* -t: #Tweets per request when crawling a user's tweets, default 200, max 200
	* -h: Show help information
- Token file format: The file should contain four lines as follows

		Consumer Key
		Consumer Secret
		Access Token
		Access Token Secret

- Command examples (assume under lib/ and Linux):
	* Grab tweets given a tweet ID file
		
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.TweetsByTweetID -tf ../data/keys.txt -i ../data/Tweet/input/id1.txt -o ../data/Tweet/output/tweet1.txt

	* Grab tweets given a tweet ID directory
	
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.TweetsByTweetID -tf ../data/keys.txt -d -i ../data/Tweet/input/ -o ../data/Tweet/output/
	
	* Grab tweets given a user ID file, 2 requests per ID and 100 tweets per request
		
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.TweetsByUserID -tf ../data/keys.txt -i ../data/User/input/id1.txt -o ../data/Tweet/output/tweet1.txt -p 2 -t 100

	* Grab tweets given a user ID directory, default number of requests per ID and defaualt number of tweets per request
	
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.TweetsByUserID -tf ../data/keys.txt -d -i ../data/User/input/ -o ../data/Tweet/output/
	
	* Grab [screen name | follower IDs | friend IDs] given a user ID file
	
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.[ScreenName|Followers|Friends] -tf ../data/keys.txt -i ../data/User/input/id1.txt -o ../data/User/output/[name1|followers1|friends1].txt
	
	* Grab [screen name | follower IDs | friend IDs] given a user ID directory
	
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.[ScreenName|Followers|Friends] -tf ../data/keys.txt -d -i ../data/User/input/ -o ../data/User/output/

### Input format:
- The input could either be a single file or a directory with input files
- Tweets given tweet IDs: Each line contains a tweet ID
- Tweets/User screen names/follower IDs/friend IDs given user IDs: Each line contains a user ID

### Output format:
- Tweets: Each line contains a tweet in JSON format
- User screen names: Each line contains a user's ID and his screen name, separated by tab
- Follower IDs/friend IDs: Each line contains a user's ID and one of his followers'/friends' ID, separated by tab

## Rate limit crawler

### Commands to run rate limit crawler:
- Options:
	* -tf: Token file name, necessary if -ck, -cs, -at or -as is not given
	* -ck: Consumer key, necessary if -tf is not given
	* -cs: Consumer secret, necessary if -tf is not given
	* -at: Access token, necessary if -tf is not given
	* -as: Access token secret, necessary if -tf is not given
	* -l: API name, full list at <https://dev.twitter.com/rest/public>
	* -h: Show help information
- Token file format: The file should contain four lines as follows

		Consumer Key
		Consumer Secret
		Access Token
		Access Token Secret

- Command examples (assume under lib/ and Linux):
	* Get rate limits of all APIs
		
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.RateLimit -tf ../data/keys.txt

	* Get rate limit of an API named /geo/search
	
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar crawlers.RateLimit -tf ../data/keys.txt -l /geo/search

## Extract text from JSON-format tweet file

### Commands to run text extractor:
- Options:
	* -d: Input and output are directories, default false
	* -i: Input file/directory name, necessary
	* -o: Output file/directory name, necessary
	* -h: Show help information

- Command examples (assume under lib/ and Linux):
	* Extract text from a JSON-format tweet file
		
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar tweet.TextExtractor -i ../data/TweetJson/input/tweets1.txt -o ../data/Tweet/output/text1.txt
			
	* Extract text from a directory with JSON-format tweet files
	
			java -cp TwitterCrawler.jar:twitter4j-core-4.0.4.jar tweet.TextExtractor -d -i ../data/TweetJson/input/ -o ../data/Tweet/output/

### Input format:
- The input could either be a single file or a directory with input files
- Each line contains a JSON-format tweet

### Output format:
- Each line contains a tweet's ID and text, separated by tab
