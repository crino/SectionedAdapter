package com.crino.sectionedadaptertest;

import com.example.listviewtest.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import android.app.ListActivity;
import android.content.Context;

public class MainActivity extends ListActivity {
	
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        MySectionedAdapter adpt = new MySectionedAdapter(this);
        getListView().setAdapter(adpt);
        
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				SectionedAdapter adapter = (SectionedAdapter)adapterView.getAdapter();
				
				int section = adapter.getSectionForPosition(position);
				int row = adapter.getRowForPosition(position);
				
				if (row == SectionedAdapter.INVALID_ROW) {
					Toast.makeText(MainActivity.this, "You clicked section " + section, Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(MainActivity.this, "You clicked row " + row + " in section " + section, Toast.LENGTH_SHORT).show();
				}
			}
        	
		});
    }

    private class MySectionedAdapter extends SectionedAdapter {

		public MySectionedAdapter(Context context) {
			super(context);
		}
    
		@Override
		protected int getNumberOfSections() {
			return 3;
		}
		
		@Override
		protected int getNumberOfRowsInSection(int section) {
			switch (section) {
				case 0:
					return 4;
				case 1:
					return 3;					
				default:
					return 6;
			}
		}
		
		@Override
		public Object getItemInSectionAndRow(int section, int row) {
			return null;
		}

		@Override
		protected View newSectionView(Context context, int section, ViewGroup parent) {
			return ((LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header, parent, false);
		}
		
		@Override
		protected void bindSectionView(Context context, int section, View convertView) {
			((TextView)convertView.findViewById(R.id.list_header_title)).setText("Section " + section);
		}
		
		@Override
		protected View newRowViewInSection(Context context, int section, int row, ViewGroup parent) {
			return ((LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item, parent, false);
		}
		
		@Override
		protected void bindRowViewInSection(Context context, int section, int row, View convertView) {
			((TextView)convertView.findViewById(R.id.list_item_title)).setText("Item " + row + " in section " + section);
		}

    }
}
