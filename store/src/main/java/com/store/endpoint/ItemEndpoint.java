package com.store.endpoint;

import com.store.dto.ItemSaveCommand;
import com.store.dto.ItemUpdateCommand;
import com.store.entity.ItemEntity;
import com.store.repository.ItemRepository;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/item")
public class ItemEndpoint {

    private final ItemRepository itemRepository;

    public ItemEndpoint(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GET
    @Path(("/externalCall"))
    public Response externalCall() {
        Integer integer = itemRepository.countItems();
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public ItemEntity show(@PathParam("id") Long id) {
        return itemRepository
                .findById(id)
                .orElse(null);
    }

    @POST
    @Path(value = "/update")
    public Response update(@Valid ItemUpdateCommand command) {
        int numberOfEntitiesUpdated = itemRepository.update(command.getId(), command.getName(), command.getDescription());

        return Response
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath())
                .build();
    }

    @GET
    @Path(value = "/list")
    public List<ItemEntity> list() {
        return itemRepository.findAll();
    }

    @PUT
    @Path(value = "/save")
    public Response save(@Valid ItemSaveCommand cmd) {
        ItemEntity genre = itemRepository.save(cmd.getName());

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

    protected URI location(ItemEntity item) {
        return location(item.getId());
    }
}
