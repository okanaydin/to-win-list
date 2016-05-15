package xyz.oguzcelik.towinlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by Cynapsis on 5/15/2016.
 */
public class ReportAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    List<Report> data = null;
    public ReportAdapter(Context context, int resource, List<Report> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data =  objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReportHolder reportHolder = null;
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            reportHolder = new ReportHolder();
            reportHolder.image = (ImageView)row.findViewById(R.id.showImage);
            reportHolder.description = (TextView)row.findViewById(R.id.showDescriptionTxt);

            row.setTag(reportHolder);
        } else {
            reportHolder = (ReportHolder) row.getTag();
        }

        Report report = data.get(position);
        reportHolder.description.setText(report.description);
        Bitmap image = ReportActivity.decodeSampledBitmapFromFile(report.getPhotoUrl(),750,425);
        reportHolder.image.setImageBitmap(image);

        return row;
    }
    static class ReportHolder {
        ImageView image;
        TextView description;
    }
}
