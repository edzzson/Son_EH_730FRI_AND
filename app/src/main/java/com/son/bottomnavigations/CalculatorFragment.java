package com.son.bottomnavigations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {

    private TextView display;
    private double currentResult = 0.0;
    private String operator = null;
    private boolean isOperatorPressed = false;
    private boolean isNewOperation = true;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        display = view.findViewById(R.id.number_display);

        // Number buttons
        Button btn0 = view.findViewById(R.id.btn_0);
        Button btn1 = view.findViewById(R.id.btn_1);
        Button btn2 = view.findViewById(R.id.btn_2);
        Button btn3 = view.findViewById(R.id.btn_3);
        Button btn4 = view.findViewById(R.id.btn_4);
        Button btn5 = view.findViewById(R.id.btn_5);
        Button btn6 = view.findViewById(R.id.btn_6);
        Button btn7 = view.findViewById(R.id.btn_7);
        Button btn8 = view.findViewById(R.id.btn_8);
        Button btn9 = view.findViewById(R.id.btn_9);

        // Operator buttons
        Button btnAdd = view.findViewById(R.id.additional_button);
        Button btnSubtract = view.findViewById(R.id.minus_button);
        Button btnEquals = view.findViewById(R.id.equals_button);
        Button btnClear = view.findViewById(R.id.clear_button);
        ImageButton btnErase = view.findViewById(R.id.erase_button);
        Button btnDecimal = view.findViewById(R.id.btn_decimal);

        // Number button listeners
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String number = button.getText().toString();
                if (isNewOperation) {
                    display.setText("");
                    isNewOperation = false;
                }
                display.append(number);
            }
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);
        btnDecimal.setOnClickListener(numberClickListener);

        // Operator button listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("+");
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("-");
            }
        });

        // Clear button
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        // Backspace button
        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });

        // Equals button
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator != null) {
                    double operand2 = parseDoubleOrZero(display.getText().toString());
                    switch (operator) {
                        case "+":
                            currentResult += operand2;
                            break;
                        case "-":
                            currentResult -= operand2;
                            break;
                    }
                    display.setText(formatResult(currentResult));
                    isNewOperation = true;
                    isOperatorPressed = false;
                }
            }
        });

    }

    private void handleOperator(String op) {
        String currentText = display.getText().toString();

        if (!isOperatorPressed) {
            // First time operator is pressed, store the first operand
            currentResult = parseDoubleOrZero(currentText);
            operator = op;
            isOperatorPressed = true;
            isNewOperation = true;
        } else {
            // If another operator is pressed, calculate the new result
            double operand2 = parseDoubleOrZero(currentText);
            switch (operator) {
                case "+":
                    currentResult += operand2;
                    break;
                case "-":
                    currentResult -= operand2;
                    break;
            }
            operator = op;
            display.setText(formatResult(currentResult));
            isNewOperation = true;
        }
    }

    private double parseDoubleOrZero(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String formatResult(double result) {
        if (result % 1 == 0) {
            return String.valueOf((int) result);  // Whole number
        } else {
            return decimalFormat.format(result);  // Decimal number
        }
    }

    private void clear() {
        display.setText("0");
        currentResult = 0.0;
        operator = null;
        isOperatorPressed = false;
        isNewOperation = true;
    }
}