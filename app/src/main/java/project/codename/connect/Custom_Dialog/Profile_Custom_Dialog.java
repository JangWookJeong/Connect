package project.codename.connect.Custom_Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import project.codename.connect.R;

public class Profile_Custom_Dialog {

    private Context Context;
    private TextView Text_Title, Text_Explanation;
    private Dialog dialog;
    private EditText Text_Content;
    private Button Ok_Button, Cancel_Button;


    public Profile_Custom_Dialog(Context context) {
        this.Context = context;

    }//constructor

    public void callFuntion(final TextView profile_text, String text, String annotation) {
        dialog = new Dialog(Context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.profile_custom_dialog);
        createponent();
        addcomponent(text, annotation);
        addlistener(profile_text);


    }/////callFuntion

    private void addlistener(final TextView profile_text) {
        Ok_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_text.setText(Text_Content.getText().toString());
                dialog.dismiss();
            }
        });///Ok_Button
        Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void createponent() {

        dialog.show();
        Text_Title = (TextView) dialog.findViewById(R.id.profile_custom_dialog_textview_title);
        Text_Explanation = (TextView) dialog.findViewById(R.id.profile_custom_dialog_textview_explanation);
        Text_Content = (EditText) dialog.findViewById(R.id.profile_custom_dialog_edittext);
        Ok_Button = (Button) dialog.findViewById(R.id.profile_custom_dialog_okbutton);
        Cancel_Button = (Button) dialog.findViewById(R.id.profile_custom_dialog_cancelbutton);

    }

    ;

    private void addcomponent(String text, String annotation) {
        Text_Title.setText(text);
        Text_Explanation.setText(annotation);

    }//addcomponent()
}/////
