package com.example.Wakim.alarmsList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.Wakim.activities.ScheduleAlarmActivity;
import com.example.Wakim.data.Alarm;
import com.example.Wakim.databinding.FragmentListalarmsBinding;

import org.jetbrains.annotations.NotNull;

/**
 * This class displays all of the Alarms in a RecyclerView, which includes information about the Alarm timings,
 * the alarm title, whether it is repeating or not, which days of the week the alarm will sound,
 * and a Toggle that indicates whether the alarm is active or not.
 */
public class AlarmsListFragment extends Fragment implements OnManageListener {
    private AlarmRecyclerViewAdapter alarmRecyclerViewAdapter;
    private AlarmsListViewModel alarmsListViewModel;
    private FragmentListalarmsBinding binding;

    /**
     * This method obtains the Alarm records for display in the RecyclerView by utilizing an AlarmsListViewModel that
     * searches the AlarmRepository and a Room database for the alarms. When retrieving the Alarms data, an observer is added so that the
     * RecyclerView is automatically refreshed if the Alarms data in the Room database changes.
     *
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Context)} and before
     * {@link #onCreateView } w(LayoutInflater, ViewGroup, Bundle)}.
     *
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, add a {@link LifecycleObserver} on the
     * activity's Lifecycle, removing it when it receives the
     * {@link androidx.lifecycle.LifecycleService} callback.
     *
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alarmRecyclerViewAdapter = new AlarmRecyclerViewAdapter(this);
        alarmsListViewModel = ViewModelProviders.of(this).get(AlarmsListViewModel.class);
        alarmsListViewModel.getAlarmsLiveData().observe(this, alarms -> {
            if (alarms != null) {
                alarmRecyclerViewAdapter.setAlarms(alarms);
            }
        });
    }


    /**
     * This method navigates the user to the CreateAlarmFragment if the user selects the button to add an alarm.
     *
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null. This will be called between
     * {@link #onCreate(Bundle)} and {@link #onViewCreated(View, Bundle)}.
     * <p>A default View can be returned by calling  t(int)} in your
     * constructor. Otherwise, this method returns null.
     *
     * <p>It is recommended to <strong>only</strong> inflate the layout in this method and move
     * logic that operates on the returned View to {@link #onViewCreated(View, Bundle)}.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentListalarmsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentListalarmsRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentListalarmsRecylerView.setAdapter(alarmRecyclerViewAdapter);

        binding.fragmentListalarmsAddAlarm.setOnClickListener(v -> startScheduleAlarmActivity());

        return view;
    }

    /**
     *  This method opens the ScheduleAlarm Activity
     */
    private void startScheduleAlarmActivity() {
        startActivity(new Intent(getContext(), ScheduleAlarmActivity.class));
    }

    /**
     * This method returns the view when released
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * This method is has a toggle listener that, depending on the state of the alarm, will either schedule or cancel the alarm.
     *
     * @param alarm
     */
    @Override
    public void onToggle(Alarm alarm) {
        if (alarm.isStarted()) {
            alarm.cancelAlarm(requireContext());
            alarmsListViewModel.update(alarm);
        } else {
            alarm.schedule(requireContext());
            alarmsListViewModel.update(alarm);
        }
    }

    /**
     * This method deletes an alarm
     *
     * @param alarm
     */
    @Override
    public void onDelete(Alarm alarm) {
        alarm.cancelAlarm(requireContext());
        alarmsListViewModel.delete(alarm);;
    }

    /**
     * This method update an alarm by reopening an a
     *
     * @param alarm
     */
    @Override
    public void onEdit(Alarm alarm) {
        startScheduleAlarmActivity(alarm);
    }

    /**
     * This method update an alarm by reopening the ScheduleAlarm Activity of the specific alarmId
     *
     * @param alarm
     */
    private void startScheduleAlarmActivity(Alarm alarm) {
        Intent intent = new Intent(getContext(), ScheduleAlarmActivity.class);
        intent.putExtra("alarmId", alarm.getAlarmId());
        startActivity(intent);
    }
}
