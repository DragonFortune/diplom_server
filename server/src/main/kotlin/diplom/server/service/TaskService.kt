package diplom.server.service

import diplom.server.dto.TaskDto

interface TaskService {

    fun create(dto: TaskDto): Int

    fun findByUserId(user_id: Int): List<TaskDto>?
}