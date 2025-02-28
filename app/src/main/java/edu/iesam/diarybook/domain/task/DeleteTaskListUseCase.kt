package edu.iesam.diarybook.domain.task

class DeleteTaskListUseCase(private val repository: TaskRepository) {

    operator fun invoke(tasks: List<Task>) {
        repository.deleteTaskList(tasks)
    }
}