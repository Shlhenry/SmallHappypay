package com.example.administrator.smallhappypay.tool;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.administrator.smallhappypay.R;

public class DialogUtil {

    public static AlertDialog getAlertDialog(Context context, String msg, String btName) {
        final AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setMessage(msg);
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, btName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.dismiss();
            }
        });
        return alert;
    }

    public static AlertDialog getAlertDialog(Context context, String msg, String btName,
                                             DialogInterface.OnClickListener onclickListener) {
        final AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, btName, onclickListener);
        return alert;
    }

    public static AlertDialog getAlertDialog(Context context, String title, String msg, String btName,
                                             DialogInterface.OnClickListener onclickListener) {
        final AlertDialog alert = new AlertDialog.Builder(context).create();
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setCancelable(false);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, btName, onclickListener);
        return alert;
    }

    public static AlertDialog getAlertDialog(Context context, String title, String msg, String commitName,
                                             String cancelName, DialogInterface.OnClickListener onClick) {
        AlertDialog alert = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(commitName, onClick);
        builder.setNegativeButton(cancelName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert = builder.create();
        return alert;
    }

    public static AlertDialog getAlertDialog(Context context, String title, String msg, String commitName,
                                             String cancelName, DialogInterface.OnClickListener onClick, DialogInterface.OnClickListener oncanClick) {
        AlertDialog alert = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(commitName, onClick);
        builder.setNegativeButton(cancelName, oncanClick);
        alert = builder.create();
        return alert;
    }

    public static AlertDialog getAlertDialog(Context context, String msg, String items[],
                                             DialogInterface.OnClickListener onclickListener) {
        final AlertDialog alert = new AlertDialog.Builder(context).setItems(items, onclickListener).create();
        alert.setTitle(msg);
        alert.setCancelable(true);
        return alert;
    }

    public static AlertDialog getAlertDialog(Context context, String msg, String items[], String btName,
                                             DialogInterface.OnClickListener onclickListener,
                                             DialogInterface.OnClickListener onclickListener1) {
        final AlertDialog alert = new AlertDialog.Builder(context).setItems(items, onclickListener).create();
        alert.setTitle(msg);
        alert.setCancelable(false);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, btName, onclickListener1);
        return alert;
    }

    /**
     * @param context
     * @param view
     * @param cancel  false 外部不可操作
     * @return
     */
    public static Dialog getDialog(Context context, int view, boolean cancel) {
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyleBottom);
        dialog.setCancelable(cancel);
        // dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setGravity(Gravity.CENTER);
        // TODO: 2017/12/4 设置Dialog动画
        // dialog.getWindow().setWindowAnimations(R.style.dialog_anim_style);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(p);
        return dialog;
    }

    /**
     * @param context
     * @param view
     * @param cancel  false 外部不可操作
     * @return
     */
    public static Dialog loadingProgress(Context context, int view, boolean cancel) {
        final Dialog dialog = new Dialog(context, R.style.MyProgressLoading);
        dialog.setCancelable(cancel);
        // dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(p);
        return dialog;
    }

    /**
     * @param context
     * @param view
     * @param cancel  false 外部不可操作
     * @return
     */
    public static Dialog getMDialog(Context context, int view, boolean cancel) {
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyleBottom);
        dialog.setCancelable(cancel);
        // dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(p);
        return dialog;
    }

    /**
     * @param context
     * @param view
     * @param cancel  false 外部不可操作
     * @return
     */
    public static Dialog getFullScreenDialog(Context context, int view, boolean cancel) {
        final Dialog dialog = new Dialog(context, R.style.FullScreenDialog);
        dialog.setCancelable(cancel);
        // dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(p);
        return dialog;
    }


//    public static AlertDialog getDialog(Context context, int view, boolean cancel, double w, double h) {
//        final AlertDialog dialog = new AlertDialog.Builder(context).create();
//        dialog.setCancelable(cancel);
//        dialog.show();
//        dialog.getWindow().setContentView(view);
//
//        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
//        p.width = (int) (MyApplication.getInstance().getDeviceWidth() * w);
//        // p.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        p.height = (int) (MyApplication.getInstance().getDeviceHeight() * h);
//        dialog.getWindow().setAttributes(p);
//        return dialog;
//    }

    public static ProgressDialog getProgressDialog(Context context, String msg) {
        return getProgressDialog(context, msg, false);
    }

    public static ProgressDialog getProgressDialog(Context context, String msg, boolean isCancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(isCancelable);
        return progressDialog;
    }
}
