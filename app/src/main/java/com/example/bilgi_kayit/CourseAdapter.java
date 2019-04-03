package com.example.bilgi_kayit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private Context mCtx;
    private List<Course> courseList;

    public CourseAdapter(Context mCtx, List<Course> courseList) {
        this.mCtx = mCtx;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout,null);
        return   new CourseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        final Course course = courseList.get(i);
        courseViewHolder.btnCourse.setText(course.getName());
        courseViewHolder.txtNote.setText(course.getNote());
        courseViewHolder.btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCourse = new Intent(mCtx,CourseDisplay.class);
                intentCourse.putExtra("course_name",course.getName());
                intentCourse.putExtra("course_size",course.getStudentCount());
                intentCourse.putExtra("course_avg",course.getNoteAvg());
                mCtx.startActivity(intentCourse);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView  txtNote;
        Button btnCourse;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCourse=itemView.findViewById(R.id.dersadi);
            txtNote= itemView.findViewById(R.id.dersnotu);
        }
    }



}