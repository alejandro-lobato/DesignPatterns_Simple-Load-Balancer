
# Design Patterns - Final Project

## Description

This project was based on Chapter 1 - "Introduction to Middleware" of author Sacha Krakowiak.
More specifically, on the section of process management and synchronization. In this section we are presented with 3 options available to handle concurrent calls over a procedure in a distributed system.

![Handling Concurrent Calls, Sacha Krakowiak](\ThreadPoolReference.jpg)

The third option was selected for implementation. Extract from the chapter:

> Worker threads communicate with the daemon through a shared buffer using the producer-consumer scheme. 
>
>Worker threads are waiting for new work to arrive; after executing the procedure, a worker thread returns to the pool, i.e. tries to get new work to do. If all worker threads are busy when a call arrives, the execution of the call is delayed until a thread becomes free.

Trying to replicate this behavior, I decided to use the Proxy design pattern to make the daemon thread communicate with the buffer and used Chain of Responsibility to bypass waiting time; when the threads were busy, the work request would instead be passed around among worker thread objects to handle it. Finally, the Singleton pattern is used to ensure that only one Daemon object exists, as well as only one Buffer instance.