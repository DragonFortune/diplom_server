package diplom.server.service.impl

import diplom.server.dto.TaskDto
import diplom.server.dto.UserDto
import diplom.server.entity.TaskEntity
import diplom.server.entity.UserEntity
import diplom.server.repository.TaskRepository
import diplom.server.service.TaskService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TaskServiceImpl (
    private val taskRepository: TaskRepository,
) : TaskService{

    override fun findByUserId(user_id: Int): List<TaskDto>? {
        val tasks = taskRepository.findByUserId(user_id)
        return tasks?.map { it.toDto() }
    }

    override fun create(dto: TaskDto): Int {
        return taskRepository.save(dto.toEntity()).taskId
    }

    private fun TaskEntity.toDto(): TaskDto =
        TaskDto(
            task_id = this.taskId,
            user_id = this.userId,
            task_name = this.taskName,
            category = this.category ,
            data = this.data,
            priority = this.priority,
            completed = this.completed
    )

    private fun TaskDto.toEntity(): TaskEntity =
        TaskEntity(
            taskId = 0,
            userId = this.user_id,
            taskName = this.task_name,
            category = this.category ?: "",
            data = this.data,
            priority = this.priority ?: 0,
            completed = this.completed
        )
}