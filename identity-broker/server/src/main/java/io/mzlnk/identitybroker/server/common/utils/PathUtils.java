package io.mzlnk.identitybroker.server.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static com.fasterxml.jackson.databind.JsonMappingException.Reference;

public class PathUtils {

    public static String getPath(List<Reference> references) {
        String root = references.get(0).getFieldName();

        BiFunction<String, Reference, String> accumulator = (path, ref) -> {
            return ref.getFrom() instanceof Collection
                    ? "%s[%d]".formatted(path, ref.getIndex())
                    : "%s.%s".formatted(path, ref.getFieldName());
        };

        // Stream#reduce does not use combiner during sequential reduction
        BinaryOperator<String> combiner = (s1, s2) -> s1;

        return references.stream()
                .skip(1)
                .reduce(root, accumulator, combiner);
    }

}
