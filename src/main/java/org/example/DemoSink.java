package org.example;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.schema.GenericObject;
import org.apache.pulsar.client.impl.MessageIdImpl;
import org.apache.pulsar.functions.api.Record;
import org.apache.pulsar.io.core.Sink;
import org.apache.pulsar.io.core.SinkContext;

@Slf4j
public class DemoSink implements Sink<GenericObject> {

    @Override
    public void open(Map<String, Object> config, SinkContext sinkContext) throws Exception {

    }

    @Override
    public void write(Record<GenericObject> record) throws Exception {
        record.getMessage().ifPresent(msg -> {
            final MessageId id = msg.getMessageId();
            log.info("XYZ sink {}", id);
            final MessageIdImpl msgIdImpl = (MessageIdImpl) id;
            log.info("XYZ ledger: {}, entry: {}", msgIdImpl.getLedgerId(), msgIdImpl.getEntryId());
        });
    }

    @Override
    public void close() throws Exception {

    }
}
