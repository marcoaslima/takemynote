package app.megari.com.br.note.model.table;

import android.provider.BaseColumns;

/**
 * Created by marck on 03/09/2016.
 */
public class ReminderTable implements BaseColumns {
    public static final String TABLE_NAME = "tb_reminder";
    public static final String COLUMN_NAME_ENTRY_ID = "cd_reminder";
    public static final String COLUMN_NAME_TITLE = "nm_reminder";
    public static final String COLUMN_NAME_CONTENT = "dt_reminder";
    public static final String COLUMN_NAME_ACTIVE = "ic_active";
}
