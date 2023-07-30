module com.example.projetjava {
    requires javafx.controls;
    requires javafx.fxml;
            requires org.apache.groovy;
        
                            
    opens com.example.projetjava to javafx.fxml;
    exports com.example.projetjava;
}