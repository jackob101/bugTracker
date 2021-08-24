package com.trix.bugtracker.model.Issue;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.trix.bugtracker.model.User.User;

import java.io.IOException;

public class IssueSerializer extends StdSerializer<Issue> {

    public IssueSerializer() {
        this(null);
    }

    public IssueSerializer(Class<Issue> t) {
        super(t);
    }

    @Override
    public void serialize(Issue value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeStringField("description", value.getDescription());
        gen.writeStringField("createdDate", (value.getOpenedTime() + "").split("T")[0]);
        gen.writeStringField("closedTime", value.getClosedTime() + "");
        gen.writeStringField("priority", value.getPriority().toString());

        if (value.getCreatedBy() != null) {
            gen.writeObjectFieldStart("createdBy");
            gen.writeNumberField("id", value.getCreatedBy().getId());
            gen.writeStringField("name", value.getCreatedBy().getName());
            gen.writeStringField("lastName", value.getCreatedBy().getLastName());
            gen.writeEndObject();
        } else {
            gen.writeStringField("createById", "null");
        }

        if (value.getUsers() != null) {
            gen.writeArrayFieldStart("users");

            for (User user : value.getUsers()) {
                gen.writeStartObject();
                gen.writeStringField("name", user.getName());
                gen.writeStringField("lastName", user.getLastName());
                gen.writeNumberField("id", user.getId());
                gen.writeEndObject();
            }

            gen.writeEndArray();
        } else {
            gen.writeStringField("users", "null");
        }

        if (value.getProject() != null) {
            gen.writeObjectFieldStart("project");
            gen.writeStringField("name", value.getProject().getName());
            gen.writeNumberField("id", value.getProject().getId());
            gen.writeEndObject();
        }

        gen.writeEndObject();

    }
}
