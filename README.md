# cohere4j

[![build](https://github.com/llmjava/cohere4j/actions/workflows/main.yml/badge.svg)](https://github.com/llmjava/cohere4j/actions/workflows/main.yml) [![Jitpack](https://jitpack.io/v/llmjava/cohere4j.svg)](https://jitpack.io/#llmjava/cohere4j) [![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

</b>

This is an unofficial Java client library that you can use to interact with **Cohere API**. It can be used in Android or any Java and Kotlin Project.

## Add Dependency

### Gradle

To use library in your gradle project follow the steps below:

1. Add this in your root `build.gradle` at the end of repositories:
    ```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency
   ```groovy
   dependencies {
       def COHERE4J_VERSION = "..."
       implementation "com.github.llmjava:cohere4j:$COHERE4J_VERSION"
   }
   ```

### Maven

To use the library in your Maven project, follow the steps below:

1. Add the JitPack repository to your build file:
    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```
2. Add the dependency
    ```xml
    <dependency>
        <groupId>com.github.llmjava</groupId>
        <artifactId>cohere4j</artifactId>
        <version>${COHERE4J_VERSION}</version>
    </dependency>
    ```

## Usage

Example code to use the **Cohere API**:

Create a configuration file
```properties
# Set API key using env variable or put actual value
cohere.apiKey=${env:COHERE_API_KEY}
```

Create an instance of `CohereClient` and submit text generation requests

```java
import com.github.llmjava.cohere4j.*;

public class Main {
   public static void main(String[] args) {
       CohereConfig config = CohereConfig.fromProperties("cohere.properties");
       CohereClient client = new CohereClient.Builder().withConfig(config).build();

       String text = "tell me a joke";
       GenerateRequest request = new GenerateRequest.Builder()
               .withPrompt(text)
               .withConfig(config)
               .build();

       System.out.println(client.generate(request).getTexts().get(0));
   }
}
```

Find more usage examples under [examples folder](https://github.com/llmjava/cohere4j/tree/main/src/test/java/com/github/llmjava/cohere4j).

### Text generation
#### Synchronously
Customizable way

```
GenerateRequest request = new GenerateRequest.Builder()
    .withPrompt(text)
    .withConfig(config)
    .build();

GenerateResponse response = client.generate(request);
```

#### Asynchronously
Customizable way

```
GenerateRequest request = new GenerateRequest.Builder()
    .withPrompt(text)
    .withConfig(config)
    .build();

client.generateAsync(request, new AsyncCallback<GenerateResponse>() {
    @Override public void onSuccess(GenerateResponse response) {
    }
    @Override public void onFailure(Throwable throwable) {
    }
});
```

#### Streaming
Customizable way
```
GenerateRequest request = new GenerateRequest.Builder()
     .withPrompt(text)
     .withConfig(config)
     .withStream(true) // set the request to streaming
     .build();

client.generateStream(request, new StreamingCallback<StreamGenerateResponse>() {
      @Override public void onPart(StreamGenerateResponse response) {
      }
      @Override public void onComplete(StreamGenerateResponse response) {
      }
      @Override public void onFailure(Throwable throwable) {
      }
});
```

### Embeddings
Create an embedding request
```
EmbedRequest request = new EmbedRequest.Builder()
    .withText(text)
    .build();
```
#### Synchronously
Customizable way

```
EmbedResponse response = client.embed(request);
```

#### Asynchronously
Customizable way

```
client.embedAsync(request, new AsyncCallback<EmbedResponse>() {
    @Override public void onSuccess(EmbedResponse response) {
    }
    @Override public void onFailure(Throwable throwable) {
    }
});
```


### Classification
Create a classification request
```
ClassifyRequest request = new ClassifyRequest.Builder()
    .withExample("Dermatologists don't like her!", "Spam")
    .withExample("Hello, open to this?", "Spam")
    .withExample("I need help please wire me $1000 right now", "Spam")
    .withExample("Nice to know you ;)", "Spam")
    .withExample("Please help me?", "Spam")
    .withExample("Your parcel will be delivered today", "Not spam")
    .withExample("Review changes to our Terms and Conditions", "Not spam")
    .withExample("Weekly sync notes", "Not spam")
    .withExample("Re: Follow up from today’s meeting", "Not spam")
    .withExample("Pre-read for tomorrow", "Not spam")
    .withInput("Confirm your email address")
    .withInput("hey i need u to send some $")
    .withTruncate("END")
    .build();
```

#### Synchronously
Customizable way

```
ClassifyResponse response = client.classify(request);
```

#### Asynchronously
Customizable way

```
client.classifyAsync(request, new AsyncCallback<ClassifyResponse>() {
    @Override public void onSuccess(ClassifyResponse response) {
    }
    @Override public void onFailure(Throwable throwable) {
    }
});
```



### Tokenization
Create a tokenization request
```
TokenizeRequest request = new TokenizeRequest.Builder()
    .withText("tokenize me! :D")
    .withModel("command")
    .build();
```

#### Synchronously
Customizable way

```
TokenizeResponse response = client.tokenize(request);
```

#### Asynchronously
Customizable way

```
client.tokenizeAsync(request, new AsyncCallback<TokenizeResponse>() {
    @Override public void onSuccess(TokenizeResponse response) {
    }
    @Override public void onFailure(Throwable throwable) {
    }
});
```

## Build Project

Clone the repository and import as Maven project in IntelliJ IDEA or Eclipse

Before building the project, make sure you have the following things installed.

- Maven
- Java 8

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To build the library using Gradle, execute the following command

```shell
./gradlew build
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.