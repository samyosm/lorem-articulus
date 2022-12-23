# Overview

Lorem Articulus will be a restful API that will allow you to generate texts from a few hints using GPT-3. For example, you might provide the API with a title, a few tags, and a short outline , the API will use those hints to generate an article using OpenAI.

# Get started

Below is a short guide to using this API.

## 1. Sign In

To use this API, you will first need to get a token by signing up at TODO.

## 2. Send a post request

Base Url: **/api/v1**

| Endpoint | Description       | Method |
|----------|-------------------|--------|
| /twitter | A twitter post    | POST   |
| /youtube | A youtube comment | POST   |
| /article | A short article   | POST   |

### POST /twitter

Description:
This endpoint allows users to generate a tweet based on a given set of topics and tone.

#### Request Parameters:

Body:
- topics (required): A list of topics (in strings) to be included in the tweet
- tone (required): The tone of the tweet (e.g. commenting, praising, etc.)
- description (optional): A brief description of the desired tweet

Header:
- bearer token (required): Your API token

Request Body Example:
```json
{
  "topics": [
    "AI",
    "Text generating AIs",
    "Dangers"
  ],
  "tone": "commenting",
  "description": "A tweet someone affraid of AIs would tweet"
}
```

Response Example:

`I'm so scared of the potential dangers of text generating AIs. We need to be careful with how we use AI technology or else it could have serious consequences. #AIDangers #TextGeneratingAIs`

### POST /youtube

Description:
This endpoint is used for creating comments about YouTube videos.

#### Request Parameters:

Body:
- videoTitle (required): The title of the YouTube video.
- commentTone (required): The tone of the comment. Options include Appreciative, Critical, or Neutral.
- author (required): The author of the YouTube video.
- commentDescription (required): The description of the comment.

Header:
- bearer token (required): Your API token

Request Body Example:
```json
{
  "videoTitle": "Brief History of the Royal Family",
  "commentTone": "Appreciative",
  "author": "CGP Grey",
  "commentDescription": "A comment that would show appreciation toward the author for the humorous video."
}
```

Response Example:

`This was such a fun and informative video! CGP Grey always does an amazing job of making history entertaining and I really appreciate it. Thanks for the great video!`

### POST /article

Description:
This endpoint allows users to create a short article.

#### Request Parameters:

Body:
- title (required): The title of the article.
- description (required): A short description of the article.
- tags (required): An array of tags related to the article.

Header:
- bearer token (required): Your API token

Request Body Example:
```json
{
  "title": "How to memorize more easily the content of your textbooks.",
  "description": "An article targating students. It will give tips about how to better memorize what they read in their books.",
  "tags": [
    "students",
    "reading",
    "textbooks",
    "memorization"
  ]
}
```

Response Example:

```text
How to Memorize More Easily the Content of Your Textbooks

As a student, you know that textbooks are a crucial part of your education. They contain a wealth of information that you need to learn and remember in order to do well in your classes. But memorizing the content of textbooks can be a daunting task. It can be difficult to keep track of all the facts and figures, and it can be hard to remember what you read. Fortunately, there are some strategies you can use to make memorizing the content of your textbooks easier.

1. Read Actively

The first step to memorizing the content of your textbooks is to read actively. This means that you should be actively engaging with the material as you read. Ask yourself questions about the material, make connections between different concepts, and take notes as you read. This will help you to better understand and remember the material.

2. Break Up Your Reading

Another strategy for memorizing the content of your textbooks is to break up your reading. Instead of trying to read the entire textbook in one sitting, break it up into smaller chunks. This will make it easier to focus on the material and will help you to better remember what you read.

3. Use Mnemonic Devices

Mnemonic devices are a great way to help you remember the content of your textbooks. Mnemonic devices are memory aids that use associations and acronyms to help you remember information. For example, you could use the acronym “ROY G BIV” to remember the colors of the rainbow (red, orange, yellow, green, blue, indigo, and violet).

4. Create Visual Aids

Creating visual aids is another great way to help you remember the content of your textbooks. You can create diagrams, charts, and other visuals to help you better understand and remember the material. This is especially helpful for visual learners.

5. Quiz Yourself

Quizzing yourself is a great way to test your knowledge and help you remember the content of your textbooks. After you’ve read a section, try to answer some questions about the material. This will help you to better remember what you read.

6. Teach Someone Else

Teaching someone else is another great way to help you remember the content of your textbooks. When you teach someone else, you have to explain the material in your own words, which can help you to better understand and remember it.

7. Take Breaks

Taking breaks is an important part of memorizing the content of your textbooks. When you take breaks, your brain has time to process the information you’ve read and store it in your long-term memory. So make sure to take breaks while you’re studying.

These are just a few strategies you can use to help you memorize the content of your textbooks. By using these strategies, you can make memorizing the content of your textbooks easier and more effective. So the next time you’re studying, try out some of these strategies and see how they work for you. Good luck!
```

# Soon

*If you have any idea, please create an issue*
- Long article (paragraph per paragraph)
- Twitter comment
- Reddit comment