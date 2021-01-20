
<h1>
<center>Spring Boot 🍂</center>
</h1>


[comment]: <> (-------------------------------------------------------------------)

[comment]: <> (<details>)

[comment]: <> (<summary><b>Simple Spring Boot REST-full API</b></summary>)

[comment]: <> (</details>)

[comment]: <> (-------------------------------------------------------------------)


[comment]: <> (Configurations)
<details>
<summary><b>Configurations</b></summary>

1. To change the port number of the server from 8080(default) to 8081 or any other port, 
    * got to `Edit Configurations` on top right.
    * click on `Modify Options`.
    * click on `Change VM Options`.
    * Add the following lines on the appeared text-box:
      
          -Dserver.port=8081

</details>

[comment]: <> (Annotations)
<details>
<summary><b>Annotations</b></summary>

Annotations | Description | 
--- | --- | 
@Repository | Marked on a Repository file in DAO/DAL.
@Service | Marked on a Service file.
@Autowired | Marked on a constructor with dependency injection.
@RestController | Marked on a Class with API's in it.
@RequestMapping(method = RequestMethod.GET, path = "get") | Marked on a `GET` method, and can be accessed on path `localhost:8080/get`.
@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) | Only Accepts JSON data from Post request.
@GetMapping | Marked on a `GET` method.
@PostMapping | Marked on a `POST` method.
@DeleteMapping | Marked on a `DELETE` method.
@PathVariable | Marking a property as a path variable.
@RequestBody | Added in front of the `User` in argument list. This indicates to map the incoming data from post request to this `User` model


</details>

[comment]: <> (Simple Spring Boot REST-full API)
<details>
<summary><b>Simple Spring Boot REST-full API</b></summary>

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

* To ensure the return of the JSON payload, make sure to add a getter for the message in the `Message` class in the above example.
* When accessed on `localhost:8080`, a JSON is returned:

        {
            "message": "Hello World!"
        }

</details>

[comment]: <> (Path Variables)
<details>
<summary><b>Adding Path Variable on an API</b></summary>

* The following example shows how to add a path variable in a Request handler.

      @RequestMapping(method = RequestMethod.GET, path = "{userUid}")
          public User fetchUser(@PathVariable("userUid") UUID userUid) {
          return userService.getUser(userUid).get();
      }


</details>

[comment]: <> (ResponseEntity and HTTP Response Code)
<details>
<summary><b>ResponseEntity and HTTP Response Code</b></summary>

* `ResponseEntity` is a generic class in spring boot which is used to handle a request. It returns a Response code of the given data.
* `Optional` is a generic class which returns null, if the object is not assigned, else it returns the object.  
* An example given below shows the use of Response Entity used to handle a request:

      @RequestMapping(method = RequestMethod.GET, path = "{userUid}")
      public ResponseEntity<?> fetchUser2(@PathVariable("userUid") UUID userUid) {
          Optional<User> optionalUser = userService.getUser(userUid);
          if (optionalUser.isPresent()) {
              return ResponseEntity.ok(optionalUser.get());
          }
          return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(new ErrorMessage("User " + userUid + "was not found!"));
      }

* We cannot also update the above code into functional program (Streams style), But that option is not available on 
  IntelliJ Community. 

</details>

[comment]: <> (Sending the post request from postman)
<details>
<summary><b>Sending the POST request from Postman</b></summary>

* Before sending the POST request make sure that, the `User` model has a default constructor.
* Following method is used for handling the POST Request:

      // POST: Saving a user in the database.
      @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
          int result = userService.insertUser(user);
          if (result == 1) {
              return ResponseEntity.ok().build();
          }
          return ResponseEntity.badRequest().build();
      }


</details>




