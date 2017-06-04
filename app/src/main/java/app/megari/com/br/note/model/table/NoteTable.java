package app.megari.com.br.note.model.table;

import android.provider.BaseColumns;

/**
 * Created by marck on 03/09/2016.
 */
public class NoteTable implements BaseColumns{
        public static final String TABLE_NAME = "tb_notes";
        public static final String COLUMN_NAME_ENTRY_ID = "_id";
        public static final String COLUMN_NAME_TITLE = "nm_note";
        public static final String COLUMN_NAME_CONTENT = "ds_note";
        public static final String COLUMN_NAME_TRASH = "ic_trash";
        public static final String COLUMN_NAME_ACTIVE = "ic_active";
        public static final String COLUMN_NAME_COLOR = "nm_color";
}
