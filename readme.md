# Functionnal java tuto

This repository contains unit test that you have to complete in order to learn functional data class. 

## Vavr 

[vavr](https://www.vavr.io) is java library that bring functional data class to java like options, eithers, try, futures and better collections.  

This tuto covers  
 * `Option`: https://www.vavr.io/vavr-docs/#_option
 * `Either`: https://www.vavr.io/vavr-docs/#_either
 
## Spring reactor 

Spring reactor is an implementor of the reactive streams specification. It's used to handle and compose async or non blocking calls.  

The main pieces are 
 * `Mono` to handle a value (or no value) that will be available in the future
 * `Flux` to handle a set of value that will be available in the future
 
`Mono`is lazy in contrary of java `CompletionStage` or vavr `Future`. With reactor you describe what you want to do and you will execute all at the end. 
As an implementor of the reactive streams specification reactor handle backpressure. It means that the producer is slow down if the consumer is slower.

The api of reactor is simpler than akka stream and well suited for reactive web application with spring web flux. 

## Akka stream   

Akka stream is an implementor of the reactive streams specification like. It's used to handle and compose async or non blocking calls.
Akka stream is well suited to implement real time stream processing with full control on the resources that are used. 

The main pieces are 
 * `Source`: something that emit elements 
 * `Flow`: a piece of processing. Like a pipeline, the data pass through and are processed (filtered, transformed ...)
 * `Sink`: The sink will consume the stream, it is where the streams ends.
 
Like reactor, with akka stream you will define a blue print that will be run. 
As an implementor of the reactive streams specification akka stream handle backpressure. It means that the producer is slow down if the consumer is slower.

The akka stream api is more complicated than reactor but more powerful. 
With the `Flow` abstraction you can define reusable pieces. This is a plus in stream processing application. 

The [alpakka](https://doc.akka.io/docs/alpakka/current/) project brings a lot of predefined pieces to interact with a lot of databases or technologies.      