# pulsar-sink-demo

A simple Pulsar Sink demo.

Build:

```bash
mvn clean package -DskipTests
```

Download Pulsar 3.0.0 from [here](https://pulsar.apache.org/download/) and extract it to a directory (let's use env variable `PULSAR_DIR` to represent it).

```bash
mkdir -p $PULSAR_DIR/connectors
cp ./demo-config.yml $PULSAR_DIR
cp ./target/pulsar-sink-demo-1.0.0.nar $PULSAR_DIR/connectors
cd $PULSAR_DIR
./bin/pulsar-daemon start standalone
./bin/pulsar-admin sinks create --sink-config-file ./demo-config.yml
# You will see "Created successfully"
./bin/pulsar-client produce -m hello my-topic
```

Then, in `logs/functions/public/default/demo-sink/demo-sink-0.log`, you will see the following logs.

```
2023-07-20T21:05:27,028+0800 [public/default/demo-sink-0] INFO  org.example.DemoSink - XYZ sink 7:0:-1
2023-07-20T21:05:27,040+0800 [public/default/demo-sink-0] INFO  org.apache.pulsar.functions.instance.JavaInstanceRunnable - Encountered exception in sink write:
java.lang.ClassCastException: class org.apache.pulsar.client.impl.MessageIdImpl cannot be cast to class org.apache.pulsar.client.impl.MessageIdImpl (org.apache.pulsar.client.impl.MessageIdImpl is in unnamed module of loader java.net.URLClassLoader @5a07e868; org.apache.pulsar.client.impl.MessageIdImpl is in unnamed module of loader org.apache.pulsar.common.nar.NarClassLoader @7ea08277)
	at org.example.DemoSink.lambda$write$0(DemoSink.java:25) ~[Isc1RCEuFtRMxZW7AdGN0g/:?]
	at java.util.Optional.ifPresent(Optional.java:178) ~[?:?]
	at org.example.DemoSink.write(DemoSink.java:22) ~[Isc1RCEuFtRMxZW7AdGN0g/:?]
	at org.apache.pulsar.functions.instance.JavaInstanceRunnable.sendOutputMessage(JavaInstanceRunnable.java:429) ~[?:?]
	at org.apache.pulsar.functions.instance.JavaInstanceRunnable.handleResult(JavaInstanceRunnable.java:391) ~[?:?]
	at org.apache.pulsar.functions.instance.JavaInstanceRunnable.run(JavaInstanceRunnable.java:331) ~[?:?]
	at java.lang.Thread.run(Thread.java:833) ~[?:?]
2023-07-20T21:05:27,046+0800 [public/default/demo-sink-0] ERROR org.apache.pulsar.functions.instance.JavaInstanceRunnable - [public/default/demo-sink:0] Uncaught exception in Java Instance
java.lang.ClassCastException: class org.apache.pulsar.client.impl.MessageIdImpl cannot be cast to class org.apache.pulsar.client.impl.MessageIdImpl (org.apache.pulsar.client.impl.MessageIdImpl is in unnamed module of loader java.net.URLClassLoader @5a07e868; org.apache.pulsar.client.impl.MessageIdImpl is in unnamed module of loader org.apache.pulsar.common.nar.NarClassLoader @7ea08277)
	at org.example.DemoSink.lambda$write$0(DemoSink.java:25) ~[?:?]
	at java.util.Optional.ifPresent(Optional.java:178) ~[?:?]
	at org.example.DemoSink.write(DemoSink.java:22) ~[?:?]
	at org.apache.pulsar.functions.instance.JavaInstanceRunnable.sendOutputMessage(JavaInstanceRunnable.java:429) ~[org.apache.pulsar-pulsar-functions-instance-3.0.0.jar:3.0.0]
	at org.apache.pulsar.functions.instance.JavaInstanceRunnable.handleResult(JavaInstanceRunnable.java:391) ~[org.apache.pulsar-pulsar-functions-instance-3.0.0.jar:3.0.0]
	at org.apache.pulsar.functions.instance.JavaInstanceRunnable.run(JavaInstanceRunnable.java:331) ~[org.apache.pulsar-pulsar-functions-instance-3.0.0.jar:3.0.0]
	at java.lang.Thread.run(Thread.java:833) ~[?:?]
```
