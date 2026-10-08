# Whole Mart

Java 17 / Spring Boot multi-module backend. The root Maven project aggregates the service modules; `whole-mart-platform` is the runnable application.

## Current implementation

- `whole-mart-common`: shared exceptions, API response types, constants, authenticated-user access, and utilities.
- `whole-mart-identity`: user and role persistence, registration, password login, OTP verification, and bearer-token authentication.
- `whole-mart-merchant`: authenticated merchant profile creation and updates, profile lookup, and paged search for active wholesalers/shopkeepers.
- Catalog, cart, order, payment, ledger, delivery, and notification are present as Maven modules and follow the implementation roadmap; their business behavior has not been implemented yet.

## Run locally

Install Maven 3.9+ and Java 17+, then from this directory run:

```bash
mvn -pl whole-mart-platform -am spring-boot:run
```

The local profile uses an in-memory H2 database. Set `WHOLEMART_JWT_SECRET` to a random secret of at least 32 bytes outside local development. The OTP endpoint currently logs OTPs for local development; connect an SMS provider before using it with real accounts. Use a persistent database and migrations before production.

## Identity API

- `POST /api/auth/register` — `{ "phone": "9876543210", "password": "example-pass", "fullName": "A User" }`
- `POST /api/auth/login` — phone and password; returns a one-hour bearer token.
- `POST /api/auth/send-otp` — `{ "phone": "9876543210" }`
- `POST /api/auth/verify-otp` — phone and six-digit code; returns a bearer token.
- `GET /api/users/me` — requires `Authorization: Bearer <token>`.

## Merchant API

All merchant endpoints require a bearer token. Create a profile with `POST /api/merchants` using `businessName`, `phone`, `merchantType` (`WHOLESALER` or `SHOPKEEPER`), and optional `gstin`. The authenticated user is assigned as the owner. `GET /api/merchants/{id}` reads a profile, `GET /api/merchants/search?name=...&type=WHOLESALER&page=0&size=20` searches active profiles, and `PUT /api/merchants/{id}` updates the caller's own profile.
