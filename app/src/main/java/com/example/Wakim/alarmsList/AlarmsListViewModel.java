package com.example.Wakim.alarmsList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Wakim.data.Alarm;
import com.example.Wakim.data.AlarmRepository;

import java.util.List;

/**
 *This class uses the AlarmRepository and the Room database to locate the
 * alarms and is used by the AlarmsListFragment to retrieve the Alarm records to show in the RecyclerView.
 */
public class AlarmsListViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;
    private LiveData<List<Alarm>> alarmsLiveData;

    public AlarmsListViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
        alarmsLiveData = alarmRepository.getAlarmsLiveData();
    }

    /**
     * Method used for updating an alarm record
     *
     * @param alarm
     */
    public void update(Alarm alarm) {
        alarmRepository.update(alarm);
    }

    /**
     * Method used for reading the alarm records
     *
     * @return
     */
    public LiveData<List<Alarm>> getAlarmsLiveData() {
        return alarmsLiveData;
    }

    /**
     * Method used for deleting an alarm record
     *
     * @param alarm
     */
    public void delete(Alarm alarm) {
        alarmRepository.delete(alarm);
    }
}
