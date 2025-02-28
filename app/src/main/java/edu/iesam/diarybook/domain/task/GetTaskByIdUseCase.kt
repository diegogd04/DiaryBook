package edu.iesam.diarybook.domain.task

class GetTaskByIdUseCase(private val repository: TaskRepository) {

    operator fun invoke(taskId: String): Task {
        return repository.getTaskById(taskId)
    }
}