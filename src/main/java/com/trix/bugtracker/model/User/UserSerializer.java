package com.trix.bugtracker.model.User;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.Project.Project;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("lastName", value.getLastName());
        gen.writeStringField("email", value.getEmail());
        gen.writeNumberField("age", value.getAge());
        if (value.getIssues() != null) {
            gen.writeArrayFieldStart("issues");
            for (Issue issue : value.getIssues()) {
                gen.writeStartObject();
                gen.writeNumberField("id", issue.getId());
                gen.writeStringField("title", issue.getTitle());
                gen.writeEndObject();
            }
            gen.writeEndArray();
        }
        if (value.getProjects() != null) {
            gen.writeArrayFieldStart("projects");
            for (Project project : value.getProjects()) {
                gen.writeStartObject();
                gen.writeNumberField("id", project.getId());
                gen.writeStringField("name", project.getName());
                gen.writeEndObject();
            }
            gen.writeEndArray();
        }
        if (value.getSubmittedIssues() != null) {
            gen.writeArrayFieldStart("submittedIssues");
            for (Issue submittedIssue : value.getSubmittedIssues()) {
                gen.writeStartObject();
                gen.writeNumberField("id", submittedIssue.getId());
                gen.writeStringField("title", submittedIssue.getTitle());
                gen.writeEndObject();
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
    }
}
