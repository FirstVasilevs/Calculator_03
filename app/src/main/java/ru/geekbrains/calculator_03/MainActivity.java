package ru.geekbrains.calculator_03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormatSymbols;

import ru.geekbrains.calculator_03.dataAndOperations.Calculator;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView resultTextView;
    private TextView historyTextView;
    private static final String CALCULATOR_IN_BUNDLE = "CALCULATOR_IN_BUNDLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState)
            calculator = new Calculator();
        else
            calculator = (Calculator) savedInstanceState.getSerializable("CALCULATOR_IN_BUNDLE");

        char decimalSeparatorChar = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        calculator.setDecimalSeparator(decimalSeparatorChar);
        ((Button) findViewById(R.id.key_dot)).setText(String.valueOf(decimalSeparatorChar));

        historyTextView = findViewById(R.id.field_history);
        historyTextView.setText(calculator.getHistoryString());
        resultTextView = findViewById(R.id.field_result);
        resultTextView.setText(calculator.getValue());

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        findViewById(R.id.key_reset).setOnClickListener(view -> doOperation("reset"));
        findViewById(R.id.key_erase).setOnClickListener(view -> doOperation("backspace"));
        findViewById(R.id.key_equal).setOnClickListener(view -> doOperation("result"));
        findViewById(R.id.key_dot).setOnClickListener(view -> doOperation("decimal_separator"));
        findViewById(R.id.key_plus).setOnClickListener(view -> doOperation("addition"));
        findViewById(R.id.key_minus).setOnClickListener(view -> doOperation("subtraction"));
        findViewById(R.id.key_divide).setOnClickListener(view -> doOperation("division"));
        findViewById(R.id.key_multiply).setOnClickListener(view -> doOperation("multiplication"));
        findViewById(R.id.key_percent).setOnClickListener(view -> doOperation("percent"));


        findViewById(R.id.key_zero).setOnClickListener(view -> numberButtonClick("0"));
        findViewById(R.id.key_one).setOnClickListener(view -> numberButtonClick("1"));
        findViewById(R.id.key_two).setOnClickListener(view -> numberButtonClick("2"));
        findViewById(R.id.key_three).setOnClickListener(view -> numberButtonClick("3"));
        findViewById(R.id.key_four).setOnClickListener(view -> numberButtonClick("4"));
        findViewById(R.id.key_five).setOnClickListener(view -> numberButtonClick("5"));
        findViewById(R.id.key_six).setOnClickListener(view -> numberButtonClick("6"));
        findViewById(R.id.key_seven).setOnClickListener(view -> numberButtonClick("7"));
        findViewById(R.id.key_eight).setOnClickListener(view -> numberButtonClick("8"));
        findViewById(R.id.key_nine).setOnClickListener(view -> numberButtonClick("9"));
        findViewById(R.id.key_twoZero).setOnClickListener(view -> numberButtonClick("00"));
    }

    private void numberButtonClick(String currentSymbol) {
        calculator.addSymbol(currentSymbol);
        resultTextView.setText(calculator.getValue());
    }

    private void doOperation(final String operation) {
        switch (operation) {
            case "reset":
                calculator.reset();
                break;
            case "backspace":
                calculator.backspace();
                break;
            case "result":
                calculator.result();
                break;
            case "addition":
                calculator.addition();
                break;
            case "subtraction":
                calculator.subtraction();
                break;
            case "division":
                calculator.division();
                break;
            case "multiplication":
                calculator.multiplication();
                break;
            case "decimal_separator":
                calculator.decimal_separator();
                resultTextView.setText(calculator.getValue());
                return;
        }
        resultTextView.setText(calculator.getValue());
        historyTextView.setText(calculator.getHistoryString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(CALCULATOR_IN_BUNDLE, calculator);
    }

}