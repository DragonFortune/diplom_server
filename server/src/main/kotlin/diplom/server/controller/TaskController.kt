package diplom.server.controller

import diplom.server.dto.TaskDto
import diplom.server.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tasks")
class TaskController (
    private val taskService: TaskService
) {
    @PostMapping("/find")
    fun find(@RequestBody task: TaskDto): ResponseEntity<Any>{
        val user = taskService.findByUserId(task.user_id)
        return ResponseEntity.ok(user )
    }

    @PostMapping("/create")
    fun create(@RequestBody dto: TaskDto): Int{
        val newTask = TaskDto(
            user_id = dto.user_id,
            task_name = dto.task_name,
            category = dto.category,
            data = dto.data,
            priority = dto.priority,
            completed = dto.completed
        )
        return taskService.create(newTask)
    }
}

