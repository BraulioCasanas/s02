package com.store.endpoint;

import com.store.dto.ItemSaveCommand;
import com.store.dto.ItemUpdateCommand;
import com.store.entity.ItemEntity;
import com.store.repository.ItemRepository;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/item")
public class ItemEndpoint {

    private final ItemRepository itemRepository;

    public ItemEndpoint(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Get("/{id}")
    public ItemEntity show(Long id) {
        return itemRepository
                .findById(id)
                .orElse(null);
    }

    @Post(value = "/update")
    public HttpResponse update(@Body @Valid ItemUpdateCommand command) {
        int numberOfEntitiesUpdated = itemRepository.update(command.getId(), command.getName(), command.getDescription());

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Get(value = "/list")
    public List<ItemEntity> list() {
        return itemRepository.findAll();
    }

    @Put(value = "/save")
    public HttpResponse<ItemEntity> save(@Body @Valid ItemSaveCommand cmd) {
        ItemEntity genre = itemRepository.save(cmd.getName());

        return HttpResponse
                .created(genre)
                .headers(headers -> headers.location(location(genre.getId())));
    }

    @Post("/ex")
    public HttpResponse<ItemEntity> saveExceptions(@Body @Valid ItemSaveCommand cmd) {
        try {
            ItemEntity genre = itemRepository.saveWithException(cmd.getName());
            return HttpResponse
                    .created(genre)
                    .headers(headers -> headers.location(location(genre.getId())));
        } catch(PersistenceException e) {
            return HttpResponse.noContent();
        }
    }

    @Delete("/delete/{id}")
    public HttpResponse delete(Long id) {
        itemRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/" + id);
    }

    protected URI location(ItemEntity item) {
        return location(item.getId());
    }
}
