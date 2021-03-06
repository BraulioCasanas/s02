package com.store.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Introspected
public class ItemUpdateCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
