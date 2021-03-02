package agile.fuel.web.dto

import javax.validation.constraints.NotBlank

data class LoginRequestDTO(
    @field:NotBlank
    val password : String,
    @field:NotBlank
    val login : String
)
