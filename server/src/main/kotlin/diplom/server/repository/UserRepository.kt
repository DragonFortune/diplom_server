package diplom.server.repository

import diplom.server.entity.UserEntity
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@EnableJpaRepositories
@Repository
interface UserRepository: CrudRepository<UserEntity, Int> {
    fun findByEmail(email: String): UserEntity?
}