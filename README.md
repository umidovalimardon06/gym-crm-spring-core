## Development History

The feature was developed incrementally via pull requests:

| PR | Branch | Description |
|---|---|---|
| #1 | `feature/domain-objects` | Domain model: User, Trainee, Trainer, Training |
| #2–3 | `feature/storage-layer` | Storage beans, BeanPostProcessor, CSV seed data |
| #4 | `feature/utility-classes` | UsernameGenerator, PasswordGenerator |
| #5 | `feature/dao-layer` | DAO interfaces and Map-backed implementations |
| #6 | `feature/service-layer` | Service layer with business logic |
| #7 | `feature/facade` | GymFacade with constructor injection |
| #8 | `feature/logging` | SLF4J logging across all layers |
| #10 | `feature/unique-username-generation` | Cross-pool username deduplication |
| #11 | `test/unit-tests` | Unit tests for DAO, service, facade, utilities |
| #12 | `bug-fix` | Fix bugs across all layers, add logging/tests |