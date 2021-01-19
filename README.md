
<center>
<h1>🍂 Spring Boot 🍂</h1>
</center>
<hr>


[comment]: <> (<details>)

[comment]: <> (<summary>Simple Spring Boot REST-full API</summary>)

[comment]: <> (</details>)





[comment]: <> (Annotations)
<details>
<summary>Annotations</summary>

Annotations | Description | 
--- | --- | 
@Repository | Marked on a Repository file in DAO/DAL.
@Service | Marked on a Service file.
@Autowired | Marked on a constructor with dependency injection.
@RestController | Marked on a Class with API's in it.
@RequestMapping(method = RequestMethod.GET) | Marked on a `GET` method.
@RequestMapping(method = RequestMethod.GET, path = "get") | Marked on a `GET` method, and can be accessed on path `localhost:8080/path`.
@GetMapping | Marked on a `GET` method.
@PostMapping | Marked on a `POST` method.
@DeleteMapping | Marked on a `DELETE` method.
@PathVariable | Marking a property as a path variable.

</details>

[comment]: <> (Simple Spring Boot REST-full API)
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

[comment]: <> (Path Variables)
<details>
<summary>Adding Path Variable on an API</summary>

* The following example shows how to add a path variable in a Request handler.

      @RequestMapping(method = RequestMethod.GET, path = "{userUid}")
          public User fetchUser(@PathVariable("userUid") UUID userUid) {
          return userService.getUser(userUid).get();
      }


</details>


