package agile.fuel.web.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegisterUserDTO(
        @NotBlank
        val login : String,
        @NotBlank
        val password : String,
        @Email(regexp = EMAIL_REGEX)
        @NotBlank
        val email : String
){
        companion object Regex{
                const val EMAIL_REGEX = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,}"
        }
}