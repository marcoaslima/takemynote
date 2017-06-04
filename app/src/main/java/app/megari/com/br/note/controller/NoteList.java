package app.megari.com.br.note.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.megari.com.br.note.HomeActivity;
import app.megari.com.br.note.MainActivity;
import app.megari.com.br.note.R;
import app.megari.com.br.note.model.Note;
import app.megari.com.br.note.model.NoteHelper;

/**
 * Created by marck on 03/09/2016.
 */
public class NoteList extends BaseAdapter {
    public ArrayList<Note> notes;
    Context context;
    HomeActivity home;

    private static LayoutInflater inflater=null;

    public NoteList(HomeActivity mainActivity, ArrayList<Note> notes) {
        this.notes = notes;
        context=mainActivity;
        home = mainActivity;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        String txtTituloNotaLista;
        String txtConteudoNotaLista;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.note_list, null);

        ((TextView) rowView.findViewById(R.id.txtTituloNotaLista)).setText(notes.get(position).getTitulo());
        ((TextView) rowView.findViewById(R.id.txtConteudoNotaLista)).setText(notes.get(position).getDescricao());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                final CharSequence[] items = {"Excluir", "Cancelar"};

                builder.setTitle("Opção");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        Integer codigo = notes.get(position).getCodigo();

                        switch (item) {
                            case 0:
                                NoteHelper.Delete(context, codigo);
                                Snackbar.make(v, "Nota excluída com sucesso", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                Reload();

                                break;
                        }
                    }

                });

                AlertDialog alert = builder.create();

                alert.show();
            }
        });
        return rowView;
    }

    public void Reload(){
        notifyDataSetChanged();
        this.home.CarregaNotas();
    }

}
