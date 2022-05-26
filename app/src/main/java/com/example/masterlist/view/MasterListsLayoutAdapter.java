package com.example.masterlist.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.masterlist.R;
import com.example.masterlist.model.ErrandList;

import java.util.List;

/**
 * Adapter used to display a Master List.
 */
public class MasterListsLayoutAdapter extends BaseAdapter implements PopupMenu.OnMenuItemClickListener{
    private List<ErrandList> masterList;
    Context context;
    LayoutInflater inflater;

    public MasterListsLayoutAdapter(List<ErrandList> errandList, Context context) {
        this.masterList = errandList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return masterList.size();
    }

    @Override
    public Object getItem(int position) {
        return masterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewProperties viewProperties;
        if(convertView == null){
            viewProperties = new ViewProperties();
            convertView = inflater.inflate(R.layout.master_lists_layout, null);
            viewProperties.txtListName = convertView.findViewById(R.id.txtListName);
            viewProperties.txtListDesc = convertView.findViewById(R.id.txtListDesc);
            viewProperties.btnMore = convertView.findViewById(R.id.btnMore);
            viewProperties.layMasterList = convertView.findViewById(R.id.layMasterList);
            convertView.setTag(viewProperties);
        }
        else{
            viewProperties = (ViewProperties) convertView.getTag();
        }

        viewProperties.btnMore.setOnClickListener(v-> onClickPopUpMenuShowMore(viewProperties.btnMore));
        viewProperties.txtListName.setText(masterList.get(position).getName());
        viewProperties.txtListDesc.setText(masterList.get(position).toString());
        viewProperties.layMasterList.setOnClickListener(v -> {
            Intent intent = new Intent(context, MasterListContent.class);
            context.startActivity(intent);
        });

        return convertView;
    }

    public void onClickPopUpMenuShowMore(View v){
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_actions_on_lists);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuRename:
                Toast.makeText(context, "Rename", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuDelete:
                Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private static class ViewProperties {
        LinearLayout layMasterList;
        ImageButton btnMore;
        TextView txtListName;
        TextView txtListDesc;
    }
}


