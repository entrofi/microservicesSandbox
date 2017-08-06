package net.entrofi.microservices.sandbox.kbms.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by hcomak on 2/4/2015.
 */
public class CollectionSerializer extends JsonSerializer<Collection<?>> {
    private static final Logger LOG = LoggerFactory.getLogger(CollectionSerializer.class);

    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value    Value to serialize; can <b>not</b> be null.
     * @param jgen     Generator used to output resulting Json content
     * @param provider Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Collection<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        if (jgen.getOutputContext().getParent().getCurrentIndex() == 0) {
            LOG.debug("Serializing collection........" + jgen.getOutputContext().getCurrentName());
            jgen.writeObject(value);
        } else {
            jgen.writeStartArray();
            jgen.writeEndArray();
        }
    }
}
