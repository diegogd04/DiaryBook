package edu.iesam.diarybook.domain.task

class SaveTaskListUseCase(private val repository: TaskRepository) {

    operator fun invoke(tasks: List<Task>) {
        repository.saveTaskList(tasks)
    }
}