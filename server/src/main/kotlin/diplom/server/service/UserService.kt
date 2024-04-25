package diplom.server.service

import diplom.server.dto.UserDto
import org.apache.catalina.User

interface UserService {

    fun getAll(): List<UserDto>

    fun create(dto: UserDto): Int

    fun findByEmail(email: String): UserDto?

}