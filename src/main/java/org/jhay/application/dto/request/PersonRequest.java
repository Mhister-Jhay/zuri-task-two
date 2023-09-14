package org.jhay.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonRequest {
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be 3 characters minimum")
    @Pattern(regexp="^[A-Za-z\\s]{2,}$", message="Name must contain only letters and spaces")
    private String name;
}
