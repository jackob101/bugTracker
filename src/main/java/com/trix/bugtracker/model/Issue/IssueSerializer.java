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
        gen.writeStringField("openedTime", value.getOpenedTime() + "");
        gen.writeStringField("closedTime", value.getClosedTime() + "");
        gen.writeStringField("priority", value.getPriority().toString());

        if (value.getCreatedBy() != null) {
            gen.writeNumberField("createdById", value.getCreatedBy().getId());
        } else {
            gen.writeStringField("createById", "null");
        }

        if (value.getUsers() != null) {
            gen.writeArrayFieldStart("users");

            for (User user : value.getUsers()) {
                gen.writeStartObject();
                gen.writeStringField("userName", user.getName());
                gen.writeStringField("userLastName", user.getLastName());
                gen.writeNumberField("userId", user.getId());
                gen.writeEndObject();
            }

            gen.writeEndArray();
        } else {
            gen.writeStringField("users", "null");
        }

        if (value.getProject() != null) {
            gen.writeStringField("projectName", value.getProject().getName());
            gen.writeNumberField("projectId", value.getProject().getId());
        }

        gen.writeEndObject();

    }
}
