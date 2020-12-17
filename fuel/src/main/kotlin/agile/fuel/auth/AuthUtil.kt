package agile.fuel.auth

import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder

fun getCurrentUserId() : ObjectId{
    return (SecurityContextHolder.getContext().authentication.principal as AuthUserDetails).id
}

fun getCurrentUserLogin() : String{
    return (SecurityContextHolder.getContext().authentication.principal as AuthUserDetails).username
}