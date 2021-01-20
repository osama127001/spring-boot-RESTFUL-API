
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

[comment]: <> (Sending the PUT request from Postman)
<details>
<summary><b>Sending the PUT request from Postman</b></summary>

* Following method/middleware is used to handle a PUT request:

      /*
          Returns: Response of the request
          Used In: PUT, POST
      */
      private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
          if (result == 1) {
              return ResponseEntity.ok().build();
          }
          return ResponseEntity.badRequest().build();
      }
    
    
      // PUT: Updates the user object.
      @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Integer> updateUser(@RequestBody User user) {
          int result = userService.updateUser(user);
          return getIntegerResponseEntity(result);
      }


</details>

[comment]: <> (Sending the DELETE request from Postman)
<details>
<summary><b>Sending the DELETE request from Postman</b></summary>

* The following method handles a delete request from Postman:

      //DELETE: Delete a user using userUid
      @RequestMapping(method = RequestMethod.DELETE, path = "{userUid}")
      public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid) {
          int result = userService.removeUser(userUid);
          return getIntegerResponseEntity(result);
      }


</details>

[comment]: <> (Query Params)
<details>
<summary><b>Query Params</b></summary>

* Adding query params basically passes some variables through the URL in order to perform filtering.
* To add a query parameter, use `?` and add the parameters in the URL. An example is shown below:

      localhost:8081/api/v1/users/get?gender=MALE&ageLessThan=18

* The following middleware handles this URL and gets the value of `gender` and `ageLessThan`.

      // GET: All users from database.
      @RequestMapping(method = RequestMethod.GET, path = "get")
      public List<User> fetchUsers(@QueryParam("gender") String gender, @QueryParam("ageLessThan") Integer ageLessThan) {
          System.out.println(gender);
          System.out.println(ageLessThan);
          return userService.getAllUsers();
      }

* If the query params are not added, the middleware will still return data normally.
* Passing the Gender got by query-params inside a function in the `userService`:

      public List<User> getAllUsers(Optional<String> gender) {
          List<User> users = userDao.selectAllUsers();
          if (!gender.isPresent()) {
              return users;
          }
          try {
              Gender theGender = Gender.valueOf(gender.get().toUpperCase());
              return users.stream()
                  .filter(user -> user.getGender().equals(theGender))
                  .collect(Collectors.toList());
          } catch (Exception exp) {
                throw new IllegalStateException("Invalid Gender" + exp);
          }
      }

* Middleware that handles query-params:

      // GET: All users from database.
      @RequestMapping(method = RequestMethod.GET, path = "get")
          public List<User> fetchUsers(@QueryParam("gender") String gender) {
          return userService.getAllUsers(Optional.ofNullable(gender));
      }

* Now we can GET send request using the following URL:

      localhost:8081/api/v1/users/get?gender=female
  
  OR
  
      localhost:8081/api/v1/users/get?gender=MALE



</details>

[comment]: <> (Produces and Consumes)
<details>
<summary><b>Produces and Consumes</b></summary>


* Uptil now we have been handling requests using Semi-structured data, JSON or any other format.
* We can set the type of files that we can accept from clients, and the type of files that we cen send to clients.
  * `@Produces`: Server produces only selected file types to client.
  * `@Consumes`: Server accepts only selected file types from a client.

* In the following UserController Class, we are forcing each request handler to produce and consume selected type of file/data.
  
      @RestController
      @RequestMapping(path = "/api/v1/users")
      public class UserController {
      private UserService userService;
    
    
        /*
             CONSTRUCTOR: Injecting User service.
        */
        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }
    
    
        /*
             GET: All users from database.
        */
        @RequestMapping(
                method = RequestMethod.GET,
                path = "get",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public List<User> fetchUsers(@QueryParam("gender") String gender) {
            return userService.getAllUsers(Optional.ofNullable(gender));
        }
    
    
        /*
             Returns: Response of the request
             Used In: PUT, POST
        */
        private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
            if (result == 1) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
    
    
        /*
             GET: User by ID using path variable and ResponseEntity and ResponseCode.
        */
        @RequestMapping(
                method = RequestMethod.GET,
                path = "{userUid}",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid) {
            Optional<User> optionalUser = userService.getUser(userUid);
            if (optionalUser.isPresent()) {
                return ResponseEntity.ok(optionalUser.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessage("User " + userUid + "was not found!"));
        }
    
    
        /*
             POST: Saving a user in the database.
        */
        @RequestMapping(
                method = RequestMethod.POST,
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
            int result = userService.insertUser(user);
            return getIntegerResponseEntity(result);
        }
    
    
        /*
             PUT: Updates the user object.
        */
        @RequestMapping(
                method = RequestMethod.PUT,
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<Integer> updateUser(@RequestBody User user) {
            int result = userService.updateUser(user);
            return getIntegerResponseEntity(result);
        }
    
        /*
            DELETE: Delete a user using userUid
        */
        @RequestMapping(
                method = RequestMethod.DELETE,
                path = "{userUid}",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid) {
            int result = userService.removeUser(userUid);
            return getIntegerResponseEntity(result);
        }
    
    }

</details>
