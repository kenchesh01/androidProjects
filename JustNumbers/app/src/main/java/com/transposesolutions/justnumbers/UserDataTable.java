package com.transposesolutions.justnumbers;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "UserData")
public class UserDataTable {
    // (autoGenerate = true)
    // id number or unique number to store/retrieve data
    @PrimaryKey
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Using getter and setter to store/retrieve top addend random number - Redo Mistake
    @ColumnInfo(name = "topAddend")
    private int topAddend;
    public int getTopAddend() {
        return topAddend;
    }
    public void setTopAddend(int topAddend) {
        this.topAddend = topAddend;
    }

    // Using getter and setter to store/retrieve (Module 1, 2, 3, 4 - Redo Mistake)
    @ColumnInfo(name = "bottomAddend")
    private int bottomAddend;
    public int getBottomAddend() {
        return bottomAddend;
    }
    public void setBottomAddend(int bottomAddend) {
        this.bottomAddend = bottomAddend;
    }

    // Using getter and setter to store/retrieve (Module 5, 6, 7, 8 - Redo Mistake)
    @ColumnInfo(name = "TopAddend1")
    private int mTopAddend1;
    public int getTopAddend1() { return mTopAddend1; }
    public void setTopAddend1(int topAddend1) { mTopAddend1 = topAddend1;  }

    @ColumnInfo(name = "TopAddend2")
    private int mTopAddend2;
    public int getTopAddend2() { return mTopAddend2; }
    public void setTopAddend2(int topAddend2) { mTopAddend2 = topAddend2;  }

    @ColumnInfo(name = "TopAddend3")
    private int mTopAddend3;
    public int getTopAddend3() { return mTopAddend3; }
    public void setTopAddend3(int topAddend3) { mTopAddend3 = topAddend3;  }

    @ColumnInfo(name = "BottomAddend1")
    private int mBottomAddend1;
    public int getBottomAddend1() { return mBottomAddend1; }
    public void setBottomAddend1(int bottomAddend1) { mBottomAddend1 = bottomAddend1;  }

    @ColumnInfo(name = "BottomAddend2")
    private int mBottomAddend2;
    public int getBottomAddend2() { return mBottomAddend2; }
    public void setBottomAddend2(int bottomAddend2) { mBottomAddend2 = bottomAddend2;  }

    @ColumnInfo(name = "BottomAddend3")
    private int mBottomAddend3;
    public int getBottomAddend3() { return mBottomAddend3; }
    public void setBottomAddend3(int bottomAddend3) { mBottomAddend3 = bottomAddend3;  }


    // Using getter and setter to store/retrieve user input value/answer - Redo Mistake
    @ColumnInfo(name = "UserInput")
    private int UserInput;
    public int getUserInput() {
        return UserInput;
    }
    public void setUserInput(int userInput) {
        UserInput = userInput;
    }

    // Using getter and setter to store/retrieve correct answer - Redo Mistake
    @ColumnInfo(name = "Answer")
    private int Answer;
    public int getAnswer() {
        return Answer;
    }
    public void setAnswer(int answer) {
        Answer = answer;
    }

    // Using getter and setter to store/retrieve result (Correct/Wrong) - Redo Mistake
    @ColumnInfo(name = "Status")
    private String Status;
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }

}
