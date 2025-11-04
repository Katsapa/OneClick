import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainFrame extends Application {

    private ObservableList<String> macroList = FXCollections.observableArrayList();
    private ObservableList<String> availableActions = FXCollections.observableArrayList();
    private ObservableList<String> currentMacroActions = FXCollections.observableArrayList();

    private TextField macroNameField;
    private Button setTriggerButton;
    private Label triggerLabel;
    private ListView<String> macroListView;
    private ListView<String> actionsListView;
    private ListView<String> currentActionsListView;

    @Override
    public void start(Stage primaryStage) {
        initializeData();


        VBox leftSection = createLeftSection();
        VBox centerSection = createCenterSection();
        VBox rightSection = createRightSection();


        HBox mainLayout = new HBox(20);
        mainLayout.setPadding(new Insets(25));
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom right, #667eea 0%, #764ba2 100%);");
        mainLayout.getChildren().addAll(leftSection, centerSection, rightSection);


        HBox.setHgrow(leftSection, Priority.ALWAYS);
        HBox.setHgrow(centerSection, Priority.ALWAYS);
        HBox.setHgrow(rightSection, Priority.ALWAYS);

        leftSection.setPrefWidth(320);
        centerSection.setPrefWidth(420);
        rightSection.setPrefWidth(320);

        Scene scene = new Scene(mainLayout, 1300, 750);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setTitle("✨ Macro Builder Pro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeData() {
        availableActions.addAll(
                "Задержка", "Нажатие клавиши",
                "Отжатие клавиши", "Нажатие кнопки мыши", "Отжатие кнопки мыши",
                "Двойной клик", "Клик", "Вывод текста",
                "Прокрут колёсика"
        );
        loadMacros();
    }

    private VBox createLeftSection() {
        VBox leftSection = new VBox(15);
        leftSection.setPadding(new Insets(25));
        leftSection.setStyle("-fx-background-color: rgba(255,255,255,0.95); -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");

        // Header with icon
        HBox headerBox = new HBox(10);
        headerBox.setAlignment(Pos.CENTER_LEFT);
        Label title = new Label("🎮 My Macros");
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#4a5568"));

        headerBox.getChildren().add(title);

        macroListView = new ListView<>(macroList);
        macroListView.setStyle("-fx-background-color: #f8fafc; -fx-background-radius: 10; -fx-border-color: #e2e8f0; -fx-border-radius: 10;");
        macroListView.setCellFactory(param -> new MacroListCell());
        macroListView.setPrefHeight(500);

        Button refreshButton = createStyledButton("🔄 Refresh", "#4299e1");
        refreshButton.setMaxWidth(Double.MAX_VALUE);
        refreshButton.setOnAction(e -> loadMacros());

        leftSection.getChildren().addAll(headerBox, macroListView, refreshButton);
        VBox.setVgrow(macroListView, Priority.ALWAYS);

        return leftSection;
    }

    private VBox createCenterSection() {
        VBox centerSection = new VBox(20);
        centerSection.setPadding(new Insets(25));
        centerSection.setStyle("-fx-background-color: rgba(255,255,255,0.95); -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");

        // Header
        Label title = new Label("⚡ Build Macro");
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#4a5568"));

        // Macro name section
        VBox nameSection = createInputSection("📝 Macro Name");
        macroNameField = new TextField();
        macroNameField.setPromptText("Enter a creative name...");
        macroNameField.setStyle("-fx-background-color: #f8fafc; -fx-background-radius: 8; -fx-border-color: #e2e8f0; -fx-border-radius: 8; -fx-padding: 12;");
        macroNameField.setPrefHeight(40);
        nameSection.getChildren().add(macroNameField);

        // Trigger section
        VBox triggerSection = createInputSection("🎯 Hotkey Trigger");
        HBox triggerContent = new HBox(10);
        triggerContent.setAlignment(Pos.CENTER_LEFT);

        setTriggerButton = createStyledButton("⌨️ Set Trigger", "#ed8936");
        triggerLabel = new Label("No trigger set");
        triggerLabel.setStyle("-fx-background-color: #fed7d7; -fx-background-radius: 8; -fx-border-color: #feb2b2; -fx-border-radius: 8; -fx-padding: 12; -fx-text-fill: #c53030;");
        triggerLabel.setPrefSize(200, 40);
        triggerLabel.setAlignment(Pos.CENTER);

        triggerContent.getChildren().addAll(setTriggerButton, triggerLabel);
        HBox.setHgrow(triggerLabel, Priority.ALWAYS);
        triggerSection.getChildren().add(triggerContent);

        // Current actions section
        Label actionsTitle = new Label("📋 Macro Sequence");
        actionsTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        actionsTitle.setTextFill(Color.web("#4a5568"));

        currentActionsListView = new ListView<>(currentMacroActions);
        currentActionsListView.setStyle("-fx-background-color: #f0fff4; -fx-background-radius: 10; -fx-border-color: #9ae6b4; -fx-border-radius: 10;");
        currentActionsListView.setPrefHeight(250);

        // Action buttons
        HBox actionButtons = new HBox(10);
        actionButtons.setAlignment(Pos.CENTER);

        Button moveUpButton = createIconButton("⬆", "#48bb78");
        Button moveDownButton = createIconButton("⬇", "#48bb78");
        Button removeButton = createIconButton("🗑", "#e53e3e");
        Button clearButton = createIconButton("Clear", "#ed8936");

        // Set up button actions
        moveUpButton.setOnAction(e -> moveActionUp());
        moveDownButton.setOnAction(e -> moveActionDown());
        removeButton.setOnAction(e -> removeSelectedAction());
        clearButton.setOnAction(e -> clearAllActions());

        actionButtons.getChildren().addAll(moveUpButton, moveDownButton, removeButton, clearButton);

        // Save button
        Button saveButton = createStyledButton("💾 Save Macro", "#38a169");
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setPrefHeight(45);

        centerSection.getChildren().addAll(
                title, nameSection, triggerSection, actionsTitle,
                currentActionsListView, actionButtons, saveButton
        );
        VBox.setVgrow(currentActionsListView, Priority.ALWAYS);

        return centerSection;
    }

    private VBox createRightSection() {
        VBox rightSection = new VBox(15);
        rightSection.setPadding(new Insets(25));
        rightSection.setStyle("-fx-background-color: rgba(255,255,255,0.95); -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");

        Label title = new Label("🎨 Available Actions");
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#4a5568"));

        Label subtitle = new Label("Drag or click to add to macro");
        subtitle.setFont(Font.font("System", FontWeight.NORMAL, 12));
        subtitle.setTextFill(Color.web("#718096"));

        actionsListView = new ListView<>(availableActions);
        actionsListView.setStyle("-fx-background-color: #f8fafc; -fx-background-radius: 10; -fx-border-color: #e2e8f0; -fx-border-radius: 10;");
        actionsListView.setPrefHeight(550);

        Button addButton = createStyledButton("➕ Add Selected", "#4299e1");
        addButton.setMaxWidth(Double.MAX_VALUE);

        // Add button functionality
        addButton.setOnAction(e -> addSelectedAction());

        rightSection.getChildren().addAll(title, subtitle, actionsListView, addButton);
        VBox.setVgrow(actionsListView, Priority.ALWAYS);

        return rightSection;
    }

    private VBox createInputSection(String title) {
        VBox section = new VBox(8);
        Label label = new Label(title);
        label.setFont(Font.font("System", FontWeight.BOLD, 14));
        label.setTextFill(Color.web("#4a5568"));
        section.getChildren().add(label);
        return section;
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 12 20; -fx-cursor: hand;",
                color
        ));
        button.setOnMouseEntered(e -> button.setStyle(String.format(
                "-fx-background-color: derive(%s, -20%%); -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 12 20; -fx-cursor: hand;",
                color
        )));
        button.setOnMouseExited(e -> button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 12 20; -fx-cursor: hand;",
                color
        )));
        return button;
    }

    private Button createIconButton(String emoji, String color) {
        Button button = new Button(emoji);
        button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14; -fx-background-radius: 6; -fx-padding: 8 12; -fx-cursor: hand;",
                color
        ));
        button.setOnMouseEntered(e -> button.setStyle(String.format(
                "-fx-background-color: derive(%s, -20%%); -fx-text-fill: white; -fx-font-size: 14; -fx-background-radius: 6; -fx-padding: 8 12; -fx-cursor: hand;",
                color
        )));
        button.setOnMouseExited(e -> button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14; -fx-background-radius: 6; -fx-padding: 8 12; -fx-cursor: hand;",
                color
        )));
        return button;
    }

    private void loadMacros() {
        macroList.clear();
        macroList.addAll(
                "Test Combo1","Test Combo2","Test Combo3"
        );

    }

    // Action management methods
    private void addSelectedAction() {
        String selected = actionsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentMacroActions.add(selected);
            showNotification("Action added: " + selected);
        } else {
            showAlert("No Selection", "Please select an action from the list first.");
        }
    }

    private void removeSelectedAction() {
        int selectedIndex = currentActionsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String removedAction = currentMacroActions.remove(selectedIndex);
            showNotification("Action removed: " + removedAction);
        } else {
            showAlert("No Selection", "Please select an action to remove.");
        }
    }

    private void moveActionUp() {
        int selectedIndex = currentActionsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            String item = currentMacroActions.remove(selectedIndex);
            currentMacroActions.add(selectedIndex - 1, item);
            currentActionsListView.getSelectionModel().select(selectedIndex - 1);
            showNotification("Action moved up");
        } else if (selectedIndex == 0) {
            showAlert("Cannot Move", "Action is already at the top.");
        } else {
            showAlert("No Selection", "Please select an action to move.");
        }
    }

    private void moveActionDown() {
        int selectedIndex = currentActionsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < currentMacroActions.size() - 1) {
            String item = currentMacroActions.remove(selectedIndex);
            currentMacroActions.add(selectedIndex + 1, item);
            currentActionsListView.getSelectionModel().select(selectedIndex + 1);
            showNotification("Action moved down");
        } else if (selectedIndex == currentMacroActions.size() - 1) {
            showAlert("Cannot Move", "Action is already at the bottom.");
        } else {
            showAlert("No Selection", "Please select an action to move.");
        }
    }

    private void clearAllActions() {
        if (!currentMacroActions.isEmpty()) {
            currentMacroActions.clear();
            showNotification("All actions cleared");
        } else {
            showAlert("No Actions", "The macro sequence is already empty.");
        }
    }

    private void showNotification(String message) {
        // Simple notification - you could replace this with a Toast or other notification system
        System.out.println("Notification: " + message);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private class MacroListCell extends ListCell<String> {
        private HBox content;
        private CheckBox checkBox;
        private Label label;
        private Button editButton;

        public MacroListCell() {
            super();

            checkBox = new CheckBox();
            checkBox.setSelected(true);
            checkBox.setStyle("-fx-text-fill: #4a5568;");

            label = new Label();
            label.setStyle("-fx-text-fill: #4a5568; -fx-font-weight: bold;");

            editButton = new Button("✏️");
            editButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #4299e1; -fx-cursor: hand; -fx-padding: 2 6;");

            HBox leftBox = new HBox(10, checkBox, label);
            leftBox.setAlignment(Pos.CENTER_LEFT);
            HBox.setHgrow(leftBox, Priority.ALWAYS);

            content = new HBox(10, leftBox, editButton);
            content.setAlignment(Pos.CENTER_LEFT);
            content.setPadding(new Insets(8));
            content.setStyle("-fx-background-color: #f7fafc; -fx-background-radius: 8; -fx-border-color: #e2e8f0; -fx-border-radius: 8;");
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                label.setText(item);
                setGraphic(content);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}