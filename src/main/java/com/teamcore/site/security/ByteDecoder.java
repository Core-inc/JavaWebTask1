package com.teamcore.site.security;


@FunctionalInterface
public interface ByteDecoder {

    byte[] decode(byte[] encodedBytes);
}
