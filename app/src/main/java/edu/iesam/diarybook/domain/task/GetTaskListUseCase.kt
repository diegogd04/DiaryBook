package edu.iesam.diarybook.domain.task

class GetTaskListUseCase(private val repository: TaskRepository) {

    operator fun invoke(): List<Task> {
        return repository.getTaskList()
    }
}