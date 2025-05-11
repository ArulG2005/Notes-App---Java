package com.logicfirst.notesapp;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder>{
    Context context;
    RealmResults<Note> notelist;

    public MyAdaptor(Context context, RealmResults<Note> notelist) {
        this.context = context;
        this.notelist = notelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context,R.layout.item_view,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note=notelist.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        String date= DateFormat.getDateInstance().format(note.createdAt);
        holder.time.setText(date);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu=new PopupMenu(context,view);
                popupMenu.getMenu().add("DELETE");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("DELETE")){
                            Realm realm=Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                        }
                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleoutput);
            description = itemView.findViewById(R.id.descriptionoutput);
            time = itemView.findViewById(R.id.timeoutput);
        }
    }
}
