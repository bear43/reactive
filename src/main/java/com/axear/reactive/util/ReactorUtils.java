package com.axear.reactive.util;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class ReactorUtils {
    public static <T> Mono<T> errorIfEmpty(Mono<T> source, Supplier<Exception> exceptionSupplier) {
        return source.switchIfEmpty(Mono.defer(() -> Mono.error(exceptionSupplier.get())));
    }
}
