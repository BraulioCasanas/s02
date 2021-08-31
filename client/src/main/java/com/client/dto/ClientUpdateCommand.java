package com.client.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Introspected
public class ClientUpdateCommand {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
