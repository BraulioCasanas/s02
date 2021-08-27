package com.client.service;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import javax.ws.rs.core.Response;

@Client("http://store.store.svc.cluster.local")
@Header(name = HttpHeaders.USER_AGENT, value = "client")
public interface ExternalStore {

    @Get("/client/externalCall")
    Response externalCall();
}
