package lesson4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button sendButton;

    public void sendMessageTypeAction() {
        String messageText = textField.getText().trim();
        if(!messageText.isEmpty()) {
            textArea.appendText(messageText+System.lineSeparator());
            textField.clear();
        }
    }
}