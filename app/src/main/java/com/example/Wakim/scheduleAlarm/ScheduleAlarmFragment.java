package com.example.Wakim.scheduleAlarm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.Wakim.data.Alarm;
import com.example.Wakim.databinding.FragmentSchedulealarmBinding;


/**
 * This class follows the MVVM design pattern to insert instances of the Alarm model into
 * the Room Database via a ViewModel and a Repository when a user captures submits a new alarm
 */

public class ScheduleAlarmFragment extends Fragment {
    private FragmentSchedulealarmBinding binding;
    private ScheduleAlarmViewModel createAlarmViewModel;
    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Context)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     *
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, add a {@link LifecycleObserver} on the
     * activity's Lifecycle, removing it when it receives the
     * {@link androidx.lifecycle.Lifecycle.State#CREATED} callback.
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

        createAlarmViewModel = ViewModelProviders.of(this).get(ScheduleAlarmViewModel.class);
    }


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null. This will be called between
     * {@link #onCreate(Bundle)} and {@link #onViewCreated(View, Bundle)}.
     * <p>A default View can be returned by calling {@link Fragment()} in your
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
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSchedulealarmBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        int alarmId = getActivity().getIntent().getIntExtra("alarmId", -1);
        createAlarmViewModel.getAlarm(alarmId).observe(getViewLifecycleOwner(), alarm -> {
            TimePickerUtil.setTimePickerHour(binding.timePicker, alarm.getHour());
            TimePickerUtil.setTimePickerMinute(binding.timePicker, alarm.getMinute());
            binding.fragmentCreatealarmTitle.setText(alarm.getTitle());
            binding.fragmentCreatealarmDescription.setText(alarm.getDescription());
            binding.fragmentCreatealarmDescription.setText(alarm.getDescription());
            binding.fragmentCreatealarmRecurring.setChecked(alarm.isRecurring());
            binding.fragmentCreatealarmRecurringOptions.setVisibility(alarm.isRecurring() ? View.VISIBLE : View.GONE);
            binding.fragmentCreatealarmRecurring.setOnCheckedChangeListener((v, isChecked) -> {
                alarm.setRecurring(isChecked);
                binding.fragmentCreatealarmRecurringOptions.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                bindWeekDays(alarm);
            });
            bindWeekDays(alarm);
            binding.onMon.setOnCheckedChangeListener((v, isChecked) -> alarm.setMonday(isChecked));
            binding.onTue.setOnCheckedChangeListener((v, isChecked) -> alarm.setTuesday(isChecked));
            binding.onWed.setOnCheckedChangeListener((v, isChecked) -> alarm.setWednesday(isChecked));
            binding.onThu.setOnCheckedChangeListener((v, isChecked) -> alarm.setThursday(isChecked));
            binding.onFri.setOnCheckedChangeListener((v, isChecked) -> alarm.setFriday(isChecked));
            binding.onSat.setOnCheckedChangeListener((v, isChecked) -> alarm.setSaturday(isChecked));
            binding.onSun.setOnCheckedChangeListener((v, isChecked) -> alarm.setSunday(isChecked));
        });

        binding.fragmentCreatealarmScheduleAlarm.setOnClickListener(v -> {
            scheduleAlarm();
            getActivity().finish();
        });

        binding.fragmentCreatealarmTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        binding.fragmentCreatealarmDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        return view;
    }

    /**
     * Method that hides the keyboard whenever the user click outside the textview
     *
     * @param view
     */
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     *Method that takes the user input which will be used for the ROOM database
     */
    private void scheduleAlarm() {
        Alarm alarm = createAlarmViewModel.save(TimePickerUtil.getTimePickerHour(binding.timePicker),
                TimePickerUtil.getTimePickerMinute(binding.timePicker),
                binding.fragmentCreatealarmTitle.getText().toString(),
                binding.fragmentCreatealarmDescription.getText().toString());
        alarm.schedule(requireContext());
    }

    /**
     * Method the binds the weekdays checkbox
     *
     * @param alarm
     */
    private void bindWeekDays(Alarm alarm) {
        binding.onMon.setChecked(alarm.isMonday());
        binding.onTue.setChecked(alarm.isTuesday());
        binding.onWed.setChecked(alarm.isWednesday());
        binding.onThu.setChecked(alarm.isThursday());
        binding.onFri.setChecked(alarm.isFriday());
        binding.onSat.setChecked(alarm.isSaturday());
        binding.onSun.setChecked(alarm.isSunday());
    }
}