# whole-mart-common

Shared technical types for the Whole Mart modules. Keep domain rules and module-specific values in their owning module; put cross-module values and shared API text here.

## Constants

- `CommonConstants`, `ApiPathConstants`, and `SecurityConstants` hold shared API and authentication configuration values.
- `ErrorCodeConstants` and `MessageConstants` are the source for API error codes, error text, and shared response messages.
- `ValidationConstants` contains reusable validation expressions, messages, and field limits.
- `BusinessConstants` contains shared policy values such as OTP/token lifetimes, pagination defaults, and currency rounding.
- `RoleConstants` contains role names used across modules.

Use these constants in validation annotations, exception handling, services, and controllers instead of duplicating literal values.
