## Configurable Validations
This project contains **configurable** bean validations for **validating Java objects using annotations**. Validations have plenty of default options we can set manually. Currently supported validations:
- `@Email`
- `@NonNegative`
- `@NonNull`
- `@NotBlank`
- `@PersonName`
- `@StrongPassword`
- `@Username`

## Examples
Here is an example for validating a _Patch_ request in a REST controller. Since we might update only specific fields, the validations should allow `null` values.
```
public class UserPatchRequest {

    @Username(nullable = true, allowedSpecialChars = "._-")
    private String username;

    @Email(nullable = true)
    private String email;

    @StrongPassword(nullable = true, minLength = 10, needSpecialChar = true)
    private String password;

    @PersonName(nullable = true)
    private String firstName;

    ...
}

public ResponseEntity<UserResponse> patchUser(Long userId, @RequestBody @Validated UserPatchRequest userPatchRequest) {
   ...
}
```
