package com.teamcore.manageapp.service.security;


@FunctionalInterface
public interface ByteDecoder {

    byte[] decode(byte[] encodedBytes);
}
