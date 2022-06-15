package com.example.masterlist.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
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
    private final List<ErrandList> masterList;
    Context context;
    LayoutInflater inflater;
    Controller controller;

    /**
     * Private class stocking objects of a line of the adapter.
     */
    private static class ViewProperties {
        ErrandList masterList;
        boolean isUpdating;
        LinearLayout layMasterList;
        ImageButton btnMore;
        ViewSwitcher switcherEditName;
        EditText edtName;
        ImageButton btnValidateName;
        TextView txtListName;
        TextView txtListDesc;
    }

    /**
     * Adapter used in a ListView showing a list of Master Lists.
     * @param masterLists Master Lists to show.
     * @param context Context.
     */
    public MasterListsLayoutAdapter(List<ErrandList> masterLists, Context context) {
        controller = Controller.getInstance(context);
        this.masterList = masterLists;
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
            viewProperties.btnValidateName = convertView.findViewById(R.id.imgValidateListName);

            viewProperties.isUpdating = false;
            convertView.setTag(viewProperties);
        } else {
            viewProperties = (ViewProperties) convertView.getTag();
        }
        ErrandList currMasterList = masterList.get(position);
        viewProperties.masterList = currMasterList;
        View finalConvertView = convertView;
        viewProperties.btnMore.setOnClickListener(v -> onClickPopUpMenuShowMore(finalConvertView));
        viewProperties.btnValidateName.setOnClickListener(v -> validateNameEdition(finalConvertView));

        viewProperties.txtListName.setText(currMasterList.getName());
        viewProperties.txtListDesc.setText(currMasterList.toString());
        viewProperties.layMasterList.setOnClickListener(v -> {
            Intent intent = new Intent(context, MasterListContent.class);
            context.startActivity(intent);
        });

        return convertView;
    }

    /**
     * Handles what happens when clicking on one the options displayed in the pop menu.
     * @param v
     */
    public void onClickPopUpMenuShowMore(View v) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuRename:
                    ViewProperties vp = ((ViewProperties) v.getTag());
                    // If the view is not already in updating mode
                    if (!vp.isUpdating) {
                        vp.isUpdating = true;
                        vp.edtName.setText(vp.txtListName.getText());
                        vp.switcherEditName.showNext();
                    }
                    break;
                case R.id.menuDelete:
                    showConfirmationDialog(v);
//                    Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
        popupMenu.inflate(R.menu.menu_actions_on_lists);
        popupMenu.show();
    }

    /**
     * Validates the name entered by the user.
     * @param v View
     */
    private void validateNameEdition(View v) {
        ViewProperties viewProperties = ((ViewProperties) v.getTag());
        ViewSwitcher switcher = viewProperties.switcherEditName;
        String name = viewProperties.edtName.getText().toString();
        TextView textView = (TextView) switcher.findViewById(R.id.txtListName); //TODO le faire direct avec la viewprop ?
        if (controller.editMasterListName(viewProperties.masterList, name)) {
            textView.setText(name);
            viewProperties.isUpdating = false;
            switcher.showNext();
            Toast.makeText(context, "Nom sauvegardé", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Erreur: Vérifiez le nom entré", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Shows a confirmation dialog.
     * @param v View
     */
    private void showConfirmationDialog(View v){
        new AlertDialog.Builder(context)
                .setTitle("Confirmation")
                .setMessage("Supprimer la liste ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ViewProperties viewProperties = (ViewProperties)v.getTag();
                        removeList(viewProperties.masterList);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Removes an item from
     * @param listToRemove
     */
    private void removeList(ErrandList listToRemove){
        if(!controller.removeMasterList(listToRemove)){
            Toast.makeText(context, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }


}


