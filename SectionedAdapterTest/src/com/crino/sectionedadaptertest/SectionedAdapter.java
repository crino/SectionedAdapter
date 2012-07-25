package com.crino.sectionedadaptertest;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SectionedAdapter extends BaseAdapter {

	protected static final int TYPE_HEADER_VIEW = 0;
	protected static final int TYPE_ITEM_VIEW = 1;
	
	protected static final int TYPE_HEADER = 0;
	protected static final int TYPE_ITEM = 1;
	
	private int mItemsInSections[] = null;
	private Context mContext;
	
	public SectionedAdapter(Context context) {
		mContext = context;
	}

	protected int getNumberOfSections() {
		return 0;
	}
	
	protected int getNumberOfItemsInSection(int section) {
		return 0;
	}
	
	public Object getItemInSection(int section, int position) {
		return null;
	}
	
	protected int getViewTypeForSection(int section) {
		return TYPE_HEADER_VIEW;
	}
	
	protected int getViewTypeForItemInSection(int section, int position) {
		return TYPE_ITEM_VIEW;
	}
	
	protected int getNumberOfViewTypeForSections() {
		return 1;
	}
	
	protected int getNumberOfViewTypeForItems() {
		return 1;
	}
	
	protected View newSectionView(Context context, int section, ViewGroup parent) {
		return null;
	}
	
	protected void bindSectionView(Context context, int section, View convertView) {
	}
	
	protected View newItemViewInSection(Context context, int section, int position, ViewGroup parent) {
		return null;
	}
	
	protected void bindItemViewInSection(Context context, int section, int position, View convertView) {
	}
	
	public int getItemType(int position) {
		for (int section = 0; section < mItemsInSections.length; section++) {
			int size = mItemsInSections[section] + 1;
			if (position == 0) {
				return TYPE_HEADER;
			}
			if (position < size) {
				return TYPE_ITEM;
			}
			position -= size;
		}
		return IGNORE_ITEM_VIEW_TYPE;
	}
	
	@Override
	public int getItemViewType(int position) {
		for (int section = 0; section < mItemsInSections.length; section++) {
			int size = mItemsInSections[section] + 1;
			if (position == 0) {
				return getViewTypeForSection(section);
			}
			if (position < size) {
				return getViewTypeForItemInSection(section, (position - 1));
			}
			position -= size;
		}
		return IGNORE_ITEM_VIEW_TYPE;
	} 
	
	@Override
	public int getViewTypeCount() {
		int count = getNumberOfViewTypeForSections();
		count += getNumberOfViewTypeForItems();
		return count;
	}
	
	@Override
	public int getCount() {
		int count = 0;
		int sections = getNumberOfSections();
		mItemsInSections = new int[sections];
		
		count += sections;
		for (int section = 0; section < sections; section++) {
			int items = getNumberOfItemsInSection(section);
			mItemsInSections[section] = items;
			count += items;
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		for (int section = 0; section < mItemsInSections.length; section++) {
			int size = mItemsInSections[section] + 1;
			if (position == 0) {
				// section
				if (convertView == null) {
					convertView = newSectionView(mContext, section, parent);
					convertView.setTag("section");
//					android.util.Log.d("LIST", "Created a new view for section: " + section);
				}
				bindSectionView(mContext, section, convertView);
				return convertView;
			}
			if (position < size) {
				// item
				if (convertView == null) {
					convertView = newItemViewInSection(mContext, section, (position - 1), parent);
					convertView.setTag("item");
//					android.util.Log.d("LIST", "Created a new view for item at " + (position - 1) + " in section: " + section);
				}
				bindItemViewInSection(mContext, section, (position - 1), convertView);	
				return convertView;
			}
			position -= size;
		}
		return null;
	}

}
