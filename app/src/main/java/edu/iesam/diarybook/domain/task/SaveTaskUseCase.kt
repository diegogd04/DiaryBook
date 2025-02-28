package edu.iesam.diarybook.domain.task

class SaveTaskUseCase(private val repository: TaskRepository) {

    operator fun invoke(task: Task) {
        repository.saveTask(task)
    }
}