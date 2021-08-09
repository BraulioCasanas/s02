package com.clients.ednpoint;

import com.clients.dto.ClientSaveCommand;
import com.clients.dto.ClientUpdateCommand;
import com.clients.entity.ClientEntity;
import com.clients.repository.ClientRepository;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/client")
public class ClientEndpoint {

    private final ClientRepository ClientRepository;

    public ClientEndpoint(ClientRepository ClientRepository) {
        this.ClientRepository = ClientRepository;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ClientEntity show(@PathParam("id") Long id) {
        return ClientRepository
                .findById(id)
                .orElse(null);
    }

    @POST
    @Path("/update")
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(@Valid ClientUpdateCommand command) {
        int numberOfEntitiesUpdated = ClientRepository.update(command.getId(), command.getName(), command.getIsOlder());

        return Response
                .noContent()
                .build();
    }

    @GET
    @Path("/list")
    @Produces("application/json")
    public List<ClientEntity> list() {
        return ClientRepository.findAll();
    }

    @PUT
    @Path("/save")
    @Produces("application/json")
    @Consumes("application/json")
    public Response save(@Valid ClientSaveCommand cmd) {
        ClientEntity genre = ClientRepository.save(cmd.getName(), cmd.getCity(), cmd.getIsOlder());

        return Response
                .ok(genre)
                .build();
    }

    @POST
    @Path("/ex")
    @Produces("application/json")
    @Consumes("application/json")
    public Response saveExceptions(@Valid ClientSaveCommand cmd) {
        try {
            ClientEntity genre = ClientRepository.saveWithException(cmd.getName());
            return Response
                    .ok(genre)
                    .build();
        } catch(PersistenceException e) {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") Long id) {
        ClientRepository.deleteById(id);
        return Response.noContent().build();
    }

}