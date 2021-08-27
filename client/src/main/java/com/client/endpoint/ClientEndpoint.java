package com.client.endpoint;

import com.client.dto.ClientSaveCommand;
import com.client.dto.ClientUpdateCommand;
import com.client.entity.ClientEntity;
import com.client.repository.ClientRepository;
import com.client.service.ExternalStore;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/client")
public class ClientEndpoint {

    private final ClientRepository itemRepository;
    private final ExternalStore externalStore;

    public ClientEndpoint(ClientRepository itemRepository, ExternalStore externalStore) {
        this.itemRepository = itemRepository;
        this.externalStore = externalStore;
    }

    @GET
    @Path(("/externalCall"))
    public Response externalCall() {
        return externalStore.externalCall();
    }

    @GET
    @Path("/{id}")
    public ClientEntity show(@PathParam("id") Long id) {
        return itemRepository
                .findById(id)
                .orElse(null);
    }

    @POST
    @Path(value = "/update")
    public Response update(@Valid ClientUpdateCommand command) {
        int numberOfEntitiesUpdated = itemRepository.update(command.getId(), command.getName(), command.getDescription());

        return Response
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath())
                .build();
    }

    @GET
    @Path(value = "/list")
    public List<ClientEntity> list() {
        return itemRepository.findAll();
    }

    @PUT
    @Path(value = "/save")
    public Response save(@Valid ClientSaveCommand cmd) {
        ClientEntity genre = itemRepository.save(cmd.getName());

        return Response
                .ok(genre)
                .build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        itemRepository.deleteById(id);
        return Response.noContent().build();
    }

    protected URI location(Long id) {
        return URI.create("/" + id);
    }

    protected URI location(ClientEntity item) {
        return location(item.getId());
    }
}
