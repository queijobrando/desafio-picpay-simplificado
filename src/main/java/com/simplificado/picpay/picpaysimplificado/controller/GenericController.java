package com.simplificado.picpay.picpaysimplificado.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericController {
    default URI generateHeaderLocationLong(Long id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    default URI generateHeaderLocationUuid(UUID id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
