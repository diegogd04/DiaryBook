package edu.iesam.diarybook.domain

interface ActivityRepository {

    suspend fun getActivityList(): List<Activity>
}