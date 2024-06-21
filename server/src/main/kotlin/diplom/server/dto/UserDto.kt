package diplom.server.dto

class UserDto (
    val user_id: Int? = null,
    val user_name: String? = null,
    val email: String,
    val password: String
)
class LoginResponse(
    val token: String,
    val user_id: Int?,
    val user_name: String?
)
