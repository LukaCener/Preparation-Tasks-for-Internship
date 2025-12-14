package org.LukaCener.praksa.zad3;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank String name,
        @Positive double price,
        @NotBlank String category
) {
}