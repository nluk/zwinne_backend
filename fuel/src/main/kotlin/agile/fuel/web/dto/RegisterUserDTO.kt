package agile.fuel.web.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegisterUserDTO(
        @field:NotBlank
        val login : String,
        @field:NotBlank
        val password : String,
        @field:Email(regexp = EMAIL_REGEX)
        @field:NotBlank
        val email : String
){
        companion object Regex{
                const val EMAIL_REGEX = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,}"
        }
}