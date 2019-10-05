package org.qbit.challenge.challenge.dev.converter;

public interface Converter<S,T> {

    T convert(S source);
}