package diplom.server.service

import diplom.server.dto.UserDto
import org.apache.catalina.User
import org.springframework.security.core.userdetails.UserDetails

interface UserService {

    fun getAll(): List<UserDto>

    fun create(dto: UserDto): Int

    fun findByEmail(email: String): UserDto?

    fun convertToUserDetails(userDto: UserDto): UserDetails

}