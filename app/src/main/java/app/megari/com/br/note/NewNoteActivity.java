package app.megari.com.br.note;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.megari.com.br.note.model.Note;
import app.megari.com.br.note.model.NoteHelper;

public class NewNoteActivity extends AppCompatActivity {

    private Button btnInsertNote;
    private EditText txtTituloNota;
    private EditText txtConteudoNota;
    private TextView linkCancelarInserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        btnInsertNote  = (Button) findViewById(R.id.btnInsertNote);
        txtConteudoNota = (EditText) findViewById(R.id.txtConteudoNota);
        txtTituloNota = (EditText) findViewById(R.id.txtTituloNota);
        linkCancelarInserir = (TextView) findViewById(R.id.linkCancelarInserir);


        btnInsertNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note(0, txtTituloNota.getText().toString(), txtConteudoNota.getText().toString(), false,  true, "#");
                long resultado = NoteHelper.Insert(NewNoteActivity.this, note);

                Snackbar.make(view, resultado>0?"Nota salva com sucesso":"Falha ao salvar nota", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        linkCancelarInserir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
