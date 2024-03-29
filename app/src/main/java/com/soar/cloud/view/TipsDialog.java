package com.soar.cloud.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.soar.cloud.R;


public class TipsDialog extends Dialog {

    private TipsDialog(Context context) {
        super(context);
    }

    private TipsDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {

        private Context context;

        private String title;

        private String message;

        private String positive;

        private String negative;

        private View.OnClickListener positiveListener;

        private View.OnClickListener negativeListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message){
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(String positive, View.OnClickListener listener){
            this.positive = positive;
            this.positiveListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negative, View.OnClickListener listener){
            this.negative = negative;
            this.negativeListener = listener;
            return this;
        }

        public TipsDialog create() {
            TipsDialog dialog = new TipsDialog(context, R.style.CustomDialogStyle);
            View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_tips, null);

            WindowManager m = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) (d.getWidth() * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);

            dialog.setContentView(contentView,params);

            if (title != null)
                ((TextView) contentView.findViewById(R.id.tv_title)).setText(title);

            if(message != null)
                ((TextView) contentView.findViewById(R.id.tv_message)).setText(message);

            if(positive != null)
                ((TextView) contentView.findViewById(R.id.tv_positive)).setText(positive);

            if(negative != null)
                ((TextView) contentView.findViewById(R.id.tv_negative)).setText(negative);

            if(positiveListener != null){
                ((TextView) contentView.findViewById(R.id.tv_positive)).setOnClickListener(v->{
                    positiveListener.onClick(v);
                    dialog.dismiss();
                });
            }

            if(negativeListener != null){
                ((TextView) contentView.findViewById(R.id.tv_negative)).setOnClickListener(v->{
                    negativeListener.onClick(v);
                    dialog.dismiss();
                });
            }
            return dialog;
        }

    }
}
