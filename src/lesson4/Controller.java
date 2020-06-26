package lesson4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button sendButton;

    @FXML
    private Text count;

    private double valueOne = 0;
    private String valueTop = "";
    private boolean start = true;
    private Calc calc = new Calc();
    @FXML
    private void clickBtnValue(ActionEvent event){
        if (start) {
            count.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        count.setText(count.getText() + value);
    }
    @FXML
    private void clickBtnOperations(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (!"=".equals(value)){
            if (!valueTop.isEmpty())
                return;
            valueTop = value;
            valueOne = Double.parseDouble(count.getText());
            count.setText("");
        }
        else {
            if (valueTop.isEmpty())
                return;
            count.setText(String.valueOf(calc.calculate(valueOne,Long.parseLong(count.getText()),valueTop)));
            valueTop = "";
            start = true;
        }
    }
    public void sendMessageTypeAction() {
        String messageText = textField.getText().trim();
        if(!messageText.isEmpty()) {
            textArea.appendText(messageText+System.lineSeparator());
            textField.clear();
        }
    }
}