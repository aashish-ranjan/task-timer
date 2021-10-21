package com.example.tasktimer;

import static com.example.tasktimer.AppProvider.CONTENT_AUTHORITY_URI;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class TasksContract {
    public static final String TABLE_NAME = "Tasks";

    /**
     * The URI to access the Tasks table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static long getTaskId(Uri uri) {
        return ContentUris.parseId(uri);
    }

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
