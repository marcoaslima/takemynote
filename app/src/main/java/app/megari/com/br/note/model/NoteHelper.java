package app.megari.com.br.note.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import app.megari.com.br.note.model.table.NoteTable;

/**
 * Created by marck on 03/09/2016.
 */
public class NoteHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteTable.TABLE_NAME + " (" +
                    NoteTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
                    COMMA_SEP + NoteTable.COLUMN_NAME_TITLE + TEXT_TYPE +
                    COMMA_SEP + NoteTable.COLUMN_NAME_CONTENT + TEXT_TYPE +
                    COMMA_SEP + NoteTable.COLUMN_NAME_TRASH + TEXT_TYPE +
                    COMMA_SEP + NoteTable.COLUMN_NAME_ACTIVE + TEXT_TYPE +
                    COMMA_SEP + NoteTable.COLUMN_NAME_COLOR + TEXT_TYPE +" )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteTable.TABLE_NAME;

    public NoteHelper(Context context) {
        super(context, Database.DB_NAME, null, Database.DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static Long Insert(Context context, Note note){
        NoteHelper mDbHelper = new NoteHelper(context);


            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(NoteTable.COLUMN_NAME_ACTIVE, note.getAtivo());
            values.put(NoteTable.COLUMN_NAME_COLOR, note.getCor());
            values.put(NoteTable.COLUMN_NAME_CONTENT, note.getDescricao());
            values.put(NoteTable.COLUMN_NAME_TITLE, note.getTitulo());
            values.put(NoteTable.COLUMN_NAME_TRASH, note.getLixeira());

            long newRowId = (long)0;

        try {
            newRowId = db.insert(
                    NoteTable.TABLE_NAME,
                    null,
                    values);
        }catch(Exception ex){
            ex.printStackTrace();
        }

            return newRowId;

    }

    public static ArrayList<Note> SelectNote(Context context){
        NoteHelper mDbHelper = new NoteHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                NoteTable._ID,
                NoteTable.COLUMN_NAME_TITLE,
                NoteTable.COLUMN_NAME_COLOR,
                NoteTable.COLUMN_NAME_TRASH,
                NoteTable.COLUMN_NAME_ACTIVE,
                NoteTable.COLUMN_NAME_CONTENT,
        };

        String sortOrder =    NoteTable._ID + " DESC";

        Cursor c = db.query(
                NoteTable.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<Note> notes = new ArrayList<>();

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {


                    Boolean ativo = Boolean.parseBoolean(c.getString(c.getColumnIndex(NoteTable.COLUMN_NAME_ACTIVE)));
                    Integer codigo = c.getInt(c.getColumnIndex(NoteTable.COLUMN_NAME_ENTRY_ID));
                    String titulo = c.getString(c.getColumnIndex(NoteTable.COLUMN_NAME_TITLE));
                    String descricao = c.getString(c.getColumnIndex(NoteTable.COLUMN_NAME_CONTENT));
                    String cor = c.getString(c.getColumnIndex(NoteTable.COLUMN_NAME_COLOR));
                    Boolean lixeira = Boolean.parseBoolean(c.getString(c.getColumnIndex(NoteTable.COLUMN_NAME_TRASH)));

                    notes.add(new Note(codigo, titulo, descricao, lixeira, ativo, cor));
                }while (c.moveToNext());
            }
        }
        //c.close();
        db.close();
        mDbHelper.close();
        return notes;
    }

    public static void Delete(Context context, Integer Codigo){
        NoteHelper mDbHelper = new NoteHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = NoteTable.COLUMN_NAME_ENTRY_ID;

        String[] selectionArgs = { String.valueOf(Codigo) };
        db.delete(NoteTable.TABLE_NAME, selection + "=" + selectionArgs[0], null);
    }

}
