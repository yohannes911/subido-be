package org.subido.core.util;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * Utility class for build java beans in code.
 */
public final class BuildUtils {

    private BuildUtils() {
    }

    /**
     * Builds instance by calling passed setters, etc.
     *
     * @param instance  the bean to build
     * @param consumers the setter, etc. consumes instance to call
     * @param <B>       the type of instance
     * @return the original instance
     */
    @SafeVarargs
    public static <B> B build(@Nonnull final B instance, @Nonnull final Consumer<B>... consumers) {
        for (Consumer<B> consumer : consumers) {
            consumer.accept(instance);
        }
        return instance;
    }
}
