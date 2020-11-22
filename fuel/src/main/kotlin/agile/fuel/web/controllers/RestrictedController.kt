package agile.fuel.web.controllers

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@PreAuthorize("hasAnyAuthority('Admin','User')")
@RequestMapping("/restricted")
class RestrictedController {

    @GetMapping("/endpoint")
    fun getRestrictedEndpoint() : ResponseEntity<Any>{
        return ResponseEntity.ok("Got restricted")
    }
}