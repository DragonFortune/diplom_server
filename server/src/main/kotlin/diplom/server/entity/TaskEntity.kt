package diplom.server.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.Date

@Entity
@Table(name = "tasks")
class TaskEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val taskId: Int = 0,
    val userId: Int,
    val taskName: String,
    val category: String = "",
    val data: LocalDate = LocalDate.now(),
    val priority: Int = 0,
    val completed: Boolean,
)
