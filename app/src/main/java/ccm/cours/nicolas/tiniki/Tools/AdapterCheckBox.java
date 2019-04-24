package ccm.cours.nicolas.tiniki.Tools;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import ccm.cours.nicolas.tiniki.R;

public class AdapterCheckBox extends RecyclerView.Adapter<AdapterCheckBox.ViewHolderCheckBox> {

    private ArrayList<String> collectionChoix;

    @Override
    public ViewHolderCheckBox onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckBox cb = (CheckBox) LayoutInflater.from(parent.getContext()).inflate(R.layout.descripteur_check_box, parent, false);
        ViewHolderCheckBox vh = new ViewHolderCheckBox(cb);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderCheckBox holder, int position) {
        holder.mCheckBox.setText(collectionChoix.get(position));
    }

    @Override
    public int getItemCount() {
        return collectionChoix.size();
    }

    public static class ViewHolderCheckBox extends RecyclerView.ViewHolder{
        public CheckBox mCheckBox;
        public ViewHolderCheckBox(CheckBox cb){
            super(cb);
            mCheckBox = cb;
        }
    }

    public AdapterCheckBox(ArrayList<String> data){
        collectionChoix = data;
    }

    public String getChoixAvecPosition(int position){

        return collectionChoix.get(position);

    }

}
