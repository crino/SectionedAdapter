package com.crino.sectionedadaptertest;

import com.example.listviewtest.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.app.ListActivity;
import android.content.Context;

public class MainActivity extends ListActivity {

	TextView tv;
	
	
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        
        MySectionedAdapter adpt = new MySectionedAdapter(this);
        getListView().setAdapter(adpt);
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
		protected int getNumberOfItemsInSection(int section) {
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
		public Object getItemInSection(int section, int position) {
			return null;
		}
		
		@Override
		protected int getViewTypeForSection(int section) {
			return TYPE_HEADER_VIEW;
		}
		
		@Override
		protected int getViewTypeForItemInSection(int section, int position) {
			return TYPE_ITEM_VIEW;
		}
		
		@Override
		protected int getNumberOfViewTypeForSections() {
			return 1;
		}
		
		@Override
		protected int getNumberOfViewTypeForItems() {
			return 1;
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
		protected View newItemViewInSection(Context context, int section, int position, ViewGroup parent) {
			return ((LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item, parent, false);
		}
		
		@Override
		protected void bindItemViewInSection(Context context, int section, int position, View convertView) {
			((TextView)convertView.findViewById(R.id.list_item_title)).setText("Item " + position + " in section " + section);
		}

    }
}
