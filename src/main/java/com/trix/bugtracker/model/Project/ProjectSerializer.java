package com.trix.bugtracker.model.Project;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ProjectSerializer extends StdSerializer<Project> {

    public ProjectSerializer() {
        this(null);
    }

    public ProjectSerializer(Class<Project> t) {
        super(t);
    }

    @Override
    public void serialize(Project value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        gen.writeNumberField("id", value.getId());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("description", value.getDescription());
        if (value.getAssignedUsers() != null) {
            gen.writeNumberField("assignedUsers", value.getAssignedUsers().size());
        } else {
            gen.writeNumberField("assignedUsers", 0);
        }
        if (value.getIssues() != null) {
            gen.writeNumberField("issues", value.getIssues().size());
        } else {
            gen.writeNumberField("issues", 0);
        }

        gen.writeEndObject();
    }
}
