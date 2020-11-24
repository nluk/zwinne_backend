@file:JvmName("MongoConverters")
package agile.fuel.domain.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@WritingConverter
class LocalDateTimeToString : Converter<LocalDateTime, String>{
    override fun convert(localDateTime: LocalDateTime): String = localDateTime.toString()
}

@ReadingConverter
class StringToLocalDateTime : Converter<String, LocalDateTime>{
    override fun convert(localDateTime: String): LocalDateTime = LocalDateTime.parse(localDateTime)
}

@WritingConverter
class LocalDateTimeToInteger : Converter<LocalDateTime, Long>{
    override fun convert(localDateTime: LocalDateTime): Long = localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond()

}

fun allConverters() : List<Converter<*,*>> = listOf(
        LocalDateTimeToString(),
        StringToLocalDateTime()
)
