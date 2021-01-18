# Spring Boot

[comment]: <> (<details>)

[comment]: <> (<summary>Simple Spring Boot REST-full API</summary>)

[comment]: <> (</details>)


<details>
<summary>Simple Spring Boot REST-full API</summary>

* Add the following code in the main application file for a simple get request.
  
      @SpringBootApplication
      public class LearningSpringBootApplication {
    
            public static void main(String[] args) {
                SpringApplication.run(LearningSpringBootApplication.class, args);
            }
    
            @RestController
            class MessageController {
    
                @RequestMapping(method = RequestMethod.GET)
                Message getMessage() {
                    return new Message("Hello World!");
                }
            }
    
            class Message {
  
                private final String message;
    
                public String getMessage() {
                    return message;
                }
    
                public Message(String message) {
                    this.message = message;
                }
            }
      }

* When accessed on `localhost:8080`, a JSON is returned:

        {
            "message": "Hello World!"
        }

</details>

