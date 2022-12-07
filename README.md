# Device Measurement Server

## Creation of JAR file

After execution of Build task (e.g. Intellij Idea From Gradle window or Build menu item)
file MeasureDeviceServer.jar should be created in build\lib project subdirectory.

## Run server

Switch into mentioned directory in separate command window (e.g. on Windows Win+R and CMD, cd ...)
and run following command (java executable location should be already assigned in path)

java.exe -jar .\MeasureDeviceServer.jar

SERVER SHOULD BE LAUNCHED BEFORE RUNNING CLIENT !
Use separate window for server process and separate window for client process.
See MeasureDeviceClient project for further info.

## Produced output
```

java.exe -jar .\MeasureDeviceServer.jar

Waiting for the client request
Message Received: Thermometer0;Thermometer;C;0
Waiting for the client request
Message Received: Amperemeter1;Amperemeter;A;10
Waiting for the client request
Message Received: Thermometer0;Thermometer;C;20
Waiting for the client request
Message Received: Amperemeter1;Amperemeter;A;30
Waiting for the client request
Message Received: Udometer0;Udometer;mm;40
Waiting for the client request
Message Received: Amperemeter2;Amperemeter;A;50
Waiting for the client request
Message Received: Udometer0;Udometer;mm;60
Waiting for the client request
Message Received: Thermometer1;Thermometer;C;70
Waiting for the client request
Message Received: Udometer2;Udometer;mm;80
Waiting for the client request
Message Received: Amperemeter0;Amperemeter;A;90
Waiting for the client request
Message Received: Thermometer2;Thermometer;C;100
Waiting for the client request
Message Received: Voltmeter2;Voltmeter;V;110
Waiting for the client request
Message Received: Udometer0;Udometer;mm;120
Waiting for the client request
Message Received: Thermometer1;Thermometer;C;130
Waiting for the client request
Message Received: Udometer0;Udometer;mm;140
Waiting for the client request
Message Received: Voltmeter0;Voltmeter;V;150
Waiting for the client request
Message Received: Thermometer1;Thermometer;C;160
Waiting for the client request
Message Received: Thermometer1;Thermometer;C;170
Waiting for the client request
Message Received: Amperemeter0;Amperemeter;A;180
Waiting for the client request
Message Received: quit

Shutting down Socket server....

=== Meessage counts by device name ===
Udometer0 - 4 message(s)
Amperemeter2 - 1 message(s)
Amperemeter1 - 2 message(s)
Thermometer2 - 1 message(s)
Thermometer1 - 4 message(s)
Thermometer0 - 2 message(s)
Voltmeter2 - 1 message(s)
Amperemeter0 - 2 message(s)
Voltmeter0 - 1 message(s)
Udometer2 - 1 message(s)

=== Meessage counts by device type ===
Thermometer - 7 message(s)
Udometer - 5 message(s)
Amperemeter - 5 message(s)
Voltmeter - 2 message(s)

=== Total message count: 19 ===

Process finished with exit code 0
```