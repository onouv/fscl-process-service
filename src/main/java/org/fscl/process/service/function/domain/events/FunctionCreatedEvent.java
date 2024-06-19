package org.fscl.process.service.function.domain.events;

import io.debezium.outbox.quarkus.ExportedEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.core.domain.events.FsclDomainEvent;
import org.fscl.process.service.function.domain.Function;

import java.time.Instant;

@EqualsAndHashCode(callSuper = false)
@Value
public class FunctionCreatedEvent extends FsclDomainEvent implements ExportedEvent<String, JsonNode>  {

    private final long id;
    private final Instant timestamp;
    private final JsonNode function;

    private static ObjectMapper mapper = new ObjectMapper();

    public FunctionCreatedEvent(long id, JsonNode function) {
        this.id = id;
        this.function = function;
        this.timestamp = Instant.now();
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(id);
    }

    @Override
    public String getAggregateType() {
        return "Function";
    }


    @Override
    public String getType() {
        return "FunctionCreated";
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public JsonNode getPayload() {
        return this.function;
    }

    public static FunctionCreatedEvent of(Function function) {
        final FsclEntityId entityId = function.getEntityId();
        ObjectNode jsonEntityId = mapper.createObjectNode()
                .put("project", entityId.project())
                .put("code", entityId.code());

        ObjectNode jsonEvent = mapper.createObjectNode()
                .set("entityId", jsonEntityId);
        jsonEvent
                .put("name", function.getName())
                .put("description", function.getDescription());

        return new FunctionCreatedEvent(function.getId(), jsonEvent);
    }

    public String toString() {
        return "{ id: " + this.id + ", function: " + this.function.toString() + "timestamp: " + this.timestamp.toString() + " }";
    }

}
