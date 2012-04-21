/**
 * Copyright (C) 2010-2012 Regis Montoya (aka r3gis - www.r3gis.fr)
 * This file is part of CSipSimple.
 *
 *  CSipSimple is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  If you own a pjsip commercial license you can also redistribute it
 *  and/or modify it under the terms of the GNU Lesser General Public License
 *  as an android library.
 *
 *  CSipSimple is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CSipSimple.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.abcvoipsip.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.abcvoipsip.api.SipProfile;
import com.abcvoipsip.ui.account.AccountsChooserListActivity;
import com.abcvoipsip.utils.Log;

public class AccountWidgetConfigure extends AccountsChooserListActivity implements OnItemClickListener {

	private static final String WIDGET_PREFS = "widget_prefs";
	private static final String THIS_FILE = "Widget config";
	private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
		    appWidgetId = extras.getInt(
		            AppWidgetManager.EXTRA_APPWIDGET_ID, 
		            AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		//Result to cancel in case application is quit by user
		Intent cancelResultValue = new Intent();
        cancelResultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                        appWidgetId);
        setResult(RESULT_CANCELED, cancelResultValue);
		
	}
	

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		if(appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
			Cursor c = (Cursor) getAdapter().getItem(position);
			SharedPreferences prefs = getSharedPreferences(WIDGET_PREFS, 0);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putLong(getPrefsKey(appWidgetId), c.getLong(c.getColumnIndex(SipProfile.FIELD_ID)));
            edit.commit();
			
            
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                            appWidgetId);
            setResult(RESULT_OK, resultValue);
            
            AccountWidgetProvider.updateWidget(this);
            
            finish();
		}else {
			Log.w(THIS_FILE, "Invalid widget ID here...");
		}
	}
	
	private static String getPrefsKey(int widgetId) {
		return "widget" + widgetId + "_account";
	}
	
	public static long getAccountForWidget(Context ctx, int widgetId) {
		SharedPreferences prefs = ctx.getSharedPreferences(WIDGET_PREFS, 0);
        return prefs.getLong(getPrefsKey(widgetId), SipProfile.INVALID_ID);
        
	}
	
	public static void deleteWidget(Context ctx, int widgetId) {
		SharedPreferences prefs = ctx.getSharedPreferences(WIDGET_PREFS, 0);
        SharedPreferences.Editor edit = prefs.edit();
		edit.remove(getPrefsKey(widgetId));
		edit.commit();
	}
	
}
