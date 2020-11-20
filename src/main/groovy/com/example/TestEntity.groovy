package com.example

import io.micronaut.data.annotation.*

import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import java.time.Instant

@MappedEntity
class TestEntity {

    @Id
    @AutoPopulated
    UUID id

    @NotBlank
    @Max(30l)
    String name

    @DateCreated
    Instant created

    @DateUpdated
    Instant modified

}
