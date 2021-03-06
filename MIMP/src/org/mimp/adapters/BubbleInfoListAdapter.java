package org.mimp.adapters;

import org.mimp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class BubbleInfoListAdapter extends BaseAdapter {

    private Context mContext;
    private BubbleInfoListHeaderAdapter mHeaderAdapter;
    private BubbleInfoListBodyAdapter mBodyAdapter;
    private LayoutInflater mInflater;
    private boolean mExists;
    private String[] mAddress;

    public BubbleInfoListAdapter(Context context, boolean exists) {
        mContext = context;
        mExists = exists;
        mInflater = LayoutInflater.from(mContext);
        mAddress = ((Activity) mContext).getIntent().getStringArrayExtra(
                "address");
    }

    @Override
    public int getCount() {
        String[] pInfoText = mContext.getResources().getStringArray(
                R.array.entries_list_bubble_interactions_body_info);
        return pInfoText.length + 1; // +1 for header
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (position == 0) {
                convertView = mInflater.inflate(R.layout.bubbleinfolistheader,
                        null);
                mHeaderAdapter = new BubbleInfoListHeaderAdapter();
                mHeaderAdapter.pLocation = (TextView) convertView
                        .findViewById(R.id.bubble_interactions_header_location);
                mHeaderAdapter.pDescription = (TextView) convertView
                        .findViewById(R.id.bubble_interactions_header_descr);
                mHeaderAdapter.pMapButton = (ImageButton) convertView
                        .findViewById(R.id.bubble_interactions_header_map);
                mHeaderAdapter.pDirectionButton = (ImageButton) convertView
                        .findViewById(R.id.bubble_interactions_header_directions);

                mHeaderAdapter.pLocation.setText(mAddress[0]);
                if (mAddress.length > 1)
                    mHeaderAdapter.pDescription.setText(mAddress[1]);
                mHeaderAdapter.pMapButton
                        .setOnClickListener((OnClickListener) mContext);
                mHeaderAdapter.pDirectionButton
                        .setOnClickListener((OnClickListener) mContext);

            }
            else {
                convertView = mInflater.inflate(R.layout.bubbleinfolistbody,
                        null);
                mBodyAdapter = new BubbleInfoListBodyAdapter();
                mBodyAdapter.pInfoTextView = (TextView) convertView
                        .findViewById(R.id.bubble_interactions_body_info);
                mBodyAdapter.pDescrTextView = (TextView) convertView
                        .findViewById(R.id.bubble_interactions_body_descr);

                if (mExists) {
                    mBodyAdapter.pInfoTextView
                            .setText(mBodyAdapter.pInfoText[position - 1]);
                    mBodyAdapter.pDescrTextView
                            .setText(mBodyAdapter.pDescrText[position - 1]);
                }
                else {
                    mBodyAdapter.pInfoTextView
                            .setText(mBodyAdapter.pInfoTextNew[position - 1]);
                    mBodyAdapter.pDescrTextView
                            .setText(mBodyAdapter.pDescrTextNew[position - 1]);
                }
            }

            convertView.setId(position);
            convertView.setTag(mHeaderAdapter);
        }
        else {
            convertView = parent.findViewById(position);
        }
        return convertView;
    }

    private class BubbleInfoListHeaderAdapter {
        public TextView pLocation;
        public TextView pDescription;
        public ImageButton pMapButton;
        public ImageButton pDirectionButton;
    }

    private class BubbleInfoListBodyAdapter {
        public String[] pInfoTextNew = mContext.getResources().getStringArray(
                R.array.entries_list_bubble_interactions_body_info_new);
        public String[] pInfoText = mContext.getResources().getStringArray(
                R.array.entries_list_bubble_interactions_body_info);
        public String[] pDescrTextNew = mContext.getResources().getStringArray(
                R.array.entries_list_bubble_interactions_body_descr_new);
        public String[] pDescrText = mContext.getResources().getStringArray(
                R.array.entries_list_bubble_interactions_body_descr);
        public TextView pInfoTextView;
        public TextView pDescrTextView;
    }
}
