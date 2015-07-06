package com.egm.alejandro.tiempo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alejandro on 05/06/2015.
 */
public class adaptador extends BaseAdapter {

    private ArrayList<List> List;
    private LayoutInflater inflater;
    private Context contexto;


    public adaptador(ArrayList<List> list, Context cont)
    {
        this.List = list;
        this.inflater = LayoutInflater.from(cont);
        this.contexto = cont;

    }

    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return List.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= inflater.inflate(R.layout.layout_clima, null);
        List list=List.get(position);
        ImageView img = (ImageView) convertView.findViewById(R.id.image);
        TextView dia = (TextView) convertView.findViewById(R.id.dia);
        TextView estado = (TextView) convertView.findViewById(R.id.estado);
        TextView temperatura = (TextView) convertView.findViewById(R.id.temperatura);

        img.setImageDrawable(contexto.getResources().getDrawable(list.getImage()));
        dia.setText(list.getDia());
        estado.setText(list.getEstado());
        temperatura.setText(list.getTemperatura());

        return convertView;
    }
}
