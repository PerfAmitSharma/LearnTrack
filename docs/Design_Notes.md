# Design Notes

## Why ArrayList Was Used Instead of Array

`ArrayList` was used because the number of students, courses, and enrollments can grow while the application is running. A normal array has a fixed size, but an `ArrayList` resizes dynamically and provides useful methods such as `add`, `isEmpty`, and iteration support.

## Where Static Members Were Used

Static members were used in `IdGenerator`. ID counters belong to the application-level ID generation process, not to one specific student, course, or enrollment object. This makes static counters and static methods a simple and appropriate choice.

## Where Inheritance Was Used

`Person` is the base class for `Student` and `Trainer`. Common fields such as `id`, `firstName`, `lastName`, and `email` are kept in `Person`. This reduces duplicate code and demonstrates inheritance.

## Where Polymorphism Was Used

`Student` and `Trainer` override `getDisplayName()` from `Person`. This means the same method name can produce specialized behavior depending on the actual object type.

## Why Service Classes Were Created

Service classes keep business logic away from the console UI. `StudentService`, `CourseService`, and `EnrollmentService` make the code easier to read, test, and extend. `Main` remains focused on showing menus, reading input, and displaying results.

## Exception Handling Approach

The project uses custom exceptions such as `EntityNotFoundException` and `InvalidInputException`. These exceptions allow service classes to report problems clearly, while `Main` catches them and shows user-friendly messages.
