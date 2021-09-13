package com.example.tasktimer;

import android.provider.BaseColumns;

public class TasksContract {
    public static final String TABLE_NAME = "Tasks";

    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String NAME = "Name";
        public static final String DESCRIPTION = "description";
        public static final String SORT_ORDER = "SortOrder";

        private Columns() {
            //private constructor to prevent instantiation
        }
    }
}
