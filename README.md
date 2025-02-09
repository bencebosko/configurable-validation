## Validation
This project contains custom bean validations for validating Java objects.

## Examples
Here is an example for validating a _Patch_ request in a REST controller. Since we might update only specific fields, the validations should allow null values:
```
public class UserPatchRequest {

    @Username(nullable = true)
    private String username;
    
    @Email(nullable = true)
    private String email;
    
    @StrongPassword(nullable = true)
    private String password;

    ...
}

@PatchMapping("/api/users/{userId}")
public ResponseEntity<UserResponse> patchUser(@PathVariable("userId") Long userId, @RequestBody @Validated UserPatchRequest userPatchRequest) {
   ...
}
```
