package com.store.endpoint;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import javax.inject.Inject;

@MicronautTest
class ItemEndpointTest {

    @Inject
    @Client("/")
    HttpClient client;

//    @Ignore
//    @Test
//    public void supplyAnInvalidOrderTriggersValidationFailure() {
//        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> client.toBlocking().exchange(HttpRequest.GET("/list")));
//
//        assertNotNull(thrown.getResponse());
//        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
//    }
//
//    @Ignore
//    @Test
//    public void testFindNonExistingGenreReturns404() {
//        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
//            client.toBlocking().exchange(HttpRequest.GET("/99"));
//        });
//
//        assertNotNull(thrown.getResponse());
//        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
//    }
//
//    @Ignore
//    @Test
//    public void testGenreCrudOperations() {
//        HttpRequest request = HttpRequest.POST("/save", new ItemSaveCommand("laptop"));
//        HttpResponse response = client.toBlocking().exchange(request);
//
//        assertEquals(HttpStatus.CREATED, response.getStatus());
//    }
}