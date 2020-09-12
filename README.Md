# Kafka Flow

A template project to set up a flow between different components using Kafka.
The main idea is to avoid creating multiple consumers and
publishers, letting this be handled by one single class, which
maps the message to the correct handler and sends the processed data to the
next topic.

## Overview

`IHandler` set up the `handler` function and the `topic` property which is used
to map the message to the handler.

`BaseHandler` is the one responsible to call the correct handler and publishes
the data to the next topic, if there is one.

## Running

```
docker-compose up
``` 

Run the test and see the logger messages with the handlers called one by one.
