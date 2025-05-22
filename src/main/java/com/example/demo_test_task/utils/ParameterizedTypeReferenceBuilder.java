package com.example.demo_test_task.utils;

import com.google.common.reflect.TypeToken;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;

@EqualsAndHashCode
@ToString
public class ParameterizedTypeReferenceBuilder {


    public static <T> ParameterizedTypeReference<T> fromTypeToken(TypeToken<T> typeToken) {
        return new TypeTokenParameterizedTypeReference<>(typeToken);
    }

    private static class TypeTokenParameterizedTypeReference<T> extends ParameterizedTypeReference<T> {

        private final Type type;

        private TypeTokenParameterizedTypeReference(TypeToken<T> typeToken) {
            this.type = typeToken.getType();
        }

        @Override
        public Type getType() {
            return type;
        }
    }
}
