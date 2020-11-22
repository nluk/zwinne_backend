package agile.fuel.web.dto

import javax.validation.constraints.NotBlank

data class RegisterUserDTO(
        @NotBlank
        val login : String,
        @NotBlank
        val password : String,
        @NotBlank
        val email : String,
)