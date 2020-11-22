package agile.fuel.web.dto

import javax.validation.constraints.NotBlank

data class LoginDTO(
        @NotBlank
        val password : String,
        @NotBlank
        val username : String
)