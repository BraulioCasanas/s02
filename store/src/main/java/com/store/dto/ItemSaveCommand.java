package com.store.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Introspected
@AllArgsConstructor
public class ItemSaveCommand {

    @NotBlank
    private String name;
}
