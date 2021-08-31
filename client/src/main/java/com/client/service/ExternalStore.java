package com.client.service;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

@Client("http://store.store.svc.cluster.local")
@Header(name = HttpHeaders.USER_AGENT, value = "client")
public interface ExternalStore {

    @Get("/item/externalCall")
//    Response externalCall();
    Integer externalCall();
}
