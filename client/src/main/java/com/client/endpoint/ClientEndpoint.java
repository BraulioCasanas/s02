package com.client.endpoint;

import com.client.dto.ClientSaveCommand;
import com.client.dto.ClientUpdateCommand;
import com.client.entity.ClientEntity;
import com.client.repository.ClientRepository;
import com.client.service.ExternalStore;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Slf4j
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
//    public Response externalCall() {
//        return externalStore.externalCall();
    public Integer externalCall() {
        Integer records = externalStore.externalCall();
        log.info("records :: ".concat(records.toString()));
        return records;
    }

    @GET
    @Path("/detail/{id}")
    public ClientEntity show(@PathParam("id") Long id) {
        return itemRepository
                .findById(id)
                .orElse(null);
    }

    @PUT
    @Path(value = "/update/{id}")
    public Response update(@PathParam("id") Long id, @Valid ClientUpdateCommand command) {

        if (itemRepository.findById(id).isEmpty()) {
            return Response.notModified("the user with id ".concat(id.toString()).concat(" not exist")).build();
        }

        int numberOfEntitiesUpdated = itemRepository.update(id, command.getName(), command.getDescription());

        return Response
                .noContent()
                .header(HttpHeaders.LOCATION, location(id).getPath())
                .build();
    }

    @GET
    @Path(value = "/list")
    public List<ClientEntity> list() {
        return itemRepository.findAll();
    }

    @POST
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
