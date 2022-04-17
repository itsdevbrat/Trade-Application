package com.example.Test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class ErrorMessage {

    Instant timestamp;

    String description;
}
