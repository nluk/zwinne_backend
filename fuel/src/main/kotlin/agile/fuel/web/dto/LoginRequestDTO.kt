package agile.fuel.web.dto

import javax.validation.constraints.NotBlank

data class LoginRequestDTO(
    @NotBlank
    val password : String,
    @NotBlank
    val login : String
)
