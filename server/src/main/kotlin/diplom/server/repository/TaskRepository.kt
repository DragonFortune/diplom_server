package diplom.server.repository

import diplom.server.entity.TaskEntity
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@EnableJpaRepositories
@Repository
interface TaskRepository: CrudRepository<TaskEntity, Int> {

    fun findByUserId(user_id: Int): List<TaskEntity>?
}