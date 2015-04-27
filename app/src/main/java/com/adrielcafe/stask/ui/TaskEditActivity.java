package com.adrielcafe.stask.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.adrielcafe.stask.App;
import com.adrielcafe.stask.R;
import com.adrielcafe.stask.model.Task;
import com.marvinlabs.widget.floatinglabel.edittext.FloatingLabelEditText;
import com.orm.SugarRecord;

public class TaskEditActivity extends ActionBarActivity {
    private Task task;

    private FloatingLabelEditText titleView;
    private FloatingLabelEditText descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_task_edit);

        titleView = (FloatingLabelEditText) findViewById(R.id.title);
        descriptionView = (FloatingLabelEditText) findViewById(R.id.description);

        titleView.getInputWidget().setSingleLine();
        descriptionView.getInputWidget().setMaxLines(5);

        if(getIntent().hasExtra(App.EXTRA_TASK_ID)){
            setTitle(R.string.edit_task);
            long taskId = getIntent().getLongExtra(App.EXTRA_TASK_ID, -1);
            task = SugarRecord.findById(Task.class, taskId);
            titleView.setInputWidgetText(task.title);
            descriptionView.setInputWidgetText(task.description);
        } else {
            setTitle(R.string.new_task);
            task = new Task();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_done:
                if(isValid()) {
                    saveTask();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveTask(){
        task.title = titleView.getInputWidgetText().toString();
        task.description = descriptionView.getInputWidgetText().toString();
        task.save();

        setResult(RESULT_OK);
        finish();
    }

    private boolean isValid(){
        if(titleView.getInputWidgetText().toString().isEmpty()){
            Toast.makeText(this, R.string.forgot_title, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}