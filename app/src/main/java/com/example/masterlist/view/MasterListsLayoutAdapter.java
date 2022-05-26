package com.example.masterlist.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.masterlist.R;
import com.example.masterlist.controller.Controller;
import com.example.masterlist.model.ErrandList;

import java.util.List;

/**
 * Adapter used to display a Master List.
 */
public class MasterListsLayoutAdapter extends BaseAdapter {
    private List<ErrandList> masterList;
    Context context;
    LayoutInflater inflater;

    Controller controller;

    public MasterListsLayoutAdapter(List<ErrandList> errandList, Context context) {
        controller = Controller.getInstance();
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

        if (convertView == null) {
            viewProperties = new ViewProperties();
            convertView = inflater.inflate(R.layout.master_lists_layout, null);
            viewProperties.txtListName = convertView.findViewById(R.id.txtListName);
            viewProperties.edtName = convertView.findViewById(R.id.edtEditListName);
            viewProperties.txtListDesc = convertView.findViewById(R.id.txtListDesc);
            viewProperties.btnMore = convertView.findViewById(R.id.btnMore);
            viewProperties.layMasterList = convertView.findViewById(R.id.layMasterList);
            viewProperties.switcherEditName = convertView.findViewById(R.id.swcEditListName);
            viewProperties.isUpdating = false;
            convertView.setTag(viewProperties);
        } else {
            viewProperties = (ViewProperties) convertView.getTag();
        }
        ErrandList currMasterList = masterList.get(position);
        viewProperties.masterList = currMasterList;
        View finalConvertView = convertView;
        viewProperties.btnMore.setOnClickListener(v -> onClickPopUpMenuShowMore(finalConvertView));
        viewProperties.txtListName.setText(currMasterList.getName());
        viewProperties.txtListDesc.setText(currMasterList.toString());
        viewProperties.layMasterList.setOnClickListener(v -> {
            Intent intent = new Intent(context, MasterListContent.class);
            context.startActivity(intent);
        });

        return convertView;
    }

    public void changeSwitcher(View v) {
        // Gets the properties of the concerned line
        ViewProperties viewProperties = ((ViewProperties) v.getTag());
        ViewSwitcher switcher = viewProperties.switcherEditName;

        // Updates current update status
        boolean isUpdating = !viewProperties.isUpdating;
        viewProperties.isUpdating = isUpdating;

        // Update complete
        if (!isUpdating) {
            String name = viewProperties.edtName.getText().toString();
            TextView textView = (TextView) switcher.findViewById(R.id.txtListName); //TODO le faire direct avec la viewprop ?
//            Log.d("test", textView.getText().toString());
            controller.editMasterListName(viewProperties.masterList, name);
            textView.setText(name);
            switcher.showNext();
            Toast.makeText(context, "Nom sauvegardé", Toast.LENGTH_SHORT).show();
        } else {
            switcher.showNext();
        }

    }

    public void onClickPopUpMenuShowMore(View v) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuRename:
                        changeSwitcher(v);
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
        });
        popupMenu.inflate(R.menu.menu_actions_on_lists);
        popupMenu.show();
    }

    private static class ViewProperties {
        ErrandList masterList;
        boolean isUpdating;
        LinearLayout layMasterList;
        ImageButton btnMore;
        ViewSwitcher switcherEditName;
        EditText edtName;
        TextView txtListName;
        TextView txtListDesc;
    }
}


