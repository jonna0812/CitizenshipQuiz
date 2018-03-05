package com.example.android.citizenshipquiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    int answer;
    int correctID;
    EditText nameInput;
    EditText ageInput;
    RadioGroup education;
    RadioGroup english;
    CheckBox job_yes;
    CheckBox job_no;
    RadioGroup income;
    ScrollView mainView;
    TextView resultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        nameInput = (EditText) (findViewById(R.id.enter_name));
        ageInput = (EditText)(findViewById(R.id.enter_age));
        education = (RadioGroup) (findViewById(R.id.education_level));
        english = (RadioGroup) (findViewById(R.id.english_proficiency));
        job_yes = (CheckBox)(findViewById(R.id.job_yes));
        job_no = (CheckBox)(findViewById(R.id.job_no));
        income = (RadioGroup) (findViewById(R.id.income_level));
        mainView = (ScrollView)(findViewById(R.id.mainScrollView));
        resultDisplay = (TextView)(findViewById(R.id.result_textView));
    }

    public void checkAnswers (View view){

        //get name
        String applicantName = nameInput.getText().toString();

        //Check age input and verify if get point
        answer = Integer.parseInt(ageInput.getText().toString());
        boolean a = answer < 60;
        boolean b = answer > 18;
        if(a&&b){
        score += 1;
        }

        //check education that gain point
        answer = education.getCheckedRadioButtonId();
        RadioButton ed_answer = (RadioButton)(findViewById(R.id.el_answerD));
        correctID = ed_answer.getId();
        if(answer == correctID){
            score += 1;
        }

        //check English level for point
        answer = english.getCheckedRadioButtonId();
        RadioButton eng_answer = (RadioButton)(findViewById(R.id.ep_answerD));
        correctID = eng_answer.getId();
        if(answer == correctID){
            score += 1;
        }

        //chech job for point
        boolean hasJob = job_yes.isChecked();
        if(hasJob){
            score += 1;
        }

        //check income level for point
        answer = income.getCheckedRadioButtonId();
        RadioButton in_answer = (RadioButton)(findViewById(R.id.il_AnswerD));
        correctID = in_answer.getId();
        if(answer == correctID){
            score += 1;
        }

        String showMessage = resultDisplay(applicantName, score);
        displayMessage(showMessage);

    }

    //generate a message based on the score
    private String resultDisplay (String applicantName, int score){
        String resultMessage = getString(R.string.phase1) + applicantName;
        if(score == 5){
            resultMessage += "\n"+ getString(R.string.phase2) + " " + score + "\n" + getString(R.string.phase3);
        }else if(score == 4){
            resultMessage += "\n"+ getString(R.string.phase2) + " " + score + "\n" + getString(R.string.phase4);
        }else if(score < 4){
            resultMessage += "\n" + getString(R.string.phase5);
        }
        return resultMessage;
    }

    //display the message in the end
    private void displayMessage (String finalMessage){
        resultDisplay.setText(finalMessage);
    }

    //Clear all selected sections in the app and roll up the scrollview to top.
    public void resetQuiz (View view){
        nameInput.getText().clear();

        ageInput.getText().clear();

        education.clearCheck();

        english.clearCheck();

        job_yes.setChecked(false);
        job_no.setChecked(false);

        income.clearCheck();

        resultDisplay.setText(getString(R.string.def_message));

        mainView.fullScroll(ScrollView.FOCUS_UP);

    }

}
