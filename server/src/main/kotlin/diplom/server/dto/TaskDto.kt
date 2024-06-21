package diplom.server.dto

import java.time.LocalDate
import java.util.Date

class TaskDto (
    val task_id: Int? = null,
    val user_id: Int,
    val task_name: String,
    val category: String? = null,
    val data: LocalDate,
    val priority: Int? = null,
    val completed: Boolean,
)