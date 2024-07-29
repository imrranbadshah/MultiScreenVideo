package com.test.plugins.multiscreenactivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "MultiscreenActivity")
public class MultiscreenActivityPlugin extends Plugin {

    private MultiscreenActivity implementation = new MultiscreenActivity();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void openSplitScreen(PluginCall call) {
        String video1Url = call.getString("video1Url");
        String video2Url = call.getString("video2Url");
        Log.i("entering","the onsplit method");
        Context context = getActivity().getApplicationContext();
        Intent intent = new Intent(context, SplitScreenActivity.class);
        intent.putExtra("video1Url", video1Url);
        intent.putExtra("video2Url", video2Url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
        call.resolve();
    }
}
