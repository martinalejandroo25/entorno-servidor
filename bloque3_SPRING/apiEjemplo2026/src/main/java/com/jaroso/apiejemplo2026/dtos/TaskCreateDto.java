package com.jaroso.apiejemplo2026.dtos;

import java.time.LocalDate;

public record TaskCreateDto(String title, String description, LocalDate finishDate,
                            String username) {
}
