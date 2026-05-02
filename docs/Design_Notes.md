# Design Notes

## Why ArrayList Was Used Instead of Array

`ArrayList` was used because the number of students, courses, and enrollments can grow while the application is running. A normal array has a fixed size, but an `ArrayList` resizes dynamically and provides useful methods such as `add`, `isEmpty`, and iteration support.

## Where Static Members Were Used

Static members were used in `IdGenerator`. ID counters belong to the application-level ID generation process, not to one specific student, course, or enrollment object.

## Where Inheritance Was Used

`Person` is the base class for `Student` and `Trainer`. Common fields such as `id`, `firstName`, `lastName`, and `email` are kept in `Person`. This reduces duplicate code and demonstrates inheritance.

## Polymorphism

`Student` and `Trainer` override `getDisplayName()` from `Person` so behavior depends on the actual object type.

## Service Layer

`StudentService`, `CourseService`, and `EnrollmentService` hold business logic. `Main` shows menus, reads input, and calls services.

## Exceptions

`EntityNotFoundException` and `InvalidInputException` report problems clearly; `Main` catches them and prints user-friendly messages.
