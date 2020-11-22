package agile.fuel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuelApplication

fun main(args: Array<String>) {
	runApplication<FuelApplication>(*args)
}
