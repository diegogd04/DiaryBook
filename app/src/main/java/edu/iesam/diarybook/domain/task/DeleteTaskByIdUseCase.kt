package edu.iesam.diarybook.domain.task

class DeleteTaskByIdUseCase(private val repository: TaskRepository) {

    operator fun invoke(taskId: String) {
        repository.deleteTaskById(taskId)
    }
}