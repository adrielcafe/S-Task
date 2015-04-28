package com.adrielcafe.stask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adrielcafe.stask.R;
import com.adrielcafe.stask.model.Task;
import com.adrielcafe.stask.ui.TasksActivity;
import com.daimajia.swipe.SwipeLayout;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private static TasksActivity activity;

    public TaskAdapter(Context context, List<Task> cellList) {
        super(context, R.layout.list_item_task, cellList);
        activity = (TasksActivity) getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_task, null);
            viewHolder = new ViewHolder();
            viewHolder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipeLayout);
            viewHolder.complete = convertView.findViewById(R.id.complete);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.show = (ImageView) convertView.findViewById(R.id.show);
            viewHolder.edit = (ImageView) convertView.findViewById(R.id.edit);
            viewHolder.delete = (ImageView) convertView.findViewById(R.id.delete);
            viewHolder.toggleStatus = (ImageView) convertView.findViewById(R.id.toggleStatus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(task.title);
        if(task.done){
            viewHolder.complete.setBackgroundColor(getContext().getResources().getColor(R.color.accent));
        } else {
            viewHolder.complete.setBackgroundColor(getContext().getResources().getColor(R.color.gray));
        }

        setListeners(viewHolder, task);

        return convertView;
    }

    private void setListeners(ViewHolder viewHolder, final Task task){
        final SwipeLayout swipeLayout = viewHolder.swipeLayout;
        final View statusView = viewHolder.complete;
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeLayout.toggle(true);
            }
        });
        viewHolder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showTask(task);
                swipeLayout.toggle(true);
            }
        });
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.editTask(task);
                swipeLayout.toggle(true);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.deleteTask(task);
                swipeLayout.toggle(true);
            }
        });
        viewHolder.toggleStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.toggleTaskStatus(task, statusView);
                swipeLayout.toggle(true);
            }
        });
    }

    private static class ViewHolder {
        SwipeLayout swipeLayout;
        View complete;
        TextView title;
        ImageView show;
        ImageView edit;
        ImageView delete;
        ImageView toggleStatus;
    }
}