package ru.otus.serializer.meta;

import org.junit.jupiter.api.Test;
import ru.otus.SampleObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectMetaReaderReflectionTest {

    @Test
    void readObjectMeta() {
        SampleObject sampleObject = new SampleObject(10, 20, "text");
        ObjectMetaReader objectMetaReader = new ObjectMetaReaderReflection();
        List<? extends FieldMeta> result = objectMetaReader.readObjectMeta(sampleObject);

        assertEquals(3, result.size());

        Map<String, Object> resultMap = result.stream().collect(Collectors.toMap(FieldMeta::getFieldName, FieldMeta::getFieldValue));

        assertTrue(resultMap.containsKey("someIntField"));
        assertTrue(resultMap.containsKey("someIntegerField"));
        assertTrue(resultMap.containsKey("someStringField"));

        assertEquals(10, resultMap.get("someIntField"));
        assertEquals(20, resultMap.get("someIntegerField"));
        assertEquals("text", resultMap.get("someStringField"));
    }
}