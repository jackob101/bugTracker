package com.trix.bugtracker.model.Comment;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CommentSerializer extends StdSerializer<Comment>{

    public CommentSerializer(){
	this(null);
    }

    protected CommentSerializer(Class<Comment> t) {
	super(t);
    }

	@Override
	public void serialize(Comment value, JsonGenerator gen, SerializerProvider provider) throws IOException {

	    gen.writeStartObject();
	    gen.writeNumberField("id", value.getId());
	    gen.writeStringField("comment", value.getComment());
	    gen.writeObjectFieldStart("user");
	    gen.writeNumberField("id", value.getCommentOwner().getId());
	    gen.writeStringField("name", value.getCommentOwner().getName());
	    gen.writeStringField("lastName", value.getCommentOwner().getLastName());
	    gen.writeEndObject();
	    gen.writeNumberField("issueId", value.getIssue().getId());
	    gen.writeStringField("creationDate", value.getCreationDate().toString());
	    gen.writeEndObject();
	}

}
