package edu.iesam.diarybook.presentation.activityForm

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import edu.iesam.diarybook.R
import edu.iesam.diarybook.databinding.FragmentCreateActivityBinding
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.task.domain.Task
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class CreateActivityFragment : Fragment() {

    private var _binding: FragmentCreateActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateActivityViewModel by viewModel()
    private val currentUserId get() = FirebaseAuth.getInstance().currentUser?.uid
    private val event = Event("", "", "", 0, "", "", "", "", false)
    private val task = Task("", "", "", 0, "", false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateActivityBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        toolbarEdit()
        setDate()
        setHour()
    }

    private fun toolbarEdit() {
        binding.toolbar.topAppBar.apply {
            title = getString(R.string.create_activity_title)
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setDate() {
        val editText = binding.createFormEvent.eventDate

        editText.apply {
            isFocusable = false
            setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePicker =
                    DatePickerDialog(requireContext(), { _, yearPick, monthPick, dayPick ->
                        val date = String.format(
                            getString(R.string.date_format),
                            dayPick,
                            monthPick + 1,
                            yearPick
                        )
                        editText.setText(date)
                    }, year, month, day)

                datePicker.show()
            }
        }
    }

    private fun setHour() {
        val editText = binding.createFormEvent.eventHour

        editText.apply {
            isFocusable = false
            setOnClickListener {
                val calendar = Calendar.getInstance()
                val hourDay = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val timePicker = TimePickerDialog(requireContext(), { _, hourPick, minutePick ->
                    val hour = String.format(getString(R.string.hour_format), hourPick, minutePick)
                    editText.setText(hour)
                }, hourDay, minute, true)

                timePicker.show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createForm()
    }

    private fun createForm() {
        binding.toggleGroupButtons.apply {
            check(R.id.button_event_add)
            createSetUpViewEventForm()
            addOnButtonCheckedListener { group, checkedId, isChecked ->
                if (!isChecked) return@addOnButtonCheckedListener
                when (checkedId) {
                    R.id.button_event_add -> createSetUpViewEventForm()
                    R.id.button_task_add -> createSetUpViewTaskForm()
                }
            }
        }
    }

    private fun createSetUpViewEventForm() {
        binding.apply {
            createFormEvent.createFormEvent.visibility = View.VISIBLE
            createFormTask.createFormTask.visibility = View.GONE
            createButton.setOnClickListener {
                val currentTime = System.currentTimeMillis()
                bindDataEvent(currentTime)
                viewModel.createEvent(event)
                findNavController().navigateUp()
            }
        }
    }

    private fun bindDataEvent(time: Long) {
        binding.createFormEvent.apply {
            event.apply {
                title = eventTitle.text.toString()
                description = eventDescription.text.toString()
                duration = eventDuration.text.toString()
                date = eventDate.text.toString()
                hour = eventHour.text.toString()
                userId = currentUserId.toString()
                this.time = time
            }
        }
    }

    private fun createSetUpViewTaskForm() {
        binding.apply {
            createFormEvent.createFormEvent.visibility = View.GONE
            createFormTask.createFormTask.visibility = View.VISIBLE
            createButton.setOnClickListener {
                val currentTime = System.currentTimeMillis()
                bindDataTask(currentTime)
                viewModel.createTask(task)
                findNavController().navigateUp()
            }
        }
    }

    private fun bindDataTask(time: Long) {
        binding.createFormTask.apply {
            task.apply {
                title = taskTitle.text.toString()
                description = taskDescription.text.toString()
                userId = currentUserId.toString()
                this.time = time
            }
        }
    }
}