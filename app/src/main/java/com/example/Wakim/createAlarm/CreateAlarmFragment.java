package com.example.Wakim.createAlarm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;

import com.example.Wakim.data.Alarm;
import com.example.Wakim.R;
import com.example.Wakim.databinding.FragmentCreatealarmBinding;

import java.util.Random;


/**
 * This class follows the MVVM design pattern to insert instances of the Alarm model into
 * the Room Database via a ViewModel and a Repository when a user captures submits a new alarm
 */

public class CreateAlarmFragment extends Fragment {
    private FragmentCreatealarmBinding binding;
    private CreateAlarmViewModel createAlarmViewModel;
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

        createAlarmViewModel = ViewModelProviders.of(this).get(CreateAlarmViewModel.class);
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
        binding = FragmentCreatealarmBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentCreatealarmRecurring.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.fragmentCreatealarmRecurringOptions.setVisibility(View.VISIBLE);
            } else {
                binding.fragmentCreatealarmRecurringOptions.setVisibility(View.GONE);
            }
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
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(binding.fragmentCreatealarmTimePicker),
                TimePickerUtil.getTimePickerMinute(binding.fragmentCreatealarmTimePicker),
                binding.fragmentCreatealarmTitle.getText().toString(),
                binding.fragmentCreatealarmDescription.getText().toString(),
                System.currentTimeMillis(),
                true,
                binding.fragmentCreatealarmRecurring.isChecked(),
                binding.fragmentCreatealarmCheckMon.isChecked(),
                binding.fragmentCreatealarmCheckTue.isChecked(),
                binding.fragmentCreatealarmCheckWed.isChecked(),
                binding.fragmentCreatealarmCheckThu.isChecked(),
                binding.fragmentCreatealarmCheckFri.isChecked(),
                binding.fragmentCreatealarmCheckSat.isChecked(),
                binding.fragmentCreatealarmCheckSun.isChecked()
        );
        createAlarmViewModel.insert(alarm);

        alarm.schedule(requireContext());
    }
}