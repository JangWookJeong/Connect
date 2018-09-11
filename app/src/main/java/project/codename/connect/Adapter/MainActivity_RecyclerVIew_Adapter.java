package project.codename.connect.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_RecyclerVIew_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List list = new ArrayList();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        return null;
    }/////onCreateViewHolder

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }//////onBindViewHolder

    @Override
    public int getItemCount() {
        return list.size();
    }/////getItemCount
}////
