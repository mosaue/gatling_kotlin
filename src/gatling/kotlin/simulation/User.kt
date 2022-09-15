package simulation

import org.apache.commons.lang3.RandomStringUtils
import java.util.*

data class User(
    val address: String = "string",
    val country: String = "string",
    val dob: String = "12/12/1990",
    val firstName: String = "string",
    val gender: String = "M",
    val homePhone: String = "(111) 111-1111",
    val lastName: String = "string",
    val locality: String = "string",
    val mobilePhone: String = "(111) 111-1111",
    val postalCode: String = "string",
    val region: String = "string",
    val ssn: String = "${Random().nextInt(111, 999)}-${Random().nextInt(11, 99)}-${Random().nextInt(1111, 9999)}",
    val title: String = "Mr.",
    val workPhone: String = "(111) 111-1111",
    val emailAddress: String = RandomStringUtils.randomAlphanumeric(20) + "@foo.com",
    val password: String = "${RandomStringUtils.randomAlphabetic(16)}aB-${Random().nextInt(111, 999)}"
)
