package com.example.Wakim.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * This interface provide methods that your app can use to query, update, insert, and delete data in the database.
 */
@Dao
public interface AlarmDao {
    @Insert
    void insert(Alarm alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Query("SELECT * FROM alarm_table ORDER BY hour, minute ASC")
    LiveData<List<Alarm>> getAlarms();

    @Query("SELECT * FROM alarm_table WHERE alarmId = :alarmId")
    LiveData<Alarm> getAlarm(int alarmId);

    @Update
    void update(Alarm alarm);

    @Delete
    void delete(Alarm alarm);
}
