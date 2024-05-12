package diplom.server.service.impl

import diplom.server.dto.UserDto
import diplom.server.entity.UserEntity
import diplom.server.repository.UserRepository
import diplom.server.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
): UserService {

    override fun getAll(): List<UserDto> {
        return userRepository.findAll()
            .map{it.toDto()}
    }

    override fun create(dto: UserDto): Int {
        return userRepository.save(dto.toEntity()).id
    }

    override fun findByEmail(email: String): UserDto? {
        val user = userRepository.findByEmail(email)
        return user?.toDto()
    }


    private fun UserEntity.toDto(): UserDto =
        UserDto(
            id = this.id,
            user_name = this.user_name,
            email = this.email,
            password = this.password,
        )

    private fun UserDto.toEntity(): UserEntity =
        UserEntity(
            id = 0,
            user_name = this.user_name ?: "",
            email = this.email,
            password = this.password,
        )

    override fun convertToUserDetails(userDto: UserDto): UserDetails {
        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        return User(userDto.email, userDto.password, authorities)
    }
}