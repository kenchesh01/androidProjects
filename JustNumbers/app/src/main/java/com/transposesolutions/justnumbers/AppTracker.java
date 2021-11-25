package com.transposesolutions.justnumbers;
import android.app.Application;
public class AppTracker extends Application {


    // To Store the value for Number of Math Problems - updateQuestions()
    int TotalProblems = 1;

    // Getter Method to retrieve stored values user selected values for number of math problems - updateQuestions()
    public int getTotalMathProblems() {
        return TotalProblems;
    }

    // Setter Method to store the user selected values for number of math problems - updateQuestion()
    public void setTotalMathProblems(int mTotalMathProblems) {
        TotalProblems = mTotalMathProblems;
    }


    // ID to store the user selected values for number of math problems used to update the text view - display number of questions
    int NumberOfQuestions;

    // Getter Method to retrieve stored values user selected values for number of math problems
    public int getNumberOfQuestions() {
        return NumberOfQuestions;
    }

    // Setter Method to store the user selected values for number of math problems
    public void setNumberOfQuestions(int mNumberOfQuestions) {
        NumberOfQuestions = mNumberOfQuestions;
    }


    // ID to store Math Problem User Got it Wrong - redo mistake()
    Boolean MistakeQuestions = true;

    // Getter Method to retrieve stored values for  Math Problem User Got it Wrong
    public Boolean getMistakeQuestions() {
        return MistakeQuestions;
    }

    // Setter Method to return stored value for  Math Problem User Got it Wrong
    public void setMistakeQuestions(Boolean mMistakeQuestions) {
        MistakeQuestions = mMistakeQuestions;
    }

    // ID to store default value for Clock Settings
    Boolean ClockSettings = true;

    // Getter Method to retrieve stored values for Clock Settings - Default value - True
    public Boolean getClockSettings() {
        return ClockSettings;
    }

    // Setter Method to store the user selected values for Clock Settings - Default value - True
    public void setClockSettings(Boolean mClockSettings) {
        this.ClockSettings = mClockSettings;
    }
}
